package com.cisco.josouthe.wrapper.jms;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;
import com.cisco.josouthe.wrapper.ibmmq.JMSConnectionImpl;

import java.util.HashSet;
import java.util.Set;

public class JMSContext extends BaseWrapper {
    private IReflector getMetaData, getJMSProviderName, internalJmsConnectionImpl;
    private Object metaDataObject;
    private Set<String> queueNames = new HashSet<>();
    private String connectionName;


    public JMSContext(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
        this.metaDataObject = getReflectiveObject(this.object, getMetaData);
    }

    public void initMethods() {
        getMetaData = makeInvokeInstanceMethodReflector("getMetaData");
        getJMSProviderName = makeInvokeInstanceMethodReflector("getJMSProviderName");
        internalJmsConnectionImpl = makeAccessFieldValueReflector("connection");
    }

    public String getJMSProviderName() {
        if( metaDataObject == null ) this.metaDataObject = getReflectiveObject(this.object, getMetaData);
        return getReflectiveString(metaDataObject, getJMSProviderName, "UNKNOWN-PROVIDER");
    }

    public void addQueue( String name ) {
        queueNames.add(name);
    }

    public Set<String> getQueueNames() { return queueNames; }

    public JMSConnectionImpl getConnection() {
        return new JMSConnectionImpl( this.interceptor, getReflectiveObject(this.object, internalJmsConnectionImpl), this.object);
    }
    public void setConnectionName( String name ) { this.connectionName=name; }
    public String getConnectionName() {
        if( this.connectionName == null ) {
            setConnectionName( getConnection().getHostPortString() );
        }
        return this.connectionName;
    }
}
