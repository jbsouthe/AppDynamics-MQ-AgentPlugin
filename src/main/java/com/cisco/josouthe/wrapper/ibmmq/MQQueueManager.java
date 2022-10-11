package com.cisco.josouthe.wrapper.ibmmq;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.util.ExceptionUtility;
import com.cisco.josouthe.json.AuthenticationOverrideInfo;
import com.cisco.josouthe.wrapper.BaseWrapper;
import com.cisco.josouthe.wrapper.jms.JmsConnectionFactory;

import java.util.Hashtable;
import java.util.Map;

public class MQQueueManager extends BaseWrapper {

    private String hostname, userID, password, queue, channel;
    private Integer port;
    private IReflector constructor, accessQueue, accessQueueWithOptions;

    public MQQueueManager(ASDKPlugin asdkPlugin, String qMgrName, Map<String,Object> connectionPropertiesMap ) { //for Junit
        super(asdkPlugin,null, null);
        this.queue = qMgrName;
        this.hostname = (String) connectionPropertiesMap.get("hostname");
        this.port = (Integer) connectionPropertiesMap.get("port");
        this.channel = (String) connectionPropertiesMap.get("channel");
        this.userID = (String) connectionPropertiesMap.get("userID");
        this.password = (String) connectionPropertiesMap.get("password");
        init( this.getClass().getClassLoader());
    }

    public MQQueueManager(ASDKPlugin aGenericInterceptor, JmsConnectionFactory jmsConnectionFactory, AuthenticationOverrideInfo authenticationOverrideInfo) {
        super(aGenericInterceptor, null, null);
        hostname = jmsConnectionFactory.getStringProperty("XMSC_WMQ_HOST_NAME");
        port = jmsConnectionFactory.getIntProperty("XMSC_WMQ_PORT");
        queue = jmsConnectionFactory.getStringProperty("XMSC_WMQ_QUEUE_MANAGER");
        channel = jmsConnectionFactory.getStringProperty("XMSC_WMQ_CHANNEL");
        if( authenticationOverrideInfo != null && authenticationOverrideInfo.channel != null ) channel=authenticationOverrideInfo.channel;
        userID = jmsConnectionFactory.getStringProperty("XMSC_USERID");
        if( authenticationOverrideInfo != null && authenticationOverrideInfo.userID != null ) userID=authenticationOverrideInfo.userID;
        password = jmsConnectionFactory.getStringProperty("XMSC_PASSWORD");
        if( authenticationOverrideInfo != null && authenticationOverrideInfo.password != null ) password=authenticationOverrideInfo.password;
        init(jmsConnectionFactory.getObject().getClass().getClassLoader());
    }

    public MQQueueManager(ASDKPlugin aGenericInterceptor, MQSession mqSession, AuthenticationOverrideInfo authenticationOverrideInfo) {
        super(aGenericInterceptor, null, null);
        hostname = mqSession.getHostname();
        port = mqSession.getPort();
        queue = mqSession.getQueueManagerName();
        channel = mqSession.getChannel();
        if( authenticationOverrideInfo != null && authenticationOverrideInfo.channel != null ) channel=authenticationOverrideInfo.channel;
        userID = mqSession.getUserID();
        if( authenticationOverrideInfo != null && authenticationOverrideInfo.userID != null ) userID=authenticationOverrideInfo.userID;
        password = mqSession.getPassword();
        if( authenticationOverrideInfo != null && authenticationOverrideInfo.password != null ) password=authenticationOverrideInfo.password;
        init(mqSession.getObject().getClass().getClassLoader());
    }
    public MQQueueManager(ASDKPlugin aGenericInterceptor, Object qMgrObject, String qMgrName, Hashtable properties, AuthenticationOverrideInfo authenticationOverrideInfo ) {
        super(aGenericInterceptor, null, null);
        queue= qMgrName;
        hostname = String.valueOf(properties.get("hostname"));
        port = Integer.valueOf(String.valueOf(properties.get("port")));
        channel = String.valueOf(properties.get("channel"));
        if( authenticationOverrideInfo != null && authenticationOverrideInfo.channel != null ) channel=authenticationOverrideInfo.channel;
        userID = String.valueOf(properties.get("userID"));
        if( authenticationOverrideInfo != null && authenticationOverrideInfo.userID != null ) userID=authenticationOverrideInfo.userID;
        password = String.valueOf(properties.get("password"));
        if( authenticationOverrideInfo != null && authenticationOverrideInfo.password != null ) password=authenticationOverrideInfo.password;
        init(qMgrObject.getClass().getClassLoader());
    }

    public void initMethods() {
        constructor = interceptor.getNewReflectionBuilder()
                .createObject("com.ibm.mq.MQQueueManager", String.class.getCanonicalName(), Hashtable.class.getCanonicalName() )
                .build();
        accessQueue= makeInvokeInstanceMethodReflector("accessQueue", String.class.getCanonicalName());
        accessQueueWithOptions= makeInvokeInstanceMethodReflector("accessQueue", String.class.getCanonicalName(), int.class.getCanonicalName());
    }

    public MQQueue accessQueue(String name ) {
        logger.debug(String.format("accessQueue('%s')", name));
        Object mqQueueObject = getReflectiveObject(this.object, accessQueue, name);
        return new MQQueue(this.interceptor, mqQueueObject, this.object, name, null);
    }

    public MQQueue accessQueue(String name, int options) {
        logger.debug(String.format("accessQueue('%s', %d)", name, options));
        Object mqQueueObject = getReflectiveObject(this.object, accessQueueWithOptions, name, options);
        return new MQQueue(this.interceptor, mqQueueObject, this.object, name, options);
    }

    public void init( ClassLoader classLoader ) {
        try{
            Hashtable connectionProperties = new Hashtable<String,Object>(); //jmsConnectionFactoryWrapper.getPropertyHashTable("XMSC_WMQ_HOST_NAME", "XMSC_WMQ_PORT", "XMSC_WMQ_CHANNEL", "XMSC_USERID", "XMSC_PASSWORD");
            connectionProperties.put("transport", "MQSeries Client");
            connectionProperties.put("hostname", hostname);
            connectionProperties.put("port", port);
            connectionProperties.put("channel", channel);
            connectionProperties.put("userID", userID);
            connectionProperties.put("password", password);
            logger.debug(String.format("jms queue '%s' connection properties: %s", queue, connectionProperties));
            this.object = constructor.execute( classLoader, null, new Object[] { queue, connectionProperties });
            logger.debug("Initialized IBM MQ Queue Manager for monitoring, with reflection");
        } catch (Exception exception) {
            Throwable rootCause = ExceptionUtility.getRootCause(exception);
            logger.debug(String.format("Error initializing reflective queue manager for %s Exception: %s",queue, rootCause.toString()),rootCause);
        }
    }

    public String getHostname() { return hostname; }
    public void setHostname( String s ) { hostname=s; }
    public String getUserID() { return userID; }
    public void setUserID( String s ) { userID=s; }
    public void setPassword( String s ) { password=s; }
    public Integer getPort() { return port; }
    public void setPort( Integer i ) { port=i; }
    public String getChannel() { return channel; }
    public void setChannel( String s ) { channel=s; }
    public String getName() { return queue; }
    public String getConnectionKey() { return String.format("IBM MQ JMS Provider:%s:%d", hostname, port); }
}
