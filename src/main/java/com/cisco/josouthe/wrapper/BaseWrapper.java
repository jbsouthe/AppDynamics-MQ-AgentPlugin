package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.logging.ISDKLogger;
import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.ReflectorException;
import com.cisco.josouthe.exception.MQErrorException;
import com.cisco.josouthe.util.ExceptionUtility;
import org.bouncycastle.jcajce.provider.symmetric.ARC4;

public abstract class BaseWrapper {
    protected ASDKPlugin interceptor;
    protected ISDKLogger logger;
    protected Object object, parentObject;

    public BaseWrapper(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        if( aGenericInterceptor != null ) {
            this.interceptor = aGenericInterceptor;
            this.logger = aGenericInterceptor.getLogger();
        }
        this.object=objectToWrap;
        this.parentObject=parentObject;
        initMethods();
    }

    abstract void initMethods();

    public Boolean matches( Object otherObject ) {
        if( otherObject == null ) return false;
        return otherObject == this.object;
    }

    public Object getObject() { return object; }
    public Object getParentObject() { return parentObject; }

    public String toString() { return String.valueOf(this.object); }

    protected IReflector makeInvokeInstanceMethodReflector(String method, String...args ) {
        if( args.length > 0 ) return interceptor.getNewReflectionBuilder().invokeInstanceMethod( method, true, args).build();
        return interceptor.getNewReflectionBuilder().invokeInstanceMethod( method, true).build();
    }

    protected IReflector makeAccessFieldValueReflector(String field ) {
        return interceptor.getNewReflectionBuilder().accessFieldValue( field, true).build();
    }

    protected String getReflectiveString(Object object, IReflector method, String defaultString) {
        String value = defaultString;
        if( object == null || method == null ) return defaultString;
        try{
            value = (String) method.execute(object.getClass().getClassLoader(), object);
            if( value == null ) return defaultString;
        } catch (ReflectorException e) {
            logException(e, object, method);
        }
        return value;
    }

    protected Integer getReflectiveInteger(Object object, IReflector method, Integer defaultInteger) {
        Integer value = defaultInteger;
        if( object == null || method == null ) return defaultInteger;
        try{
            value = (Integer) method.execute(object.getClass().getClassLoader(), object);
            if( value == null ) return defaultInteger;
        } catch (ReflectorException e) {
            logException(e, object, method);
        }
        return value;
    }

    protected Long getReflectiveLong( Object object, IReflector method ) {
        if( object == null || method == null ) return null;
        Object rawValue = getReflectiveObject( object, method );
        if( rawValue instanceof Long  ) return (Long) rawValue;
        if( rawValue instanceof Integer ) return ((Integer) rawValue).longValue();
        if( rawValue instanceof Double ) return ((Double)rawValue).longValue();
        if( rawValue instanceof Number ) return ((Number)rawValue).longValue();
        return null;
    }

    protected Object getReflectiveObject(Object object, IReflector method, Object... args) {
        Object value = null;
        if( object == null || method == null ) return value;
        try{
            if( args.length > 0 ) {
                value = method.execute(object.getClass().getClassLoader(), object, args);
            } else {
                value = method.execute(object.getClass().getClassLoader(), object);
            }
        } catch (ReflectorException exception) {
            logException(exception, object, method);
        }
        return value;
    }

    private void logException( ReflectorException reflectorException, Object object, IReflector method ) {
        Throwable sourceException = ExceptionUtility.getRootCause(reflectorException);
        MQErrorException mqErrorException = ExceptionUtility.processException(reflectorException);
        if( mqErrorException == null ) {
            logger.info(String.format("Error in reflection call, method: %s object: %s exception: %s", method.getClass().getCanonicalName(), object.getClass().getCanonicalName(), sourceException.toString()),sourceException);
        }else {
            logger.info(String.format("MQError in reflection call, method: %s object: %s error: %s", method.getClass().getCanonicalName(), object.getClass().getCanonicalName(), mqErrorException.getMessage()),mqErrorException);
        }
    }
}
