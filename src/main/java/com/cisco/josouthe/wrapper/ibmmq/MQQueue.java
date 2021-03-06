package com.cisco.josouthe.wrapper.ibmmq;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;

public class MQQueue extends BaseWrapper {
    private IReflector close, getCurrentDepth, getMaximumDepth;
    private String name;
    private Integer options;

    public MQQueue(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject, String name, Integer options) {
        super(aGenericInterceptor, objectToWrap, parentObject);
        this.name=name;
        this.options=options;
    }

    public void initMethods() {
        getCurrentDepth = makeInvokeInstanceMethodReflector("getCurrentDepth");
        getMaximumDepth = makeInvokeInstanceMethodReflector("getMaximumDepth");
        close = makeInvokeInstanceMethodReflector("close");
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
