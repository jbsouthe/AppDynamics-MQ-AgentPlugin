package com.cisco.josouthe.wrapper.jms;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;

public class JmsConnectionImplWrapper extends BaseWrapper {
    private IReflector getStringProperty, getIntProperty, getBooleanProperty, getPropertyNames, getObjectProperty;

    public JmsConnectionImplWrapper(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }

    public void initMethods() {
        getStringProperty = makeInvokeInstanceMethodReflector("getStringProperty", String.class.getCanonicalName() );
        getIntProperty = makeInvokeInstanceMethodReflector("getIntProperty", String.class.getCanonicalName());
        getBooleanProperty = makeInvokeInstanceMethodReflector("getBooleanProperty", String.class.getCanonicalName());
        getPropertyNames = makeInvokeInstanceMethodReflector("getPropertyNames"); //returns Enumeration<String>
        getObjectProperty = makeInvokeInstanceMethodReflector("getObjectProperty", String.class.getCanonicalName());
    }

    public String getStringProperty( String name ) {
        return (String) getReflectiveObject(this.object, getStringProperty, name);
    }

    public Integer getIntProperty( String name ) {
        return (Integer) getReflectiveObject(this.object, getIntProperty, name);
    }

    public Boolean getBooleanProperty( String name ) {
        return (Boolean) getReflectiveObject(this.object, getBooleanProperty, name);
    }

    public Object getObjectProperty( String name ) {
        return getReflectiveObject(this.object, getObjectProperty, name);
    }

    public String getHostPortString() {
        return String.format("%s:%d", getStringProperty("XMSC_WMQ_HOST_NAME"), getIntProperty("XMSC_WMQ_PORT") );
    }
}
