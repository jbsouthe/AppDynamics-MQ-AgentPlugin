package com.cisco.josouthe.wrapper.jms;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class JmsConnectionFactoryWrapper extends BaseWrapper {

    private IReflector getStringProperty, getIntProperty, getBooleanProperty, getPropertyNames, getObjectProperty;
    private IReflector createContext;
    private Set<JmsContextWrapper> contextSet = new HashSet<>();
    private Set<String> queueSet = new HashSet<>();

    public JmsConnectionFactoryWrapper(ASDKPlugin aGenericInterceptor, Object objectToWrap) {
        super(aGenericInterceptor, objectToWrap, null);
    }

    public void initMethods() {
        getStringProperty = makeInvokeInstanceMethodReflector("getStringProperty", String.class.getCanonicalName() );
        getIntProperty = makeInvokeInstanceMethodReflector("getIntProperty", String.class.getCanonicalName());
        getBooleanProperty = makeInvokeInstanceMethodReflector("getBooleanProperty", String.class.getCanonicalName());
        getPropertyNames = makeInvokeInstanceMethodReflector("getPropertyNames"); //returns Enumeration<String>
        getObjectProperty = makeInvokeInstanceMethodReflector("getObjectProperty", String.class.getCanonicalName());
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

    public Object getObjectProperty( String name ) {
        return getReflectiveObject(this.object, getObjectProperty, name);
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

    public Hashtable getPropertyHashTable() {
        Enumeration<String> enumeration = (Enumeration<String>) getReflectiveObject(this.object, getPropertyNames);
        if( enumeration == null ) return null;
        Hashtable hashtable = new Hashtable();
        while( enumeration.hasMoreElements() ) {
            String propertyName = enumeration.nextElement();
            Object value = getObjectProperty(propertyName);
            if( value != null ) {
                hashtable.put(propertyName, value);
            }
            this.interceptor.getLogger().debug(String.format("Property: %s=%s",propertyName,value));
        }
        return hashtable;
    }

    public Hashtable getPropertyHashTable(String... propertyNames) {
        Hashtable hashtable = new Hashtable();
        for( String propertyName : propertyNames ) {
            hashtable.put(propertyName, getObjectProperty(propertyName));
        }
        return hashtable;
    }

    public void addQueue( String name ) { this.queueSet.add(name); }
    public Set<String> getQueueSet() { return queueSet; }
}
