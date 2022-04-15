package com.cisco.josouthe.wrapper;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.ReflectorException;
import org.bouncycastle.jcajce.provider.symmetric.ARC4;

public abstract class BaseWrapper {
    protected AGenericInterceptor interceptor;
    protected Object object, parentObject;

    public BaseWrapper(AGenericInterceptor aGenericInterceptor, Object objectToWrap, Object parentObject) {
        this.interceptor=aGenericInterceptor;
        this.object=objectToWrap;
        this.parentObject=parentObject;
    }

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
            interceptor.getLogger().info("Error in reflection call, exception: "+ e.getMessage(),e);
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
            interceptor.getLogger().info("Error in reflection call, exception: "+ e.getMessage(),e);
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
        } catch (ReflectorException e) {
            interceptor.getLogger().info("Error in reflection call, method: "+ method.getClass().getCanonicalName() +" object: "+ object.getClass().getCanonicalName() +" exception: "+ e.getMessage(),e);
        }
        return value;
    }
}
