package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import java.util.Hashtable;

public class MQQueueManagerWrapper extends BaseWrapper{

    private IReflector accessQueue, accessQueueWithOptions;

    public MQQueueManagerWrapper(AGenericInterceptor aGenericInterceptor, JmsConnectionFactoryWrapper jmsConnectionFactoryWrapper) {
        super(aGenericInterceptor, null, null);


        try{
            Hashtable connectionProperties = new Hashtable<String,Object>(); //jmsConnectionFactoryWrapper.getPropertyHashTable("XMSC_WMQ_HOST_NAME", "XMSC_WMQ_PORT", "XMSC_WMQ_CHANNEL", "XMSC_USERID", "XMSC_PASSWORD");
            connectionProperties.put("transport", "MQSeries Client");
            connectionProperties.put("hostname", jmsConnectionFactoryWrapper.getObjectProperty("XMSC_WMQ_HOST_NAME"));
            connectionProperties.put("port", jmsConnectionFactoryWrapper.getIntProperty("XMSC_WMQ_PORT"));
            connectionProperties.put("channel", jmsConnectionFactoryWrapper.getObjectProperty("XMSC_WMQ_CHANNEL"));
            connectionProperties.put("userID", jmsConnectionFactoryWrapper.getObjectProperty("XMSC_USERID"));
            connectionProperties.put("password", jmsConnectionFactoryWrapper.getObjectProperty("XMSC_PASSWORD"));
            logger.info(String.format("jms queue '%s' connection properties: %s", jmsConnectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"), connectionProperties));
            IReflector constructor = interceptor.getNewReflectionBuilder()
                    .createObject("com.ibm.mq.MQQueueManager", String.class.getCanonicalName(), Hashtable.class.getCanonicalName() )
                    .build();
            this.object = constructor.execute( jmsConnectionFactoryWrapper.getObject().getClass().getClassLoader(), null,
                    new Object[] { jmsConnectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"), connectionProperties });
            logger.info("Initialized IBM MQ Queue Manager for monitoring, with reflection");
        } catch (Exception exception) {
            logger.info(String.format("Error initializing reflective queue manager for %s Exception: %s",jmsConnectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"), exception.toString()),exception);
        }

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
}
