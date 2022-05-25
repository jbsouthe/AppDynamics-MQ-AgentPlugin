package com.cisco.josouthe.exception;

public class UserNotAuthorizedException extends Throwable {
    public String hostnamne, userID;
    public Integer port;

    public UserNotAuthorizedException( String hostname, Integer port, String userID, String msg ) {
        super(msg);
        this.hostnamne = hostname;
        this.port = port;
        this.userID = userID;
    }
}
