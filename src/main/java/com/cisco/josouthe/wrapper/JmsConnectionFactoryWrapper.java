package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;

import java.util.HashSet;
import java.util.Set;

public class JmsConnectionFactoryWrapper extends BaseWrapper{

    private IReflector getStringProperty, getIntProperty, getBooleanProperty;
    private IReflector createContext;
    private Set<JmsContextWrapper> contextSet = new HashSet<>();
    private Set<String> queueSet = new HashSet<>();

    public JmsConnectionFactoryWrapper(AGenericInterceptor aGenericInterceptor, Object objectToWrap) {
        super(aGenericInterceptor, objectToWrap, null);

        getStringProperty = makeInvokeInstanceMethodReflector("getStringProperty", String.class.getCanonicalName() );
        getIntProperty = makeInvokeInstanceMethodReflector("getIntProperty", String.class.getCanonicalName());
        getBooleanProperty = makeInvokeInstanceMethodReflector("getBooleanProperty", String.class.getCanonicalName());

        createContext = makeInvokeInstanceMethodReflector("createContext");
    }

    public void addContext( JmsContextWrapper jmsContextWrapper ) { contextSet.add(jmsContextWrapper); }
    public Set<JmsContextWrapper> getContextSet() { return contextSet; }

    public String getStringProperty( String name ) {
        return (String) getReflectiveObject(this.object, getStringProperty, name);
    }

    public Integer getIntProperty( String name ) {
        return (Integer) getReflectiveObject(this.object, getIntProperty, name);
    }

    public Boolean getBooleanProperty( String name ) {
        return (Boolean) getReflectiveObject(this.object, getBooleanProperty, name);
    }

    public Object createContext() {
        return getReflectiveObject(this.object, createContext);
    }

    public boolean containsContext(JmsContextWrapper context) {
        return contextSet.contains(context);
    }

    public void removeContext(JmsContextWrapper context) {
        contextSet.remove(context);
    }

    public String getHostPortString() {
        return String.format("%s:%d", getStringProperty("XMSC_WMQ_HOST_NAME"), getIntProperty("XMSC_WMQ_PORT") );
    }

    public void addQueue( String name ) { this.queueSet.add(name); }
    public Set<String> getQueueSet() { return queueSet; }
}
