package com.cisco.josouthe.json;

import org.json.JSONObject;

public class AuthenticationOverrideInfo {
    public String jmsProviderName, hostname, userID, password;
    public Integer port;

    public AuthenticationOverrideInfo(JSONObject jsonObject) {
        jmsProviderName = jsonObject.optString("jmsProviderName", "IBM MQ JMS Provider");
        hostname = jsonObject.getString("hostname");
        port = jsonObject.getInt("port");
        userID = jsonObject.getString("userID");
        password = jsonObject.getString("password");
    }

    public String getKey() {
        return String.format("%s:%s:%d", jmsProviderName, hostname, port);
    }
}
