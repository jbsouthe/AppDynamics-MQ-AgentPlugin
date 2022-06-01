package com.cisco.josouthe.exception;

public class UserNotAuthorizedException extends Throwable {
    public String hostnamne, userID, channel;
    public Integer port;

    public UserNotAuthorizedException( String hostname, Integer port, String userID, String channel, String msg ) {
        super(msg);
        this.hostnamne = hostname;
        this.port = port;
        this.userID = userID;
        this.channel = channel;
    }

    public String toString() {
        return String.format("the user is not authorized: %s@%s:%p/channel=%s exception: %s", userID, hostnamne, port, channel, getMessage());
    }
}
