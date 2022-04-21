package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.ReflectorException;

import java.lang.reflect.Constructor;
import java.util.Hashtable;

public class MQQueueManagerWrapper extends BaseWrapper{

    private IReflector accessQueue, accessQueueWithOptions;

    public MQQueueManagerWrapper(AGenericInterceptor aGenericInterceptor, JmsConnectionFactoryWrapper jmsConnectionFactoryWrapper) {
        super(aGenericInterceptor, null, null);

        /*
        IReflector constructor = interceptor.getNewReflectionBuilder()
                .loadClass("com.ibm.mq.MQQueueManager")
                .createObject("com.ibm.mq.MQQueueManager", String.class.getCanonicalName(), Hashtable.class.getCanonicalName())
                .build();

        Hashtable environment = jmsConnectionFactoryWrapper.getPropertyHashTable();
        interceptor.getLogger().info(String.format("Created env hashtable with %d properties", environment.size()));
        try { //TODO FIX THIS "WARN JavaAgent - Exception caught: java.lang.NoClassDefFoundError: com/ibm/mq/MQEnvironment"
            this.object = constructor.execute( jmsConnectionFactoryWrapper.object.getClass().getClassLoader()
                    , null
                    , new Object[]{
                        jmsConnectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER")
                        ,environment
                    }
            );
            interceptor.getLogger().info(String.format("Made it"));
        } catch (ReflectorException e) {
            interceptor.getLogger().info("ERROR Creating MQQueueManager, exception: "+ e.toString(), e);
        } catch (RuntimeException runtimeException ) {
            interceptor.getLogger().info("Runtime Exception Creating MQQueueManager, exception: "+ runtimeException.toString(), runtimeException);
        }

         */

        try{
            Class<?> clazz = jmsConnectionFactoryWrapper.getObject().getClass().getClassLoader().loadClass("com.ibm.mq.MQQueueManager");
            Constructor constructor = clazz.getConstructor( String.class, Hashtable.class);
            Hashtable env = jmsConnectionFactoryWrapper.getPropertyHashTable("XMSC_WMQ_HOST_NAME", "XMSC_WMQ_PORT", "XMSC_WMQ_CHANNEL", "XMSC_USERID", "XMSC_PASSWORD");
            this.object = constructor.newInstance(jmsConnectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"), env);
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
