package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;

public class JmsConsumerWrapper extends BaseWrapper{
    public JmsConsumerWrapper(AGenericInterceptor aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }
}
