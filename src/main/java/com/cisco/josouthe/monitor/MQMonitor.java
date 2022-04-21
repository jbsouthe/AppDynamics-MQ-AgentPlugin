package com.cisco.josouthe.monitor;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.cisco.josouthe.wrapper.JmsConnectionFactoryWrapper;
import com.cisco.josouthe.wrapper.MQQueueManagerWrapper;
import com.cisco.josouthe.wrapper.MQQueueWrapper;

import com.ibm.mq.constants.CMQC;
import com.ibm.msg.client.wmq.WMQConstants;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

public class MQMonitor extends BaseJMSMonitor {
    private MQQueueManagerWrapper mqQueueManager;
    private ClassLoader interceptedClassLoader;
    private String providerName;

    public MQMonitor( AGenericInterceptor aGenericInterceptor, JmsConnectionFactoryWrapper connectionFactoryWrapper, String key, String providerName) {
        super(aGenericInterceptor, connectionFactoryWrapper, key);
        this.providerName=providerName;
        mqQueueManager = new MQQueueManagerWrapper(aGenericInterceptor, connectionFactoryWrapper);
        /*
        try {
            this.mqQueueManager = new MQQueueManager(connectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"), connectionFactoryWrapper.getPropertyHashTable());
            logger.info("Initialized IBM MQ Queue Manager for monitoring");
        } catch (MQException mqException) {
            logger.info(String.format("Error initializing queue manager for %s Exception: %s",connectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"), mqException.toString()),mqException);
            useReflectionInterfaces=true;
        }
        if( useReflectionInterfaces ) { //we already have the MQ classes loaded in the parent classloader, so the files we brought can't be used since they won't be found until after the loaded ones, causing an exception
            logger.info(String.format("Attempting to initialize reflective class interface for IBM MQ Queue Manager for monitoring"));
            this.interceptedClassLoader = connectionFactoryWrapper.getObject().getClass().getClassLoader();
            try{
                Class<?> clazz = interceptedClassLoader.loadClass("com.ibm.mq.MQQueueManager");
                Constructor constructor = clazz.getConstructor( String.class, Hashtable.class);
                Hashtable env = connectionFactoryWrapper.getPropertyHashTable("XMSC_WMQ_HOST_NAME", "XMSC_WMQ_PORT", "XMSC_WMQ_CHANNEL", "XMSC_USERID", "XMSC_PASSWORD");
                env.put("XMSC_WMQ_CONNECTION_MODE", WMQConstants.WMQ_CM_CLIENT );
                this.mqQueueManager = (MQQueueManager) constructor.newInstance(connectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"), env);
                logger.info("Initialized IBM MQ Queue Manager for monitoring, with reflection");
            } catch (Exception exception) {
                logger.info(String.format("Error initializing reflective queue manager for %s Exception: %s",connectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"), exception.toString()),exception);
            }
        }
         */
    }

