package com.cisco.josouthe.monitor;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.cisco.josouthe.exception.UserNotAuthorizedException;
import com.cisco.josouthe.json.AuthenticationOverrideInfo;
import com.cisco.josouthe.metric.IBMMQMetric;
import com.cisco.josouthe.util.ExceptionUtility;
import com.cisco.josouthe.util.MQConstants;
import com.cisco.josouthe.wrapper.JmsConnectionFactoryWrapper;
import com.cisco.josouthe.wrapper.MQQueueManagerWrapper;
import com.cisco.josouthe.wrapper.MQQueueWrapper;

import com.cisco.josouthe.wrapper.PCFMessageAgentWrapper;

import java.net.URI;
import java.util.*;

public class MQMonitor extends BaseJMSMonitor {
    private MQQueueManagerWrapper mqQueueManager;
    private PCFMessageAgentWrapper agent;
    private ClassLoader interceptedClassLoader;
    private String providerName;
    private List<IBMMQMetric> metrics;


    public MQMonitor(AGenericInterceptor aGenericInterceptor, JmsConnectionFactoryWrapper connectionFactoryWrapper, String key, String providerName, AuthenticationOverrideInfo authenticationOverrideInfo) {
        super(aGenericInterceptor, connectionFactoryWrapper, key);
        this.providerName=providerName;
        mqQueueManager = new MQQueueManagerWrapper(aGenericInterceptor, connectionFactoryWrapper, authenticationOverrideInfo);
        try {
            agent = new PCFMessageAgentWrapper(aGenericInterceptor, mqQueueManager );
        } catch (UserNotAuthorizedException e) {
            logger.debug(String.format("Error creating PCFMessageAgent, %s", e.toString()));
        }

        metrics = new ArrayList<>();
        metrics.add(new IBMMQMetric("Status", "CMQCFC.MQIACF_Q_MGR_STATUS", MQConstants.getIntFromConstant("CMQCFC.MQIACF_Q_MGR_STATUS"),
                "OBSERVATION", "AVERAGE", "INDIVIDUAL"));
        metrics.add(new IBMMQMetric("ConnectionCount", "CMQCFC.MQIACF_CONNECTION_COUNT", MQConstants.getIntFromConstant("CMQCFC.MQIACF_CONNECTION_COUNT"),
                "OBSERVATION", "AVERAGE", "INDIVIDUAL"));
    }


