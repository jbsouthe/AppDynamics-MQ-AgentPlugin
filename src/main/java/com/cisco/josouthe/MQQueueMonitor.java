package com.cisco.josouthe;

import com.appdynamics.instrumentation.sdk.logging.ISDKLogger;
import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;

public class MQQueueMonitor {
    private ISDKLogger logger;
    private String qName;
    private int openOptions; //https://www.ibm.com/docs/api/v1/content/SSFKSJ_9.2.0/com.ibm.mq.javadoc.doc/WMQJavaClasses/com/ibm/mq/MQQueueManager.html#accessQueue-java.lang.String-int-java.lang.String-java.lang.String-java.lang.String-
    private Object mqQueue;
    private AGenericInterceptor interceptor;

    public MQQueueMonitor(String queueName, int openOptionsArg, Object mqQueue, ISDKLogger logger, AGenericInterceptor interceptor) {
        this.qName=queueName;
        this.openOptions=openOptionsArg;
        this.mqQueue=mqQueue;
        this.logger=logger;
        this.interceptor=interceptor;
        logger.info(String.format("Initialized MQQueueMonitor for Queue Name %s open options %d", queueName, openOptionsArg));
    }

    public String getName() { return qName; }
    public int getOpenOptions() { return openOptions; }
    public Object getQueue() { return mqQueue; }
}
