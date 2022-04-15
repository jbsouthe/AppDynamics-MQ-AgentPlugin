package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;

import java.util.HashSet;
import java.util.Set;

public class JmsContextWrapper extends BaseWrapper{
    private IReflector getMetaData, getJMSProviderName;
    private IReflector internalJmsConnectionImpl;
    private Object metaDataObject;
    private Set<String> queueNames = new HashSet<>();
    private String connectionName;


    public JmsContextWrapper(AGenericInterceptor aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
        getMetaData = makeInvokeInstanceMethodReflector("getMetaData");
        getJMSProviderName = makeInvokeInstanceMethodReflector("getJMSProviderName");
        internalJmsConnectionImpl = makeAccessFieldValueReflector("connection");
        this.metaDataObject = getReflectiveObject(this.object, getMetaData);
    }

    public String getJMSProviderName() {
        if( metaDataObject == null ) this.metaDataObject = getReflectiveObject(this.object, getMetaData);
        return (String) getReflectiveObject(metaDataObject, getJMSProviderName);
    }

    public void addQueue( String name ) {
        queueNames.add(name);
    }

    public Set<String> getQueueNames() { return queueNames; }

    public JmsConnectionImplWrapper getConnection() {
        return new JmsConnectionImplWrapper( this.interceptor, getReflectiveObject(this.object, internalJmsConnectionImpl), this.object);
    }
    public void setConnectionName( String name ) { this.connectionName=name; }
    public String getConnectionName() {
        if( this.connectionName == null ) {
            setConnectionName( getConnection().getHostPortString() );
        }
        return this.connectionName;
    }
}