    @Override
    public void run() {
        logger.debug(String.format("IBM MQ Monitor run method called for %s", connectionFactoryWrapper.toString()));
        Map<Integer, Integer[]> params = new HashMap<>();
        params.put( MQConstants.getIntFromConstant("CMQCFC.MQIACF_Q_MGR_STATUS_ATTRS"),
                new Integer[] { MQConstants.getIntFromConstant("CMQCFC.MQIACF_ALL") });
        Map<String,Object> metricMap = agent.getMetrics( MQConstants.getIntFromConstant("CMQCFC.MQCMD_INQUIRE_Q_MGR_STATUS"), params);

        if( queues.isEmpty() ) queues.add("queue:///DEV.QUEUE.1"); //for testing
        logger.debug(String.format("Queue names: %s", queues.toString()));
        for( String qName : queues ) {
            try {
                String shortQueueName = getQueueName(qName);
                MQQueueWrapper mqQueueWrapper = mqQueueManager.accessQueue(shortQueueName, 8226);
                logger.debug(String.format("MQQueue destQueue = qMgr.accessQueue(qName, openOptions);"));
                int depthCurrent = mqQueueWrapper.getCurrentDepth();
                logger.debug(String.format("int %d = destQueue.getCurrentDepth();", depthCurrent));
                int depthMax = mqQueueWrapper.getMaximumDepth();
                logger.debug(String.format("int %d = destQueue.getMaximumDepth();", depthMax));
                mqQueueWrapper.close();

                reportMetric(String.format("%s|%s|%s|current depth", this.providerName, mqQueueManager.getName(), shortQueueName), depthCurrent, "OBSERVATION", "AVERAGE", "COLLECTIVE");
                reportMetric(String.format("%s|%s|%s|maximum depth", this.providerName, mqQueueManager.getName(), shortQueueName), depthMax, "OBSERVATION", "AVERAGE", "COLLECTIVE");





                /*
                MQEnvironment.hostname = connectionFactoryWrapper.getStringProperty("XMSC_WMQ_HOST_NAME");
                MQEnvironment.port = connectionFactoryWrapper.getIntProperty("XMSC_WMQ_PORT");
                MQEnvironment.channel = connectionFactoryWrapper.getStringProperty("XMSC_WMQ_CHANNEL");
                MQEnvironment.userID = connectionFactoryWrapper.getStringProperty("XMSC_USERID");
                MQEnvironment.password = connectionFactoryWrapper.getStringProperty("XMSC_PASSWORD");

                logger.debug(String.format("about to grab a queue '%s' from the queue manager: %s", qName, mqQueueManager));
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

                logger.debug(String.format("about to grab a queue '%s' from the queue manager: %s", qName, mqQueueManager));
                MQQueueManager qMgr = new MQQueueManager(
                        connectionFactoryWrapper.getStringProperty("XMSC_WMQ_QUEUE_MANAGER"),
                        connectionFactoryWrapper.getPropertyHashTable("XMSC_WMQ_HOST_NAME", "XMSC_WMQ_PORT", "XMSC_WMQ_CHANNEL", "XMSC_USERID", "XMSC_PASSWORD")
                        );

                logger.debug(String.format("MQQueueManager qMgr = new MQQueueManager(connectionFactoryWrapper.getStringProperty(\"XMSC_WMQ_QUEUE_MANAGER\"), connectionFactoryWrapper.getPropertyHashTable());", qMgr));
                int openOptions = CMQC.MQOO_INQUIRE + CMQC.MQOO_FAIL_IF_QUIESCING + CMQC.MQOO_INPUT_SHARED;
                logger.debug(String.format("int %d = CMQC.MQOO_INQUIRE + CMQC.MQOO_FAIL_IF_QUIESCING + CMQC.MQOO_INPUT_SHARED;",openOptions));
                MQQueue destQueue = qMgr.accessQueue(qName, openOptions);
                logger.debug(String.format("MQQueue destQueue = qMgr.accessQueue(qName, openOptions);"));
                int depth = destQueue.getCurrentDepth();
                logger.debug(String.format("int %d = destQueue.getCurrentDepth();", depth));
                destQueue.close();
                logger.debug(String.format("destQueue.close();"));
                qMgr.disconnect();
                logger.debug(String.format("qMgr.disconnect();"));

                PCFMessageAgent pcfMessageAgent = new PCFMessageAgent(mqQueueManager);
                PCFMessage request = new PCFMessage(CMQCFC.MQCMD_INQUIRE_Q_MGR_STATUS);
                request.addParameter(CMQCFC.MQIACF_Q_MGR_STATUS_ATTRS, new int[] { CMQCFC.MQIACF_ALL });
                PCFMessage[] responses = pcfMessageAgent.send(request);
                if (responses == null || responses.length <= 0) {
                    logger.debug("Unexpected Error while PCFMessage.send(), response is either null or empty");
                    continue;
                } else {
                    logger.debug(String.format("response[0] toString '%s'", String.valueOf(responses[0])));
                }
                //MQQueue mqQueue = mqQueueManager.accessQueue(qName, CMQC.MQOO_INQUIRE + CMQC.MQOO_FAIL_IF_QUIESCING + CMQC.MQOO_INPUT_SHARED);
                //logger.debug(String.format("Queue Name: %s current Queue Depth: %d", qName, mqQueue.getCurrentDepth()));
                //mqQueue.close();

                 */
            } catch (Exception mqException) {
                Throwable sourceException = ExceptionUtility.getRootCause(mqException);
                logger.debug(String.format("Error getting queue statistics from %s Exception: %s",qName, sourceException.toString()),sourceException);
            }
        }
    }


    public String getQueueName( String uri ) {
        String name = uri;
        try {
            name = new URI(uri).getPath();
        } catch (Exception exception) {
            logger.debug(String.format("Error attempting to get path from '%s'", uri));
        }
        return (name.startsWith("/") ? name.substring(1) : name);
    }
}
