package com.cisco.josouthe.exception;

import com.cisco.josouthe.wrapper.MQQueueManagerWrapper;

public class UserNotAuthorizedException extends Throwable {
    public String hostnamne, userID, queueManagerName, channel;
    public Integer port;

    public UserNotAuthorizedException( String hostname, Integer port, String userID, String queueManagerName, String channel, String msg ) {
        super(msg);
        this.hostnamne = hostname;
        this.port = port;
        this.userID = userID;
        this.queueManagerName = queueManagerName;
        this.channel = channel;
    }

    public UserNotAuthorizedException(MQQueueManagerWrapper mqQueueManager, String msg) {
        super(msg);
        this.hostnamne = mqQueueManager.getHostname();
        this.port = mqQueueManager.getPort();
        this.userID = mqQueueManager.getUserID();
        this.queueManagerName = mqQueueManager.getName();
        this.channel = mqQueueManager.getChannel();
    }

    public String toString() {
        return String.format("the user is not authorized: %s@%s:%p/%s?channel=%s exception: %s", userID, hostnamne, port, queueManagerName, channel, getMessage());
    }
}
