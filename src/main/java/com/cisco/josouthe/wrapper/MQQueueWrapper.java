package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.ibm.mq.MQQueue;

public class MQQueueWrapper extends BaseWrapper{
    private IReflector close, getCurrentDepth, getMaximumDepth;
    private String name;
    private Integer options;

    public MQQueueWrapper(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject, String name, Integer options) {
        super(aGenericInterceptor, objectToWrap, parentObject);

        getCurrentDepth = makeInvokeInstanceMethodReflector("getCurrentDepth");
        getMaximumDepth = makeInvokeInstanceMethodReflector("getMaximumDepth");
        close = makeInvokeInstanceMethodReflector("close");
        this.name=name;
        this.options=options;
    }

    public String getName() { return this.name; }
    public Integer getOptions() { return this.options; }

    public int getCurrentDepth() {
        return (Integer) getReflectiveInteger(this.object, getCurrentDepth, 0);
    }

    public int getMaximumDepth() {
        return (Integer) getReflectiveInteger(this.object, getMaximumDepth, 0);
    }

    public void close() {
        getReflectiveObject(this.object, close);
    }
}
