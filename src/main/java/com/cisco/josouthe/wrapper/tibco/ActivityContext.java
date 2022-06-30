package com.cisco.josouthe.wrapper.tibco;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;

public class ActivityContext extends BaseWrapper {
    private IReflector getTraceSource, getName, getDescription, getProcessModelName;

    public ActivityContext(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }

    @Override
    public void initMethods() {
        getTraceSource = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getTraceSource", true).build(); //String
        getName = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getName", true).build(); //String
        getDescription = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getDescription", true).build(); //String
        getProcessModelName = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getProcessModelName", true).build(); //String
    }

    public String getTraceSource() { return (String) getReflectiveObject(getTraceSource); }
    public String getName() { return (String) getReflectiveObject(getName); }
    public String getDescription() { return (String) getReflectiveObject(getDescription); }
    public String getProcessModelName() { return (String) getReflectiveObject(getProcessModelName); }

    public String toString() {
        return String.format("Name: %s Description: %s Process Model Name: %s Trace Source: %s", getName(), getDescription(), getProcessModelName(), getTraceSource());
    }
}
