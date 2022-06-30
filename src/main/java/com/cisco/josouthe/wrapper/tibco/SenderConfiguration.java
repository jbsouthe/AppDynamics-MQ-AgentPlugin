package com.cisco.josouthe.wrapper.tibco;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;

public class SenderConfiguration extends BaseWrapper { //com.tibco.plugin.share.jms.SenderConfiguration
    private IReflector getDeliveryMode, getPriority, getMessageType, isQueue,  getDestination, getConnectionReference, getReplyToDestinationName, getTypeHeader, isRequestReply, isOneWay, isOverrideTx, isReplyActivity;

    public SenderConfiguration(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }

    @Override
    public void initMethods() {
        getDeliveryMode = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getDeliveryMode", true).build(); //int
        getPriority = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getPriority", true).build(); //int
        getMessageType = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getMessageType", true).build(); //String
        isQueue = interceptor.getNewReflectionBuilder().invokeInstanceMethod("isQueue", true).build(); //boolean
        getDestination = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getDestination", true).build(); //String
        getConnectionReference = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getConnectionReference", true).build(); //String
        getReplyToDestinationName = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getReplyToDestinationName", true).build(); //String
        getTypeHeader = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getTypeHeader", true).build(); //String
        isRequestReply = interceptor.getNewReflectionBuilder().invokeInstanceMethod("isRequestReply", true).build(); //boolean
        isOneWay = interceptor.getNewReflectionBuilder().invokeInstanceMethod("isOneWay", true).build(); //boolean
        isOverrideTx = interceptor.getNewReflectionBuilder().invokeInstanceMethod("isOverrideTx", true).build(); //boolean
        isReplyActivity = interceptor.getNewReflectionBuilder().invokeInstanceMethod("isReplyActivity", true).build(); //boolean
    }

    public int getDeliveryMode() { return getReflectiveInteger(getDeliveryMode, 0); }
    public int getPriority() { return getReflectiveInteger(getPriority, 0); }
    public String getMessageType() { return getReflectiveString(getMessageType, "UNKNOWN-TYPE"); }
    public boolean isQueue() { return (Boolean) getReflectiveObject(isQueue); }
    public String getDestination() { return getReflectiveString(getDestination, "UNKNOWN-DESTINATION"); }
    public String getConnectionReference() { return getReflectiveString(getConnectionReference, "UNKNOWN-CONNREF"); }
    public String getReplyToDestinationName() { return (String) getReflectiveObject(getReplyToDestinationName); }
    public String getTypeHeader() { return (String) getReflectiveObject(getTypeHeader); }
    public boolean isRequestReply() { return (Boolean) getReflectiveObject(isRequestReply); }
    public boolean isOneWay() { return (Boolean) getReflectiveObject(isOneWay); }
    public boolean isOverrideTx() { return (Boolean) getReflectiveObject(isOverrideTx); }
    public boolean isReplyActivity() { return (Boolean) getReflectiveObject(isReplyActivity); }

    public String toString() {
        return String.format("Delivery Mode: %d Priority: %d Message Type: %s is queue: %s Destination: %s Connection Reference: %s Type Header: %s",
                getDeliveryMode(), getPriority(), getMessageType(), isQueue(), getDestination(), getConnectionReference(), getTypeHeader());
    }
}
