package com.cisco.josouthe.interceptor;

import com.appdynamics.instrumentation.sdk.Rule;
import com.appdynamics.instrumentation.sdk.SDKClassMatchType;
import com.appdynamics.instrumentation.sdk.SDKStringMatchType;
import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.cisco.josouthe.MetaData;
import com.cisco.josouthe.json.AuthenticationOverrideInfo;
import com.cisco.josouthe.monitor.BaseJMSMonitor;
import com.cisco.josouthe.monitor.MQMonitor;
import com.cisco.josouthe.monitor.Scheduler;
import com.cisco.josouthe.wrapper.ibmmq.DestinationWrapper;
import com.cisco.josouthe.wrapper.jms.JmsConnectionFactoryWrapper;
import com.cisco.josouthe.wrapper.jms.JmsContextWrapper;
import com.cisco.josouthe.wrapper.ibmmq.MQQueueManagerWrapper;
import org.json.JSONArray;
import org.json.JSONTokener;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class QManagerLifeCycleInterceptor extends AGenericInterceptor {
    private static String MQQUEUEMANAGER = "com.ibm.mq.MQQueueManager";
    private static String ACTIVEMQCONNECTIONFACTORY = "org.apache.activemq.ActiveMQConnectionFactory";
    private static String AUTHENTICATION_CONFIG_FILE = "IBMMQAgentPlugin-authentications.json";
    private Scheduler scheduler = null;
    //ConcurrentHashMap<Object, JmsConnectionFactoryWrapper> connectionFactoryObjectMap = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, JmsConnectionFactoryWrapper> connectionFactoryHostMap = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, JmsContextWrapper> contextMap = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, BaseJMSMonitor> monitors = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, AuthenticationOverrideInfo> authentications = new ConcurrentHashMap<>();
    ConcurrentHashMap<Object, Object> producerContextMap = new ConcurrentHashMap<>();
    ConcurrentHashMap<Object, BaseJMSMonitor> qMgrMonitorMap = new ConcurrentHashMap<>();
    private AuthenticationOverrideInfo defaultAuthenticationOverrideInfo;

    public QManagerLifeCycleInterceptor() {
        super();
        getLogger().info(String.format("Initialized plugin class %s version %s build date %s",getClass().getCanonicalName(), MetaData.VERSION, MetaData.BUILDTIMESTAMP));
        initializeAuthenticationConfig( new File( this.getAgentPluginDirectory(), AUTHENTICATION_CONFIG_FILE), authentications );
    }

    private void initializeAuthenticationConfig(File configFile, ConcurrentHashMap<String, AuthenticationOverrideInfo> authenticationsHashMap) {
        if( configFile == null || !configFile.exists() ) {
            getLogger().info(String.format("Authentication Config file does not exist, to override MQ users to monitor with, please create the file: %s", String.valueOf(configFile) ) );
        }
        try {
            InputStream inputStream = new FileInputStream(configFile);
            JSONTokener tokener = new JSONTokener(inputStream);
            JSONArray array = new JSONArray(tokener);
            for( int i=0; i< array.length(); i++ ){
                AuthenticationOverrideInfo authenticationOverrideInfo = new AuthenticationOverrideInfo(array.getJSONObject(i));
                getLogger().debug(String.format("Initializing Authentication Override: %s", authenticationOverrideInfo));
                if( authenticationOverrideInfo.isDefault() ) {
                    if( defaultAuthenticationOverrideInfo == null ) {
                        defaultAuthenticationOverrideInfo = authenticationOverrideInfo;
                    } else {
                        getLogger().warn(String.format("Default authentication override config already set, looks to be set more than once, please update the config file '%s' to only have one default, we are ignoring the others", configFile.getAbsolutePath()));
                    }
                } else {
                    authenticationsHashMap.put(authenticationOverrideInfo.getKey(), authenticationOverrideInfo);
                }
            }
        } catch (Exception e) {
            getLogger().error(String.format("Error reading %s, exception: %s", configFile.getAbsolutePath(), e.toString()));
        }
    }

    @Override
    public Object onMethodBegin(Object objectIntercepted, String className, String methodName, Object[] params) {
        getLogger().debug(String.format("onMethodBegin called for %s.%s( %s ) object: %s",className, methodName, printParameters(params), String.valueOf(objectIntercepted)));
        /*
        if( className.equals(MQQUEUEMANAGER) && methodName.equals("disconnect") && scheduler != null ) { //we want to remove this queue manager before the disconnect runs to be safe
            getLogger().debug("A com.ibm.mq.MQQueueManager is disconnecting, we are running, will attempt to remove it from the scheduler for monitoring");
            queueManagerNameMap.remove( queueManagerObjectMap.get(objectIntercepted).getQueueManagerName() );
            MQMonitorBase mqMonitor = queueManagerObjectMap.remove(objectIntercepted);
            getLogger().debug("Removed "+ String.valueOf(mqMonitor) );
            return null;
        }
         */

        if( methodName.equals("close") ) {
            JmsContextWrapper context = contextMap.remove(objectIntercepted.toString());
            if( context != null ) {
                JmsConnectionFactoryWrapper jmsConnectionFactoryWrapper = connectionFactoryHostMap.get(context.getConnectionName());
                if( jmsConnectionFactoryWrapper != null )
                    jmsConnectionFactoryWrapper.removeContext(context);
                /*if (jmsConnectionFactoryWrapper.getContextSet().isEmpty()) {
                    connectionFactoryObjectMap.remove(jmsConnectionFactoryWrapper.getObject());
                    getLogger().debug("Removed connection factory from map");
                }*/
                getLogger().debug(String.format("Removed context from connectionFactoryMap, size now %d, contextMap size now %d", connectionFactoryHostMap.size(), contextMap.size()));
            }
        }
        return null;
    }

    private Object printParameters(Object[] params) {
        if( params==null || params.length==0 ) return "";
        StringBuilder sb = new StringBuilder();
        for( int i=0; i< params.length; i++) {
            sb.append("'");
            sb.append(params[i]);
            sb.append("'");
            if( i< params.length-1) sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public void onMethodEnd(Object state, Object objectIntercepted, String className, String methodName, Object[] params, Throwable exception, Object returnVal) {
        getLogger().debug(String.format("onMethodEnd called for %s.%s( %s ) object: %s",className, methodName, printParameters(params), String.valueOf(objectIntercepted)));

        if( methodName.equals("createProducer") ) {
            producerContextMap.put(returnVal, objectIntercepted);
        }

        if( methodName.equals("send") ) { //JMSProducer.send( Destination, Message )
            if(producerContextMap.containsKey(objectIntercepted) ) {
                JmsContextWrapper context = getJmsContextWrapper( producerContextMap.get(objectIntercepted) );
                DestinationWrapper destination = new DestinationWrapper( this, params[0], objectIntercepted);
                addToMonitor(context, destination);
            }
        }

        if( methodName.equals("createQueue") ) {
            String queueName = (String) params[0];
            JmsContextWrapper context = getJmsContextWrapper(objectIntercepted);
            context.addQueue(queueName);
            addToMonitor(context, queueName, null);
        }

        if( methodName.equals("createTopic") ) {
            String topicName = (String) params[0];
            JmsContextWrapper context = getJmsContextWrapper(objectIntercepted);
            addToMonitor(context, null, topicName);
        }

        if( methodName.equals("createConsumer") || methodName.equals("createDurableConsumer") || methodName.equals("createSharedConsumer") || methodName.equals("createSharedDurableConsumer") ) {
            JmsContextWrapper context = getJmsContextWrapper(objectIntercepted);
            DestinationWrapper destination = new DestinationWrapper( this, params[0], context);
            addToMonitor(context, destination);
        }

        if( methodName.equals("createContext") ) {
            JmsConnectionFactoryWrapper connectionFactoryWrapper = new JmsConnectionFactoryWrapper(this, objectIntercepted);
            if( ! connectionFactoryHostMap.containsKey(connectionFactoryWrapper.getHostPortString())) {
                connectionFactoryHostMap.put(connectionFactoryWrapper.getHostPortString(), connectionFactoryWrapper);
            }
            connectionFactoryHostMap.put(connectionFactoryWrapper.getHostPortString(), connectionFactoryWrapper);
            getLogger().debug(String.format("Connection Factory Intercepted for host: %s channel: %s app: %s qmgr: %s"
                    ,connectionFactoryWrapper.getStringProperty("XMSC_WMQ_HOST_NAME")
                    ,connectionFactoryWrapper.getStringProperty("XMSC_WMQ_CHANNEL")
                    ,connectionFactoryWrapper.getStringProperty("XMSC_WMQ_APPNAME")
                    ,connectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER")
            ));
            if( ! contextMap.containsKey(returnVal.toString()) ) {
                contextMap.put(returnVal.toString(), new JmsContextWrapper(this, returnVal, objectIntercepted));
            }

            JmsContextWrapper context = contextMap.get(returnVal.toString());
            getLogger().debug(String.format("JMS Provider Name: '%s'",context.getJMSProviderName()));
            switch (context.getJMSProviderName()) {
                case "IBM Websphere":
                case "IBM MQ JMS Provider": {
                    String key = String.format("%s:%s", context.getJMSProviderName(), connectionFactoryWrapper.getHostPortString());
                    if(! monitors.containsKey(key)) {
                        AuthenticationOverrideInfo authenticationOverrideInfo = authentications.get(key);
                        if( authenticationOverrideInfo == null && defaultAuthenticationOverrideInfo != null )
                            authenticationOverrideInfo = defaultAuthenticationOverrideInfo;
                        monitors.put(key, new MQMonitor(this, key, "IBM MQ JMS", new MQQueueManagerWrapper(this, connectionFactoryWrapper, authenticationOverrideInfo) ));
                    }
                    break;
                }
                default: getLogger().debug(String.format("Ignoring this JMS Provider, because it is not currently supported. name: '%s'",context.getJMSProviderName()));
            }
            initializeScheduler();
            connectionFactoryWrapper.addContext(context);
            context.setConnectionName(connectionFactoryWrapper.getHostPortString());
        }

        /*
        MQQueueManager obtainBaseMQQueueManager(String qmgr, Hashtable properties, MQConnectionManager cxMan, URL ccdtUrl) throws MQException {
        if (Trace.isOn) {
            Trace.entry(this, "com.ibm.mq.MQQueueManagerFactory", "obtainBaseMQQueueManager(String,Hashtable,MQConnectionManager,URL)", new Object[]{qmgr, Trace.sanitizeMap(properties, "password", "XXXXXXXXXXXX"), cxMan, ccdtUrl});
        }

        String transport = this.determineTransport(properties);
        MQManagedConnectionFactory mcf = MQSESSION.getMQManagedConnectionFactory(transport, qmgr, properties);
        MQConnectionRequestInfo cxReqInf = MQSESSION.getConnectionRequestInfo(transport, properties, ccdtUrl);

        MQQueueManager m;
        try {
            m = (MQQueueManager)cxMan.allocateConnection(mcf, cxReqInf);

         */
        if( methodName.equals("obtainBaseMQQueueManager") ) { //this is the constructor for an MQQueueManager, with arguments regarding construction
            String key = getKey( (Hashtable) params[1] );
            if(! monitors.containsKey(key)) {
                AuthenticationOverrideInfo authenticationOverrideInfo = authentications.get(key);
                if( authenticationOverrideInfo == null && defaultAuthenticationOverrideInfo != null )
                    authenticationOverrideInfo = defaultAuthenticationOverrideInfo;
                monitors.put(key, new MQMonitor(this, key, "IBM MQ JMS", new MQQueueManagerWrapper(this, returnVal, (String) params[0], (Hashtable) params[1], authenticationOverrideInfo ) ));
            }
            qMgrMonitorMap.put(objectIntercepted, monitors.get(key));
        }

        if( methodName.equals("accessQueue") ) {
            String queueName = (String) params[0];
            BaseJMSMonitor monitor = qMgrMonitorMap.get(objectIntercepted);
            if( monitor != null ) {
                monitor.addQueue(queueName);
            }
        }

        if( methodName.equals("accessTopic") ) {
            BaseJMSMonitor monitor = qMgrMonitorMap.get(objectIntercepted);
            String topicString = null;
            String topicName = null;
            if( params[0] instanceof String ) {
                topicString = (String) params[0];
                topicName = (String) params[1];
            } else {
                topicString = (String) params[1];
                topicName = (String) params[2];
            }
            if( monitor != null ) {
                if( topicString != null ) monitor.addTopic(topicString);
                if( topicName != null ) monitor.addTopic(topicName);
            }
        }

        if( methodName.equals("put") ) {
            BaseJMSMonitor monitor = qMgrMonitorMap.get(objectIntercepted);
            if( monitor != null ) {
                if (params[0] instanceof String) { //this is a queue
                    monitor.addQueue( (String)params[0]);
                } else { //the first param is an int specifying type
                    int type = (Integer) params[0];
                    if( type == 1 ) monitor.addQueue((String) params[1]);
                    if( type == 8 ) monitor.addTopic((String) params[1]);
                }
            }
        }

    }

    private void addToMonitor( JmsContextWrapper context, DestinationWrapper destination ) {
        if( destination.isQueue() )
            addToMonitor(context, destination.getName(), null);
        if( destination.isTopic() )
            addToMonitor(context, null, destination.getName());
    }

    private void addToMonitor(JmsContextWrapper context, String queueName, String topicName ) {
        if( context.getConnectionName() != null && connectionFactoryHostMap.containsKey(context.getConnectionName())) {
            BaseJMSMonitor jmsMonitor = getMonitor(context);
            String channelName = getChannelName(context);
            if( jmsMonitor != null ) {
                if( channelName != null ) jmsMonitor.addChannel(channelName);
                if( queueName != null ) {
                    jmsMonitor.addQueue(queueName);
                    getLogger().debug(String.format("Added Queue '%s' to Monitor for '%s'", queueName, jmsMonitor.toString()));
                }
                if( topicName != null ) {
                    jmsMonitor.addTopic( topicName );
                    getLogger().debug(String.format("Added Topic '%s' to Monitor for '%s'", topicName, jmsMonitor.toString()));
                }
            }
        } else {
            getLogger().debug(String.format("Connection not found for this object: %s", context.getConnectionName()));
        }
    }

    private String getKey( Hashtable properties ) {
        return String.format("IBM MQ JMS Provider:%s:%d", String.valueOf(properties.get("hostname")), Integer.valueOf(String.valueOf(properties.get("port"))) );
    }

    private BaseJMSMonitor getMonitor( JmsContextWrapper context ) {
        String key = String.format("%s:%s", context.getJMSProviderName(), context.getConnectionName());
        return monitors.get(key);
    }

    private JmsContextWrapper getJmsContextWrapper(Object objectIntercepted) {
        JmsContextWrapper context = contextMap.get(objectIntercepted.toString());
        if( context == null ) {
            context = new JmsContextWrapper(this, objectIntercepted, null);
            contextMap.put(objectIntercepted.toString(), context);
        }
        return context;
    }

    private String getChannelName(JmsContextWrapper context) {
        if(context != null && connectionFactoryHostMap.containsKey(context.getConnection().getHostPortString())) {
            JmsConnectionFactoryWrapper connectionFactoryWrapper = connectionFactoryHostMap.get(context.getConnection().getHostPortString());
            return connectionFactoryWrapper.getStringProperty("XMSC_WMQ_CHANNEL");
        }
        return null;
    }

    private void initializeScheduler() {
        if( scheduler != null ) return;
        long sleepTimeMS = Long.parseLong(System.getProperty( "IBMMQAgentPlugin.sleepTimeMS", "30000"));
        scheduler = Scheduler.getInstance(sleepTimeMS, monitors, getLogger());
        scheduler.start();
        getLogger().debug("Initialized Scheduler to monitor MQ");

    }

    @Override
    public List<Rule> initializeRules() {
        ArrayList<Rule> rules = new ArrayList<Rule>();

        rules.add( new Rule.Builder("javax.jms.JMSContext")
                .classMatchType(SDKClassMatchType.IMPLEMENTS_INTERFACE)
                .methodMatchString("create")
                .methodStringMatchType(SDKStringMatchType.STARTSWITH)
                .build());
        rules.add( new Rule.Builder("javax.jms.JMSContext")
                .classMatchType(SDKClassMatchType.IMPLEMENTS_INTERFACE)
                .methodMatchString("close")
                .methodStringMatchType(SDKStringMatchType.EQUALS)
                .build());
        rules.add( new Rule.Builder("javax.jms.JMSProducer")
                .classMatchType(SDKClassMatchType.IMPLEMENTS_INTERFACE)
                .methodMatchString("send")
                .methodStringMatchType(SDKStringMatchType.EQUALS)
                .build());
        rules.add( new Rule.Builder("com.ibm.msg.client.jms.JmsFactoryFactory")
                .classMatchType(SDKClassMatchType.INHERITS_FROM_CLASS)
                .methodMatchString("createConnectionFactory")
                .methodStringMatchType(SDKStringMatchType.EQUALS)
                .build());
        rules.add( new Rule.Builder("com.ibm.msg.client.jms.JmsConnectionFactory")
                .classMatchType(SDKClassMatchType.IMPLEMENTS_INTERFACE)
                .methodMatchString("createContext")
                .methodStringMatchType(SDKStringMatchType.EQUALS)
                .build());
        rules.add( new Rule.Builder("com.ibm.mq.MQQueueManagerFactory")
                .classMatchType(SDKClassMatchType.MATCHES_CLASS)
                .methodMatchString("obtainBaseMQQueueManager")
                .methodStringMatchType(SDKStringMatchType.EQUALS)
                .build());
        rules.add( new Rule.Builder("com.ibm.mq.MQQueueManager")
                .classMatchType(SDKClassMatchType.MATCHES_CLASS)
                .methodMatchString("access")
                .methodStringMatchType(SDKStringMatchType.STARTSWITH)
                .build());
        rules.add( new Rule.Builder("com.ibm.mq.MQQueueManager")
                .classMatchType(SDKClassMatchType.MATCHES_CLASS)
                .methodMatchString("put")
                .methodStringMatchType(SDKStringMatchType.STARTSWITH)
                .build());
        return rules;
    }
}
