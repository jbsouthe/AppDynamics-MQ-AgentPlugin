package com.cisco.josouthe.wrapper.jms;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.cisco.josouthe.wrapper.BaseWrapper;

public class JMSConsumer extends BaseWrapper {
    public JMSConsumer(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }
    public void initMethods() {}
}
