package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class PCFMessageWrapper extends BaseWrapper{
    private IReflector addParameter, getIntParameterValue, constructor;

    public PCFMessageWrapper(AGenericInterceptor aGenericInterceptor, Object parentObject, Integer creationOptions) {
        super(aGenericInterceptor, null, parentObject);
        try{
            /*
            Class<?> pcfMessageClass = parentObject.getClass().getClassLoader().loadClass("com.ibm.mq.headers.pcf.PCFMessage");
            Constructor constructor = pcfMessageClass.getConstructor( int.class );
            this.object = constructor.newInstance(creationOptions);
             */
            constructor = interceptor.getNewReflectionBuilder().createObject("com.ibm.mq.headers.pcf.PCFMessage", new String[] { int.class.getCanonicalName() }).build();
            this.object = constructor.execute( parentObject.getClass().getClassLoader(), parentObject, new Object[] { creationOptions } );
            logger.info("Initialized IBM MQ PCFMessage for monitoring, with reflection");
        } catch (Exception exception) {
            logger.info(String.format("Error initializing reflective PCFMessage Exception: %s", exception.toString()),exception);
        }
        addParameter = makeInvokeInstanceMethodReflector("addParameter", int.class.getCanonicalName(), int[].class.getCanonicalName() );
        getIntParameterValue = makeInvokeInstanceMethodReflector("getIntParameterValue", int.class.getCanonicalName());
    }

    public PCFMessageWrapper(AGenericInterceptor aGenericInterceptor, Object message ) {
        super(aGenericInterceptor, message, null);
    }

    public void addParameter( int parameter, int[] values ) {
        getReflectiveObject(this.object, addParameter, parameter, values);
    }

    public Integer getIntParameterValue( int parameter ) {
        return (Integer) getReflectiveObject(this.object, getIntParameterValue, parameter);
    }
}
