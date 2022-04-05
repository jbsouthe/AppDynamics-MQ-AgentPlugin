package com.cisco.josouthe;

import com.ibm.mq.MQQueue;

public class MQQueueMonitor {
    private String qName;
    private int openOptions; //https://www.ibm.com/docs/api/v1/content/SSFKSJ_9.2.0/com.ibm.mq.javadoc.doc/WMQJavaClasses/com/ibm/mq/MQQueueManager.html#accessQueue-java.lang.String-int-java.lang.String-java.lang.String-java.lang.String-
    private MQQueue mqQueue;

    public MQQueueMonitor(String queueName, int openOptionsArg, MQQueue mqQueue) {
        this.qName=queueName;
        this.openOptions=openOptionsArg;
        this.mqQueue=mqQueue;
    }

    public String getName() { return qName; }
    public int getOpenOptions() { return openOptions; }
    public MQQueue getQueue() { return mqQueue; }
}
