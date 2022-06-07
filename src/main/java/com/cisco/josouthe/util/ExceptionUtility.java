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
}
