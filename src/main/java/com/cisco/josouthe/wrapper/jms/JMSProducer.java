package com.cisco.josouthe.wrapper.jms;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;

public class JMSProducer extends BaseWrapper {
    public JMSProducer(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }

    public void initMethods() {

    }
}
