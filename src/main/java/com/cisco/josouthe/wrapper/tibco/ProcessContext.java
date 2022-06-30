package com.cisco.josouthe.wrapper.tibco;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;

public class ProcessContext extends BaseWrapper {
    IReflector getService, getInvocationName, getFullCallName, getName, getId;

    public ProcessContext(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }

    @Override
    public void initMethods() {
        getService = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getService", true).build(); //String
        getInvocationName = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getInvocationName", true).build(); //String
        getFullCallName = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getFullCallName", true).build(); //String
        getName = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getName", true).build(); //String
        getId = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getId", true).build(); //long
    }

    public String getService() { return (String) getReflectiveObject(getService); }
    public String getInvocationName() { return (String) getReflectiveObject(getInvocationName); }
    public String getFullCallName() { return (String) getReflectiveObject(getFullCallName); }
    public String getName() { return (String) getReflectiveObject(getName); }
    public Long getId() { return (Long) getReflectiveObject(getId); }

    public String toString() {
        return String.format("Service: %s Invocation Name: %s Full Call Name: %s Name: %s Id: %d", getService(), getInvocationName(), getFullCallName(), getName(), getId());
    }
}
