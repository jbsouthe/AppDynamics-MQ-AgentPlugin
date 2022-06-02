package com.cisco.josouthe.json;

import junit.framework.TestCase;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationOverrideInfoTest extends TestCase {

    public void testJSONParser() {
        AuthenticationOverrideInfo defaultAuthenticationOverrideInfo = null;
        Map<String,AuthenticationOverrideInfo> authenticationsHashMap = new HashMap<>();
        String testJSON = "./IBMMQAgentPlugin-authentications.json";

        try {
            InputStream inputStream = new FileInputStream(testJSON);
            JSONTokener tokener = new JSONTokener(inputStream);
            JSONArray array = new JSONArray(tokener);
            for( int i=0; i< array.length(); i++ ){
                AuthenticationOverrideInfo authenticationOverrideInfo = new AuthenticationOverrideInfo(array.getJSONObject(i));
                System.out.println(String.format("Initializing Authentication Override: %s key: %s", authenticationOverrideInfo, authenticationOverrideInfo.getKey()));
                if( authenticationOverrideInfo.isDefault() ) {
                    if( defaultAuthenticationOverrideInfo == null ) {
                        defaultAuthenticationOverrideInfo = authenticationOverrideInfo;
                    } else {
                        System.out.println(String.format("Default authentication override config already set, looks to be set more than once, please update the config file '%s' to only have one default, we are ignoring the others", testJSON));
                    }
                } else {
                    authenticationsHashMap.put(authenticationOverrideInfo.getKey(), authenticationOverrideInfo);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(String.format("Error reading %s, exception: %s", testJSON,  e.toString()));
        }

        //assert defaultAuthenticationOverrideInfo.toString().equals("IBM MQ JMS Provider app:***SECRET***@:0/channel=MONITOR.SRVCONN isDefault? true");
        assert authenticationsHashMap.get("IBM MQ JMS Provider:someHost:1414").toString().equals("IBM MQ JMS Provider mqm:(BLANK)@someHost:1414/channel=MONITOR.SRVCONN isDefault? false");
    }

}