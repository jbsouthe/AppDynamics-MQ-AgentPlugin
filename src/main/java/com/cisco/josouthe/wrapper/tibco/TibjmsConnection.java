package com.cisco.josouthe.wrapper.tibco;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.toolbox.reflection.IReflector;
import com.cisco.josouthe.util.PrintUtility;
import com.cisco.josouthe.wrapper.BaseWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TibjmsConnection extends BaseWrapper {
    IReflector getUrls, getUrlCount, _getAllSessions; //methods
    IReflector _urls, _userName, _password, _properties, _hostName; //attributes
    IReflector TibjmsxURL_getURL; //TibjmsxURL.getURL()

    public TibjmsConnection(ASDKPlugin aGenericInterceptor, Object objectToWrap, Object parentObject) {
        super(aGenericInterceptor, objectToWrap, parentObject);
    }

    @Override
    public void initMethods() {
        getUrls = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getUrls", true).build(); //returns String ',' delimited
        getUrlCount = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getUrlCount", true).build(); //returns int
        _getAllSessions = interceptor.getNewReflectionBuilder().invokeInstanceMethod("_getAllSessions", true).build(); //returns Vector<TibjmsxSessionImp>
        _urls = interceptor.getNewReflectionBuilder().accessFieldValue("_urls", true).build(); //Vector<TibjmsxURL>
        _userName = interceptor.getNewReflectionBuilder().accessFieldValue("_userName", true).build(); //String
        _password = interceptor.getNewReflectionBuilder().accessFieldValue("_password", true).build(); //byte[]
        _properties = interceptor.getNewReflectionBuilder().accessFieldValue("_properties", true).build(); //Map
        _hostName = interceptor.getNewReflectionBuilder().accessFieldValue("_hostName", true).build(); //String
        TibjmsxURL_getURL = interceptor.getNewReflectionBuilder().invokeInstanceMethod("getURL", true).build(); //returns String
    }

    public String getUserName() {
        return (String) getReflectiveObject(_userName);
    }

    public String getPassword() {
        return String.valueOf((byte[]) getReflectiveObject(_password));
    }

    public Map getProperties() {
        return (Map) getReflectiveObject(_properties);
    }

    public String getHostName() {
        return (String) getReflectiveObject(_hostName);
    }

    public boolean hasSession(Object session) {
        for (Object tibjmsSessionImp : (Vector) getReflectiveObject(_getAllSessions)) {
            if (tibjmsSessionImp == session) return true;
        }
        return false;
    }

    public String[] getURLs() {
        List<String> urls = new ArrayList<>();
        for( Object tibjmsURL : (Vector) getReflectiveObject(_urls) ) {
            urls.add( (String) getReflectiveObject(tibjmsURL, TibjmsxURL_getURL) );
        }
        return urls.toArray(new String[0]);
    }

    public String getURLsString() {
        return (String) getReflectiveObject(getUrls);
    }

    public String toString() {
        String[] urls = getURLs();
        return String.format("Connection %d hosts, hostUrls: '%s' user: '%s' properties: '%s'", urls.length, PrintUtility.arrayToString(urls), getUserName(), getProperties());
    }
}