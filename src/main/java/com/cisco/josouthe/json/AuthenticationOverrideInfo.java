package com.cisco.josouthe.json;

import org.json.JSONObject;

public class AuthenticationOverrideInfo {
    public String jmsProviderName, hostname, userID, password, channel;
    public Integer port;
    public Boolean defaultAuthenticationOverride = false;

    public AuthenticationOverrideInfo(JSONObject jsonObject) {
        jmsProviderName = jsonObject.optString("jmsProviderName", "IBM MQ JMS Provider");
        defaultAuthenticationOverride = jsonObject.optBoolean("default", false);
        userID = jsonObject.optString("userID");
        password = jsonObject.optString("password");
        channel = jsonObject.optString("channel" );
        hostname = jsonObject.optString("hostname");
        port = jsonObject.optInt("port");
    }

    public String getKey() {
        return String.format("%s:%s:%d", jmsProviderName, hostname, port);
    }
    public boolean isDefault() { return defaultAuthenticationOverride; }

    public String toString() {
        return String.format("%s %s:%s@%s:%d/channel=%s isDefault? %s", jmsProviderName, userID, ( "".equals(password) ?  "(BLANK)" : "***SECRET***" ), hostname, port, channel, isDefault());
    }
}
