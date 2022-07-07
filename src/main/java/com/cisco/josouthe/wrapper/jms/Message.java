package com.cisco.josouthe.wrapper.jms;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;

public class Message extends BaseWrapper { //https://docs.oracle.com/javaee/7/api/index.html?javax/jms/Message.html
    IReflector getJMSDestination;

    public Message(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }

    @Override
    public void initMethods() {
        getJMSDestination = makeInvokeInstanceMethodReflector("getJMSDestination");
    }

    public Destination getJMSDestination() {
        Object destinationObject = getReflectiveObject(getJMSDestination);
        return new Destination(this.interceptor, destinationObject, this);
    }


}
