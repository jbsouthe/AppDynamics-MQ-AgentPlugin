package com.cisco.josouthe.util;

import com.appdynamics.instrumentation.sdk.toolbox.reflection.ReflectorException;

public class ExceptionUtility {
    public static Throwable getRootCause(Exception exception ) {
        if( exception instanceof ReflectorException ) {
            if( exception.getCause() instanceof java.lang.reflect.InvocationTargetException )
                return exception.getCause().getCause();
        }
        return exception;
    }

    public static boolean exceptionMatches( Exception exception, String matchString ) {
        if( exception == null || matchString == null ) return false;
        if( exception.getCause() instanceof java.lang.reflect.InvocationTargetException ) {
            Throwable rootException = exception.getCause();
            if( rootException != null ) {
                rootException = rootException.getCause();
                if( rootException != null && "com.ibm.mq.headers.MQExceptionWrapper".equals(rootException.getClass().getCanonicalName()) ) {
                    return matchString.equals(rootException.getMessage());
                }
            }
        }
        return false;
    }
}
