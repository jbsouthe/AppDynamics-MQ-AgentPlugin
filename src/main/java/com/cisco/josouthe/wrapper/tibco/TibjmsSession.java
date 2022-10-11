package com.cisco.josouthe.wrapper.tibco;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;

public class TibjmsSession extends BaseWrapper {
    private IReflector _connection;

    public TibjmsSession(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }
    @Override
    public void initMethods() {
        _connection = interceptor.getNewReflectionBuilder().accessFieldValue("_connection", true).build();
    }

    public TibjmsConnection getConnection(){
        Object connObject = getReflectiveObject(_connection);
        return new TibjmsConnection( this.interceptor, connObject, this.object);
    }
}
