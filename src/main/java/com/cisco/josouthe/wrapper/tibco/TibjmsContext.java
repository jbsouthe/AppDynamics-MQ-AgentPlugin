package com.cisco.josouthe.wrapper.tibco;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;

import javax.naming.Reference;

public class TibjmsContext extends BaseWrapper {
    private IReflector getReference;
    private IReflector _connection;

    public TibjmsContext(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }

    @Override
    public void initMethods() {
        getReference = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getReference", true).build();
        _connection = interceptor.getNewReflectionBuilder().accessFieldValue("_connection", true).build();
    }

    public Reference getReference() {
        return (Reference) getReflectiveObject(getReference);
    }

    public TibjmsConnection getConnection(){
        Object connObject = getReflectiveObject(_connection);
        return new TibjmsConnection( this.interceptor, connObject, this.object);
    }
}
