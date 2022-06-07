package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;

public class PCFMessageWrapper extends BaseWrapper{
    private IReflector addParameterArray, addParameterAtomic, getIntParameterValue, constructor, getStringParameterValue, toString;

    public PCFMessageWrapper(AGenericInterceptor aGenericInterceptor, Object parentObject, Integer creationOptions) {
        super(aGenericInterceptor, null, parentObject);
        try{
            constructor = interceptor.getNewReflectionBuilder().createObject("com.ibm.mq.headers.pcf.PCFMessage", new String[] { int.class.getCanonicalName() }).build();
            this.object = constructor.execute( parentObject.getClass().getClassLoader(), parentObject, new Object[] { creationOptions } );
            logger.info("Initialized IBM MQ PCFMessage for monitoring, with reflection");
        } catch (Exception exception) {
            logger.info(String.format("Error initializing reflective PCFMessage Exception: %s", exception.toString()),exception);
        }
        addParameterArray = makeInvokeInstanceMethodReflector("addParameter", int.class.getCanonicalName(), "[I" );
        addParameterAtomic = makeInvokeInstanceMethodReflector("addParameter", int.class.getCanonicalName(), int.class.getCanonicalName() );
        getIntParameterValue = makeInvokeInstanceMethodReflector("getIntParameterValue", int.class.getCanonicalName());
        getStringParameterValue = makeInvokeInstanceMethodReflector( "getStringParameterValue", int.class.getCanonicalName());
        toString = makeInvokeInstanceMethodReflector("toString");
    }

    public PCFMessageWrapper(AGenericInterceptor aGenericInterceptor, Object message ) {
        super(aGenericInterceptor, message, null);
    }

    public void addParameter( int parameter, int[] values ) {
        if( values.length == 1 ) addParameter(parameter, values[0]);
        getReflectiveObject(this.object, addParameterArray, parameter, values);
    }

    public void addParameter( int parameter, int value ) {
        getReflectiveObject(this.object, addParameterAtomic, parameter, value);
    }

    public Integer getIntParameterValue( int parameter ) {
        return (Integer) getReflectiveObject(this.object, getIntParameterValue, parameter);
    }

    public String getStringParameterValue( int parameter ) {
        return (String) getReflectiveObject(this.object, getStringParameterValue, parameter);
    }

    public String getQueueName() {
        return getStringParameterValue(2016); // this is CMQC.MQCA_Q_NAME
    }

    public String toString() {
        return (String) getReflectiveObject(this.object, toString);
    }
}