    @Override
    public void run() {
        logger.info(String.format("IBM MQ Monitor run method called for %s", connectionFactoryWrapper.toString()));
        logger.info(String.format("Queue names: %s", queues.toString()));
        for( String qName : queues ) {
            try {
                MQQueueWrapper mqQueueWrapper = mqQueueManager.accessQueue(qName, 8226);
                logger.info(String.format("MQQueue destQueue = qMgr.accessQueue(qName, openOptions);"));
                int depthCurrent = mqQueueWrapper.getCurrentDepth();
                logger.info(String.format("int %d = destQueue.getCurrentDepth();", depthCurrent));
                int depthMax = mqQueueWrapper.getMaximumDepth();
                logger.info(String.format("int %d = destQueue.getMaximumDepth();", depthMax));
                mqQueueWrapper.close();

                reportMetric(String.format("%s|%s|current depth", this.providerName, qName), depthCurrent, "OBSERVATION", "AVERAGE", "COLLECTIVE");
                reportMetric(String.format("%s|%s|maximum depth", this.providerName, qName), depthMax, "OBSERVATION", "AVERAGE", "COLLECTIVE");
                /*

                MQEnvironment.hostname = connectionFactoryWrapper.getStringProperty("XMSC_WMQ_HOST_NAME");
                MQEnvironment.port = connectionFactoryWrapper.getIntProperty("XMSC_WMQ_PORT");
                MQEnvironment.channel = connectionFactoryWrapper.getStringProperty("XMSC_WMQ_CHANNEL");
                MQEnvironment.userID = connectionFactoryWrapper.getStringProperty("XMSC_USERID");
                MQEnvironment.password = connectionFactoryWrapper.getStringProperty("XMSC_PASSWORD");

                logger.info(String.format("about to grab a queue '%s' from the queue manager: %s", qName, mqQueueManager));
                MQQueueManager qMgr = new MQQueueManager(connectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER")); //, connectionFactoryWrapper.getPropertyHashTable());
                 //no class def found exception because class loader is seeing the app classes and not our sdk plugin packaged version
                    [11.517s][info   ][class,load] com.ibm.mq.jmqi.remote.api.RemoteFAP source: file:/Users/josouthe/.m2/repository/com/ibm/mq/com.ibm.mq.allclient/9.2.5.0/com.ibm.mq.allclient-9.2.5.0.jar
                    [11.536s][info   ][class,load] com.ibm.mq.jmqi.remote.api.RemoteFAP$CcdtCacheLock source: file:/Users/josouthe/.m2/repository/com/ibm/mq/com.ibm.mq.allclient/9.2.5.0/com.ibm.mq.allclient-9.2.5.0.jar
                    [11.536s][info   ][class,load] com.ibm.mq.jmqi.remote.api.RemoteFAP$NameListLock source: file:/Users/josouthe/.m2/repository/com/ibm/mq/com.ibm.mq.allclient/9.2.5.0/com.ibm.mq.allclient-9.2.5.0.jar
                    [11.537s][info   ][class,load] com.ibm.mq.jmqi.remote.api.RemoteFAP$ReconnectThreadLock source: file:/Users/josouthe/.m2/repository/com/ibm/mq/com.ibm.mq.allclient/9.2.5.0/com.ibm.mq.allclient-9.2.5.0.jar
                    [11.829s][info   ][class,load] com.ibm.mq.jmqi.remote.api.RemoteFAP$Connector source: file:/Users/josouthe/.m2/repository/com/ibm/mq/com.ibm.mq.allclient/9.2.5.0/com.ibm.mq.allclient-9.2.5.0.jar
                    [12.030s][info   ][class,load] com.ibm.mq.jmqi.remote.api.RemoteFAP$$Lambda$187/0x00000008011bcf58 source: com.ibm.mq.jmqi.remote.api.RemoteFAP
                    Caused by: com.ibm.mq.jmqi.JmqiException: CC=2;RC=2195;AMQ9546: Error return code received. [1=java.lang.NoSuchMethodException[com.ibm.mq.jmqi.remote.api.RemoteFAP.<init>(com.ibm.mq.jmqi.JmqiEnvironment,int)],3=Class.getConstructor0]
                    Caused by: java.lang.NoSuchMethodException: com.ibm.mq.jmqi.remote.api.RemoteFAP.<init>(com.ibm.mq.jmqi.JmqiEnvironment,int)

                logger.info(String.format("about to grab a queue '%s' from the queue manager: %s", qName, mqQueueManager));
                MQQueueManager qMgr = new MQQueueManager(
                        connectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"),
                        connectionFactoryWrapper.getPropertyHashTable("XMSC_WMQ_HOST_NAME", "XMSC_WMQ_PORT", "XMSC_WMQ_CHANNEL", "XMSC_USERID", "XMSC_PASSWORD")
                        );

                logger.info(String.format("MQQueueManager qMgr = new MQQueueManager(connectionFactoryWrapper.getStringProperty(\"XMSC_WMQ_QUEUE_MANAGER\"), connectionFactoryWrapper.getPropertyHashTable());", qMgr));
                int openOptions = CMQC.MQOO_INQUIRE + CMQC.MQOO_FAIL_IF_QUIESCING + CMQC.MQOO_INPUT_SHARED;
                logger.info(String.format("int %d = CMQC.MQOO_INQUIRE + CMQC.MQOO_FAIL_IF_QUIESCING + CMQC.MQOO_INPUT_SHARED;",openOptions));
                MQQueue destQueue = qMgr.accessQueue(qName, openOptions);
                logger.info(String.format("MQQueue destQueue = qMgr.accessQueue(qName, openOptions);"));
                int depth = destQueue.getCurrentDepth();
                logger.info(String.format("int %d = destQueue.getCurrentDepth();", depth));
                destQueue.close();
                logger.info(String.format("destQueue.close();"));
                qMgr.disconnect();
                logger.info(String.format("qMgr.disconnect();"));

                PCFMessageAgent pcfMessageAgent = new PCFMessageAgent(mqQueueManager);
                PCFMessage request = new PCFMessage(CMQCFC.MQCMD_INQUIRE_Q_MGR_STATUS);
                request.addParameter(CMQCFC.MQIACF_Q_MGR_STATUS_ATTRS, new int[] { CMQCFC.MQIACF_ALL });
                PCFMessage[] responses = pcfMessageAgent.send(request);
                if (responses == null || responses.length <= 0) {
                    logger.info("Unexpected Error while PCFMessage.send(), response is either null or empty");
                    continue;
                } else {
                    logger.info(String.format("response[0] toString '%s'", String.valueOf(responses[0])));
                }
                //MQQueue mqQueue = mqQueueManager.accessQueue(qName, CMQC.MQOO_INQUIRE + CMQC.MQOO_FAIL_IF_QUIESCING + CMQC.MQOO_INPUT_SHARED);
                //logger.info(String.format("Queue Name: %s current Queue Depth: %d", qName, mqQueue.getCurrentDepth()));
                //mqQueue.close();

                 */
            } catch (Exception mqException) {
                logger.info(String.format("Error getting queue statistics from %s Exception: %s",qName, mqException.toString()),mqException);
                mqException.printStackTrace();
            }
        }
    }

    //public String toString() { return connectionFactoryWrapper.toString(); }

}
