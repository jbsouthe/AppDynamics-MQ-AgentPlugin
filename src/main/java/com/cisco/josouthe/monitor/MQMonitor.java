package com.cisco.josouthe.monitor;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.cisco.josouthe.wrapper.JmsConnectionFactoryWrapper;
import com.cisco.josouthe.wrapper.MQQueueManagerWrapper;
import com.cisco.josouthe.wrapper.MQQueueWrapper;
import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;

import java.util.HashSet;
import java.util.Set;

public class MQMonitor extends BaseJMSMonitor {
    private MQQueueManager mqQueueManager;


    public MQMonitor( AGenericInterceptor aGenericInterceptor, JmsConnectionFactoryWrapper connectionFactoryWrapper, String key) {
        super(aGenericInterceptor, connectionFactoryWrapper, key);

        try {
            this.mqQueueManager = new MQQueueManager(connectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"), connectionFactoryWrapper.getPropertyHashTable());
        } catch (MQException mqException) {
            interceptor.getLogger().info(String.format("Error initializing queue manager for %s Exception: %s",connectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"), mqException.toString()),mqException);
        }
    }

    @Override
    public void run() {
        interceptor.getLogger().info(String.format("IBM MQ Monitor run method called for %s", connectionFactoryWrapper.toString()));
        interceptor.getLogger().info(String.format("Queue names: %s", queues.toString()));
        for( String qName : queues ) {
            try {
                interceptor.getLogger().info(String.format("about to grab a queue from the queue manager: %s", qName));
                MQQueue mqQueue = mqQueueManager.accessQueue(qName, CMQC.MQOO_INQUIRE + CMQC.MQOO_FAIL_IF_QUIESCING + CMQC.MQOO_INPUT_SHARED);
                interceptor.getLogger().info(String.format("Queue Name: %s current Queue Depth: %d", qName, mqQueue.getCurrentDepth()));
                mqQueue.close();
            } catch (MQException mqException) {
                interceptor.getLogger().info(String.format("Error getting queue statistics from %s Exception: %s",qName, mqException.toString()),mqException);
            }
        }
    }

    //public String toString() { return connectionFactoryWrapper.toString(); }

}
