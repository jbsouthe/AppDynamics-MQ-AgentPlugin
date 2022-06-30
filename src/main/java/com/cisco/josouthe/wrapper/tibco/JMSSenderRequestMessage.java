package com.cisco.josouthe.wrapper.tibco;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.wrapper.BaseWrapper;

public class JMSSenderRequestMessage extends BaseWrapper { //com.tibco.plugin.jms.service.JMSSenderRequestMessage
    private IReflector getDeliveryMode, getPriority, getType, getStringProperty, getIntProperty, getDestinationName, getMessageBodyAsString, getReplyToName, getCorrelationId;

    public JMSSenderRequestMessage(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }

    @Override
    public void initMethods() {
        getDeliveryMode = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getDeliveryMode", true).build(); //int
        getPriority = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getPriority", true).build(); //int
        getType = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getType", true).build(); //String
        getStringProperty = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getStringProperty", true, new String[] { "com.tibco.xml.data.primitive.ExpandedName" }).build(); //String
        getIntProperty = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getIntProperty", true, new String[] { "com.tibco.xml.data.primitive.ExpandedName", int.class.getCanonicalName() }).build(); //int
        getDestinationName = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getDestinationName", true).build(); //String
        getMessageBodyAsString = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getMessageBodyAsString", true).build(); //String
        getReplyToName = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getReplyToName", true).build(); //String
        getCorrelationId = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getCorrelationId", true).build(); //String
    }

    public int getDeliveryMode() { return getReflectiveInteger(getDeliveryMode, 0); }
    public int getPriority() { return getReflectiveInteger(getPriority, 0); }
    public String getType() { return (String) getReflectiveObject(getType); }
    public String getStringProperty( Object expandedNameOrString ) {
        ExpandedName expandedName = null;
        if( expandedNameOrString instanceof String ) {
            expandedName = new ExpandedName( this.interceptor, expandedNameOrString, this.object );
        } else if( expandedNameOrString instanceof ExpandedName ) {
            //just use it as is, i agree their should be a better way than this
        } else { //let's wrap it and assume it is an ExpandedName object somehow taken from the app
            expandedName = new ExpandedName(this.interceptor, expandedNameOrString, this.object);
        }
        if( expandedName == null ) {
            logger.info(String.format("Error in getStringProperty, ExpandedName is still null for '%s'",expandedNameOrString));
            return null;
        }
        return (String) getReflectiveObject(getStringProperty, expandedName.getObject());
    }
    public Integer getIntProperty( Object expandedNameOrString, int i ) {
        ExpandedName expandedName = null;
        if( expandedNameOrString instanceof String ) {
            expandedName = new ExpandedName( this.interceptor, expandedNameOrString, this.object );
        } else if( expandedNameOrString instanceof ExpandedName ) {
            //just use it as is
        } else { //let's wrap it and assume it is an ExpandedName
            expandedName = new ExpandedName(this.interceptor, expandedNameOrString, this.object);
        }
        if( expandedName == null ) {
            logger.info(String.format("Error in getIntProperty, ExpandedName is still null for '%s'",expandedNameOrString));
            return null;
        }
        return (Integer) getReflectiveObject(getIntProperty, expandedName.getObject(), i);
    }

    public String getDestinationName() { return (String) getReflectiveObject(getDestinationName); }
    public String getMessageBodyAsString() { return (String) getReflectiveObject(getMessageBodyAsString); }
    public String getReplyToName() { return (String) getReflectiveObject(getReplyToName); }
    public String getCorrelationId() { return (String) getReflectiveObject(getCorrelationId); }

    public String toString() {
        return String.format("Destination: %s Type: %s Message Body: '%s' Correlation Id: %s", getDestinationName(), getType(), getMessageBodyAsString(), getCorrelationId());
    }
}
