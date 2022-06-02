package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.ReflectorException;
import com.cisco.josouthe.exception.ExceptionUtility;
import com.cisco.josouthe.json.AuthenticationOverrideInfo;

import java.util.Hashtable;

public class MQQueueManagerWrapper extends BaseWrapper{

    private String hostname, userID, password, queue, channel;
    private Integer port;
    private IReflector accessQueue, accessQueueWithOptions;

    public MQQueueManagerWrapper(AGenericInterceptor aGenericInterceptor, JmsConnectionFactoryWrapper jmsConnectionFactoryWrapper, AuthenticationOverrideInfo authenticationOverrideInfo) {
        super(aGenericInterceptor, null, null);
        hostname = jmsConnectionFactoryWrapper.getStringProperty("XMSC_WMQ_HOST_NAME");
        port = jmsConnectionFactoryWrapper.getIntProperty("XMSC_WMQ_PORT");
        queue = jmsConnectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER");
        channel = jmsConnectionFactoryWrapper.getStringProperty("XMSC_WMQ_CHANNEL");
        if( authenticationOverrideInfo != null && authenticationOverrideInfo.channel != null ) channel=authenticationOverrideInfo.channel;
        userID = jmsConnectionFactoryWrapper.getStringProperty("XMSC_USERID");
        if( authenticationOverrideInfo != null && authenticationOverrideInfo.userID != null ) userID=authenticationOverrideInfo.userID;
        password = jmsConnectionFactoryWrapper.getStringProperty("XMSC_PASSWORD");
        if( authenticationOverrideInfo != null && authenticationOverrideInfo.password != null ) password=authenticationOverrideInfo.password;
        init(jmsConnectionFactoryWrapper.getObject().getClass().getClassLoader());
        accessQueue= makeInvokeInstanceMethodReflector("accessQueue", String.class.getCanonicalName());
        accessQueueWithOptions= makeInvokeInstanceMethodReflector("accessQueue", String.class.getCanonicalName(), Integer.class.getCanonicalName());
    }

    public MQQueueWrapper accessQueue( String name ) {
        Object mqQueueObject = getReflectiveObject(this.object, accessQueue, name);
        return new MQQueueWrapper(this.interceptor, mqQueueObject, this.object, name, null);
    }

    public MQQueueWrapper accessQueue(String name, int options) {
        Object mqQueueObject = getReflectiveObject(this.object, accessQueueWithOptions, name, options);
        return new MQQueueWrapper(this.interceptor, mqQueueObject, this.object, name, options);
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
            logger.info(String.format("jms queue '%s' connection properties: %s", queue, connectionProperties));
            IReflector constructor = interceptor.getNewReflectionBuilder()
                    .createObject("com.ibm.mq.MQQueueManager", String.class.getCanonicalName(), Hashtable.class.getCanonicalName() )
                    .build();
            this.object = constructor.execute( classLoader, null, new Object[] { queue, connectionProperties });
            logger.info("Initialized IBM MQ Queue Manager for monitoring, with reflection");
        } catch (Exception exception) {
            Throwable rootCause = ExceptionUtility.getRootCause(exception);
            logger.info(String.format("Error initializing reflective queue manager for %s Exception: %s",queue, rootCause.toString()),rootCause);
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
}
