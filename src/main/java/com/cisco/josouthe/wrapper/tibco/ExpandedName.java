package com.cisco.josouthe.wrapper.tibco;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.ReflectorException;
import com.cisco.josouthe.wrapper.BaseWrapper;

public class ExpandedName extends BaseWrapper { //com.tibco.xml.data.primitive.ExpandedName
    IReflector makeNameOneArg, makeNameTwoArg;
    IReflector castAsString, getExpandedForm;

    public ExpandedName(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }

    public ExpandedName(ASDKPlugin aGenericInterceptor, String stringValue, Object parentObject) {
        super(aGenericInterceptor, null, parentObject);
        makeNameOneArg = this.interceptor.getNewReflectionBuilder().loadClass("com.tibco.xml.data.primitive.ExpandedName").invokeStaticMethod("makeName", true, new String[]{String.class.getCanonicalName()}).build();
        makeNameTwoArg = this.interceptor.getNewReflectionBuilder().loadClass("com.tibco.xml.data.primitive.ExpandedName").invokeStaticMethod("makeName", true, new String[]{String.class.getCanonicalName(), String.class.getCanonicalName()}).build();
        try {
            this.object = makeNameOneArg.execute(parentObject.getClass().getClassLoader(), null, new Object[]{ stringValue});
        } catch (ReflectorException e) {
            logger.info(String.format("Error creating ExpandedName wrapper for '%s'",stringValue));
        }
    }

    @Override
    public void initMethods() {
        castAsString = interceptor.getNewReflectionBuilder().invokeInstanceMethod("castAsString", true).build();
        getExpandedForm = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getExpandedForm", true).build();
    }
}
