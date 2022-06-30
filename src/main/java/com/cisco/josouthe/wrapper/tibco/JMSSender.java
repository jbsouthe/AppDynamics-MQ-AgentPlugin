package com.cisco.josouthe.wrapper.tibco;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;

public class JMSSender extends BaseWrapper { //com.tibco.plugin.share.jms.impl.JMSSender

    IReflector getConfiguration;

    public JMSSender(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }

    @Override
    public void initMethods() {
        getConfiguration = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getConfiguration", true).build();
    }

    public SenderConfiguration getConfiguration() {
        Object senderConfiguration = getReflectiveObject(this.object, getConfiguration);
        return new SenderConfiguration(this.interceptor, senderConfiguration, this.object);
    }
}
