package com.cisco.josouthe.wrapper.ibmmq;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MQSession extends BaseWrapper {
    private static Pattern hostPortPattern = Pattern.compile("(?<hostname>.*)\\((?<port>\\d+)\\)");
    private IReflector getStringProperty, getIntProperty, getBooleanProperty, getPropertyNames, getObjectProperty, toString, getConnection;

    public MQSession(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }

    public void initMethods() {
        getStringProperty = makeInvokeInstanceMethodReflector("getStringProperty", String.class.getCanonicalName() );
        getIntProperty = makeInvokeInstanceMethodReflector("getIntProperty", String.class.getCanonicalName());
        getBooleanProperty = makeInvokeInstanceMethodReflector("getBooleanProperty", String.class.getCanonicalName());
        getPropertyNames = makeInvokeInstanceMethodReflector("getPropertyNames"); //returns Enumeration<String>
        getObjectProperty = makeInvokeInstanceMethodReflector("getObjectProperty", String.class.getCanonicalName());
        toString = makeInvokeInstanceMethodReflector("toString");
        getConnection = makeInvokeInstanceMethodReflector("getConnection");
    }

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


    public String toString() {
        return (String) getReflectiveObject(this.object, toString);
    }

    public String getHostPortString() {
        /*
        Enumeration<String> enumeration = (Enumeration<String>) getReflectiveObject(getPropertyNames);
        logger.debug("Entering MQSession.getHostPortString() looking for hostname and port:");
        while( enumeration.hasMoreElements() ) {
            String key = enumeration.nextElement();
            logger.debug(String.format("property: %s = %s", key, getStringProperty(key)));
        } */
        Matcher matcher = hostPortPattern.matcher(getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST_INT"));
        if( matcher.matches() ) {
            return String.format("%s:%s", matcher.group("hostname"), matcher.group("port"));
        }
        return "";
    }

    public String getHostname() {
        Matcher matcher = hostPortPattern.matcher( getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST_INT") );
        if( matcher.matches() ) {
            return matcher.group("hostname");
        }
        return "";
    }

    public Integer getPort() {
        Matcher matcher = hostPortPattern.matcher( getStringProperty("XMSC_WMQ_CONNECTION_NAME_LIST_INT") );
        if( matcher.matches() ) {
            return Integer.parseInt(matcher.group("port"));
        }
        return 4401;
    }

    public String getChannel() {
        return getStringProperty("XMSC_WMQ_CHANNEL");
    }

    public String getUserID() {
        return getStringProperty("XMSC_USERID");
    }

    public String getPassword() {
        return getStringProperty("XMSC_PASSWORD");
    }

    public String getQueueManagerName() {
        return getStringProperty("XMSC_WMQ_QUEUE_MANAGER");
    }

}
