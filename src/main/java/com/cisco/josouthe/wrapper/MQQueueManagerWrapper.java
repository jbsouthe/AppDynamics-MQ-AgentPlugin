package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.ReflectorException;

import java.util.Hashtable;

public class MQQueueManagerWrapper extends BaseWrapper{

    private IReflector accessQueue;

    public MQQueueManagerWrapper(AGenericInterceptor aGenericInterceptor, JmsConnectionFactoryWrapper jmsConnectionFactoryWrapper) {
        super(aGenericInterceptor, null, null);

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

        accessQueue= makeInvokeInstanceMethodReflector("accessQueue", String.class.getCanonicalName(), Integer.class.getCanonicalName());
    }

    public MQQueueWrapper accessQueue( String name ) {
        Object mqQueueObject = getReflectiveObject(this.object, accessQueue, name, 32);
        return new MQQueueWrapper(this.interceptor, mqQueueObject, this.object);
    }
}
