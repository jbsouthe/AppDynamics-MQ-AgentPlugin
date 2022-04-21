package com.cisco.josouthe;

import com.appdynamics.instrumentation.sdk.Rule;
import com.appdynamics.instrumentation.sdk.SDKClassMatchType;
import com.appdynamics.instrumentation.sdk.SDKStringMatchType;
import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.cisco.josouthe.monitor.BaseJMSMonitor;
import com.cisco.josouthe.monitor.MQMonitor;
import com.cisco.josouthe.wrapper.JmsConnectionFactoryWrapper;
import com.cisco.josouthe.wrapper.JmsContextWrapper;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class QManagerLifeCycleInterceptor extends AGenericInterceptor {
    private static String MQQUEUEMANAGER = "com.ibm.mq.MQQueueManager";
    private static String ACTIVEMQCONNECTIONFACTORY = "org.apache.activemq.ActiveMQConnectionFactory";
    private Scheduler scheduler = null;
    //ConcurrentHashMap<Object, JmsConnectionFactoryWrapper> connectionFactoryObjectMap = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, JmsConnectionFactoryWrapper> connectionFactoryHostMap = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, JmsContextWrapper> contextMap = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, BaseJMSMonitor> monitors = new ConcurrentHashMap<>();

    public QManagerLifeCycleInterceptor() {
        super();
        getLogger().info("Initialized MQ Monitor on Agent");
    }

    @Override
    public Object onMethodBegin(Object objectIntercepted, String className, String methodName, Object[] params) {
        getLogger().debug(String.format("onMethodBegin called for %s.%s( %s ) object: %s",className, methodName, printParameters(params), String.valueOf(objectIntercepted)));
        /*
        if( className.equals(MQQUEUEMANAGER) && methodName.equals("disconnect") && scheduler != null ) { //we want to remove this queue manager before the disconnect runs to be safe
            getLogger().info("A com.ibm.mq.MQQueueManager is disconnecting, we are running, will attempt to remove it from the scheduler for monitoring");
            queueManagerNameMap.remove( queueManagerObjectMap.get(objectIntercepted).getQueueManagerName() );
            MQMonitorBase mqMonitor = queueManagerObjectMap.remove(objectIntercepted);
            getLogger().info("Removed "+ String.valueOf(mqMonitor) );
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
                    getLogger().info("Removed connection factory from map");
                }*/
                getLogger().info(String.format("Removed context from connectionFactoryMap, size now %d, contextMap size now %d", connectionFactoryHostMap.size(), contextMap.size()));
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

        if( methodName.equals("createQueue") ) {
            JmsContextWrapper context = new JmsContextWrapper(this, objectIntercepted, null);
            String queueName = (String) params[0];
            if( contextMap.containsKey(objectIntercepted.toString()) ) {
                context = contextMap.get(objectIntercepted.toString());
            } else {
                contextMap.put(objectIntercepted.toString(), context);
            }
            context.addQueue(queueName);
            if( context.getConnectionName() != null && connectionFactoryHostMap.containsKey(context.getConnectionName())) {
                String key = String.format("%s:%s", context.getJMSProviderName(), context.getConnectionName());
                monitors.get(key).addQueue(queueName);
                getLogger().info(String.format("Added Queue '%s' to Monitor for '%s'", queueName, monitors.get(key).toString() ));
            } else {
                getLogger().info(String.format("Connection not found for this object: %s", context.getConnectionName()));
            }

        }

        if( methodName.equals("createContext") ) {
            JmsConnectionFactoryWrapper connectionFactoryWrapper = new JmsConnectionFactoryWrapper(this, objectIntercepted);
            if( ! connectionFactoryHostMap.containsKey(connectionFactoryWrapper.getHostPortString())) {
                connectionFactoryHostMap.put(connectionFactoryWrapper.getHostPortString(), connectionFactoryWrapper);
            }
            connectionFactoryHostMap.put(connectionFactoryWrapper.getHostPortString(), connectionFactoryWrapper);
            getLogger().info(String.format("Connection Factory Intercepted for host: %s channel: %s app: %s qmgr: %s"
                    ,connectionFactoryWrapper.getStringProperty("XMSC_WMQ_HOST_NAME")
                    ,connectionFactoryWrapper.getStringProperty("XMSC_WMQ_CHANNEL")
                    ,connectionFactoryWrapper.getStringProperty("XMSC_WMQ_APPNAME")
                    ,connectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER")
            ));
            if( ! contextMap.containsKey(returnVal.toString()) ) {
                contextMap.put(returnVal.toString(), new JmsContextWrapper(this, returnVal, objectIntercepted));
            }

            JmsContextWrapper context = contextMap.get(returnVal.toString());
            getLogger().info(String.format("JMS Provider Name: '%s'",context.getJMSProviderName()));
            switch (context.getJMSProviderName()) {
                case "IBM MQ JMS Provider": {
                    String key = String.format("%s:%s", context.getJMSProviderName(), connectionFactoryWrapper.getHostPortString());
                    if(! monitors.containsKey(key))
                        monitors.put( key, new MQMonitor(this, connectionFactoryWrapper, key, "IBM MQ JMS") );
                    break;
                }
                default: getLogger().info(String.format("Ignoring this JMS Provider, because it is not currently supported. name: '%s'",context.getJMSProviderName()));
            }
            initializeScheduler();
            connectionFactoryWrapper.addContext(context);
            context.setConnectionName(connectionFactoryWrapper.getHostPortString());
        }

        /*
        if( methodName.equals("createConnectionFactory") ) { //JmsConnectionFactory is returned from this object, and then used to make contexts and such
            if( ! connectionFactoryObjectMap.containsKey(returnVal)) {
                connectionFactoryObjectMap.put(returnVal,new JmsConnectionFactoryWrapper(this, returnVal));
            }
            JmsConnectionFactoryWrapper connectionFactoryWrapper = connectionFactoryObjectMap.get(returnVal);
            getLogger().info(String.format("Connection Factory Intercepted for host: %s channel: %s app: %s qmgr: %s"
                    ,connectionFactoryWrapper.getStringProperty("XMSC_WMQ_HOST_NAME") //"XMSC_WMQ_PORT"
                    ,connectionFactoryWrapper.getStringProperty("XMSC_WMQ_CHANNEL")
                    ,connectionFactoryWrapper.getStringProperty("XMSC_WMQ_APPNAME")
                    ,connectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER")
            ));
        }

         */

    }

    private void initializeScheduler() {
        if( scheduler != null ) return;
        scheduler = Scheduler.getInstance(30000, monitors);
        scheduler.start();
        getLogger().info("Initialized Scheduler to monitor MQ");

    }

    @Override
    public List<Rule> initializeRules() {
        ArrayList<Rule> rules = new ArrayList<Rule>();

        rules.add( new Rule.Builder("javax.jms.JMSContext")
                .classMatchType(SDKClassMatchType.IMPLEMENTS_INTERFACE)
                .methodMatchString("createQueue")
                .methodStringMatchType(SDKStringMatchType.EQUALS)
                .build());
        rules.add( new Rule.Builder("javax.jms.JMSContext")
                .classMatchType(SDKClassMatchType.IMPLEMENTS_INTERFACE)
                .methodMatchString("close")
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
        return rules;
    }
}
