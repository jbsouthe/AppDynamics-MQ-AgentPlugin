package com.cisco.josouthe.wrapper.ibmmq;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.util.MQConstants;
import com.cisco.josouthe.wrapper.BaseWrapper;

import java.util.Arrays;

public class PCFMessage extends BaseWrapper {
    private IReflector addParameterIntArray, addParameterString, getIntParameterValue, constructor, getStringParameterValue, toString;

    public PCFMessage(ASDKPlugin aGenericInterceptor, Object parentObject, Integer creationOptions) {
        super(aGenericInterceptor, null, parentObject);
        try{
            this.object = constructor.execute( parentObject.getClass().getClassLoader(), null, new Object[] { creationOptions } );
            logger.debug("Initialized IBM MQ PCFMessage for monitoring, with reflection");
        } catch (Exception exception) {
            logger.debug(String.format("Error initializing reflective PCFMessage Exception: %s", exception.toString()),exception);
        }
    }

    public PCFMessage(ASDKPlugin aGenericInterceptor, Object message, Object parentObject ) {
        super(aGenericInterceptor, message, parentObject);
        initMethods();
        logger.debug(String.format("Initialized IBM MQ PCFMessage from object: %s", message.toString()));
    }

    public void initMethods() {
        constructor = interceptor.getNewReflectionBuilder().createObject("com.ibm.mq.headers.pcf.PCFMessage", new String[] { int.class.getCanonicalName() }).build();
        addParameterIntArray = makeInvokeInstanceMethodReflector("addParameter", int.class.getCanonicalName(), "[I" );
        addParameterString = makeInvokeInstanceMethodReflector("addParameter", int.class.getCanonicalName(), String.class.getCanonicalName() );
        getIntParameterValue = makeInvokeInstanceMethodReflector("getIntParameterValue", int.class.getCanonicalName());
        getStringParameterValue = makeInvokeInstanceMethodReflector( "getStringParameterValue", int.class.getCanonicalName());
        toString = makeInvokeInstanceMethodReflector("toString");
    }

    public void addParameter(int parameter, int[] values ) {
        getReflectiveObject(this.object, addParameterIntArray, parameter, values);
    }

    public void addParameter( int parameter, Integer[] integerValues ) {
        addParameter(parameter, Arrays.stream(integerValues).mapToInt(Integer::intValue).toArray());
    }

    public void addParameter( int parameter, String value ) {
        getReflectiveObject(this.object, addParameterString, parameter, value);
    }

    public Integer getIntParameterValue( String parameterName ) {
        return getIntParameterValue(MQConstants.getIntFromConstant(parameterName));
    }

    public Integer getIntParameterValue( int parameter ) {
        return (Integer) getReflectiveObject(this.object, getIntParameterValue, parameter);
    }

    public String getStringParameterValue( String parameterName ) {
        return getStringParameterValue( MQConstants.getIntFromConstant(parameterName));
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
