package com.cisco.josouthe.monitor;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.cisco.josouthe.wrapper.JmsConnectionFactoryWrapper;

public class MQMonitor extends BaseJMSMonitor {

    public MQMonitor( AGenericInterceptor aGenericInterceptor, JmsConnectionFactoryWrapper connectionFactoryWrapper) {
        super(aGenericInterceptor, connectionFactoryWrapper);
    }

    @Override
    public void run() {
        interceptor.getLogger().info(String.format("IBM MQ Monitor run method called for %s", connectionFactoryWrapper.toString()));
        interceptor.getLogger().info(String.format("Queue names: %s", connectionFactoryWrapper.getQueueSet().toString()));
    }

    public String toString() { return connectionFactoryWrapper.toString(); }
}
