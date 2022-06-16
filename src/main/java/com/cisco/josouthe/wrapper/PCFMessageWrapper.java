package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;

import java.util.Arrays;

public class PCFMessageWrapper extends BaseWrapper{
    private IReflector addParameterArray, addParameterAtomic, getIntParameterValue, constructor, getStringParameterValue, toString;

    public PCFMessageWrapper(ASDKPlugin aGenericInterceptor, Object parentObject, Integer creationOptions) {
        super(aGenericInterceptor, null, parentObject);
        try{
            this.object = constructor.execute( parentObject.getClass().getClassLoader(), null, new Object[] { creationOptions } );
            logger.debug("Initialized IBM MQ PCFMessage for monitoring, with reflection");
        } catch (Exception exception) {
            logger.debug(String.format("Error initializing reflective PCFMessage Exception: %s", exception.toString()),exception);
        }
    }

    public PCFMessageWrapper(ASDKPlugin aGenericInterceptor, Object message, Object parentObject ) {
        super(aGenericInterceptor, message, parentObject);
        initMethods();
        logger.debug(String.format("Initialized IBM MQ PCFMessage from object: %s", this.toString()));
    }

    protected void initMethods() {
        constructor = interceptor.getNewReflectionBuilder().createObject("com.ibm.mq.headers.pcf.PCFMessage", new String[] { int.class.getCanonicalName() }).build();
        addParameterArray = makeInvokeInstanceMethodReflector("addParameter", int.class.getCanonicalName(), "[I" );
        addParameterAtomic = makeInvokeInstanceMethodReflector("addParameter", int.class.getCanonicalName(), int.class.getCanonicalName() );
        getIntParameterValue = makeInvokeInstanceMethodReflector("getIntParameterValue", int.class.getCanonicalName());
        getStringParameterValue = makeInvokeInstanceMethodReflector( "getStringParameterValue", int.class.getCanonicalName());
        toString = makeInvokeInstanceMethodReflector("toString");
    }

    public void addParameter(int parameter, int[] values ) {
        getReflectiveObject(this.object, addParameterArray, parameter, values);
    }

    public void addParameter( int parameter, Integer[] integerValues ) {
        addParameter(parameter, Arrays.stream(integerValues).mapToInt(Integer::intValue).toArray());
    }

    @Deprecated //this causes the queue manager to crash under certain conditions! always send an int array
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
