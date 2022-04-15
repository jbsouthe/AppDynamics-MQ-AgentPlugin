package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;

public class MQQueueWrapper extends BaseWrapper{
    IReflector close, getCurrentDepth;
    public MQQueueWrapper(AGenericInterceptor aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);

        getCurrentDepth = makeInvokeInstanceMethodReflector("getCurrentDepth");
        close = makeInvokeInstanceMethodReflector("close");
    }

    public int getCurrentDepth() {
        return (Integer) getReflectiveInteger(this.object, getCurrentDepth, -1);
    }

    public void close() {
        getReflectiveObject(this.object, close);
    }
}
