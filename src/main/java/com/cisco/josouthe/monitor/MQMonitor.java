package com.cisco.josouthe.monitor;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.cisco.josouthe.exception.UserNotAuthorizedException;
import com.cisco.josouthe.util.ExceptionUtility;
import com.cisco.josouthe.util.MQConstants;
import com.cisco.josouthe.wrapper.ibmmq.MQQueueManagerWrapper;
import com.cisco.josouthe.wrapper.ibmmq.MQQueueWrapper;
import com.cisco.josouthe.wrapper.ibmmq.PCFMessageAgentWrapper;
import com.cisco.josouthe.wrapper.ibmmq.PCFMessageWrapper;

import java.net.URI;
import java.text.ParseException;
import java.util.*;

public class MQMonitor extends BaseJMSMonitor {
    private MQQueueManagerWrapper mqQueueManager;
    private PCFMessageAgentWrapper agent;
    private ClassLoader interceptedClassLoader;
    private String providerName;

    public MQMonitor(ASDKPlugin aGenericInterceptor, String key, String providerName, MQQueueManagerWrapper mqQueueManagerWrapper) {
        super(aGenericInterceptor, key);
        this.providerName=providerName;
        mqQueueManager = mqQueueManagerWrapper;
        try {
            agent = new PCFMessageAgentWrapper(aGenericInterceptor, mqQueueManager );
        } catch (UserNotAuthorizedException e) {
            logger.info(String.format("Error creating PCFMessageAgent, %s", e.toString()));
        }
    }


    @Override
    public void run() {
        logger.debug(String.format("IBM MQ Monitor run method called for %s", mqQueueManager.toString()));
        reportQueueManagerStatus();

        //if( queues.isEmpty() ) queues.add("queue:///DEV.QUEUE.1"); //for testing
        logger.debug(String.format("Queue names: %s", queues.toString()));
        for( String qName : queues ) {
            String shortQueueName = getQueueName(qName);
            reportQueueStatus(shortQueueName);
            reportQueueDepth(shortQueueName);
        }

        logger.debug(String.format("Channel names: %s", channels.toString()));
        for( String channelName : channels ) {
            reportChannelStatus( channelName );
        }

        logger.debug(String.format("Topic names: %s", topics.toString()));
        if( ! topics.isEmpty() ) {
            reportTopicStatus( topics );
        }
    }

    private void reportTopicStatus(Set<String> topicNames ) {
        PCFMessageWrapper request = new PCFMessageWrapper(this.interceptor, agent.getObject(), MQConstants.getIntFromConstant("MQCMD_INQUIRE_TOPIC_STATUS"));
        request.addParameter(MQConstants.getIntFromConstant("MQCA_TOPIC_STRING"), "#"); //com.ibm.mq.constants.CMQC.MQCA_TOPIC_STRING=2094
        List<PCFMessageWrapper> responses = agent.send(request);
        for (PCFMessageWrapper response : responses) {
            logger.trace(String.format("Response %s", response.toString()));
            String topicName = response.getStringParameterValue("MQCA_ADMIN_TOPIC_NAME");
            String topicString = response.getStringParameterValue("MQCA_TOPIC_STRING");
            if (topicNames.contains(topicName) || topicNames.contains(topicString)) {
                int pubCount = response.getIntParameterValue("MQIA_PUB_COUNT");
                reportMetric(String.format("%s|%s|Topic|%s/%s|Publish Count", this.providerName, mqQueueManager.getName(), topicName, topicString), pubCount, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
                int subCount = response.getIntParameterValue("MQIA_SUB_COUNT");
                reportMetric(String.format("%s|%s|Topic|%s/%s|Subscription Count", this.providerName, mqQueueManager.getName(), topicName, topicString), subCount, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            }
        }
    }

    private void reportChannelStatus( String channelName ) {
        PCFMessageWrapper request = new PCFMessageWrapper(this.interceptor, agent.getObject(), MQConstants.getIntFromConstant("CMQCFC.MQCMD_INQUIRE_CHANNEL_STATUS"));
        request.addParameter(MQConstants.getIntFromConstant("CMQCFC.MQCACH_CHANNEL_NAME"), channelName);
        List<PCFMessageWrapper> responses = agent.send(request);
        for (PCFMessageWrapper response : responses) {
            logger.trace(String.format("Response %s", response.toString()));
            int bytesSent = response.getIntParameterValue("MQIACH_BYTES_SENT");
            reportMetric(String.format("%s|%s|Channel|%s|Bytes Sent", this.providerName, mqQueueManager.getName(), channelName), bytesSent, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            int bytesRecv = response.getIntParameterValue("MQIACH_BYTES_RECEIVED");
            reportMetric(String.format("%s|%s|Channel|%s|Bytes Received", this.providerName, mqQueueManager.getName(), channelName), bytesRecv, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            int buffersSent = response.getIntParameterValue("MQIACH_BUFFERS_SENT");
            reportMetric(String.format("%s|%s|Channel|%s|Buffers Sent", this.providerName, mqQueueManager.getName(), channelName), buffersSent, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            int buffersRecv = response.getIntParameterValue("MQIACH_BUFFERS_RECEIVED");
            reportMetric(String.format("%s|%s|Channel|%s|Buffers Received", this.providerName, mqQueueManager.getName(), channelName), buffersRecv, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            int status = response.getIntParameterValue("MQIACH_CHANNEL_SUBSTATE");
            reportMetric(String.format("%s|%s|Channel|%s|Status", this.providerName, mqQueueManager.getName(), channelName), status, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
        }
    }

    private void reportQueueDepth(String shortQueueName) {
        try {
            MQQueueWrapper mqQueueWrapper = mqQueueManager.accessQueue(shortQueueName, 8226);
            logger.debug(String.format("MQQueue destQueue = qMgr.accessQueue(qName, openOptions);"));
            int depthCurrent = mqQueueWrapper.getCurrentDepth();
            logger.debug(String.format("int %d = destQueue.getCurrentDepth();", depthCurrent));
            int depthMax = mqQueueWrapper.getMaximumDepth();
            logger.debug(String.format("int %d = destQueue.getMaximumDepth();", depthMax));
            mqQueueWrapper.close();

            reportMetric(String.format("%s|%s|%s|Current Depth", this.providerName, mqQueueManager.getName(), shortQueueName), depthCurrent, "OBSERVATION", "AVERAGE", "COLLECTIVE");
            reportMetric(String.format("%s|%s|%s|Maximum Depth", this.providerName, mqQueueManager.getName(), shortQueueName), depthMax, "OBSERVATION", "AVERAGE", "COLLECTIVE");
        } catch (Exception mqException) {
            Throwable sourceException = ExceptionUtility.getRootCause(mqException);
            logger.debug(String.format("Error getting queue statistics from %s Exception: %s", shortQueueName, sourceException.toString()),sourceException);
        }
    }

    private void reportQueueStatus(String shortQueueName) {
        PCFMessageWrapper request = new PCFMessageWrapper(this.interceptor, agent.getObject(), MQConstants.getIntFromConstant("CMQCFC.MQCMD_INQUIRE_Q_STATUS"));
        request.addParameter( MQConstants.getIntFromConstant("MQCA_Q_NAME"), shortQueueName);
        request.addParameter( MQConstants.getIntFromConstant("CMQCFC.MQIACF_Q_STATUS_ATTRS"), new Integer[]{ MQConstants.getIntFromConstant("CMQCFC.MQIACF_ALL") });
        List<PCFMessageWrapper> responses = agent.send(request);
        for( PCFMessageWrapper response : responses ) {
            logger.trace(String.format("Response %s", response.toString()));
            int curQFileSize = response.getIntParameterValue("MQIACF_CUR_Q_FILE_SIZE");
            reportMetric(String.format("%s|%s|%s|Current Queue File Size", this.providerName, mqQueueManager.getName(), shortQueueName), curQFileSize, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            int maxQFileSize = response.getIntParameterValue("MQIACF_CUR_MAX_FILE_SIZE");
            reportMetric(String.format("%s|%s|%s|Max Queue File Size", this.providerName, mqQueueManager.getName(), shortQueueName), maxQFileSize, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            reportMetric(String.format("%s|%s|%s|Percent Queue File Full", this.providerName, mqQueueManager.getName(), shortQueueName), (int) (curQFileSize/maxQFileSize), "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            int curInputCount = response.getIntParameterValue("MQIA_OPEN_INPUT_COUNT");
            reportMetric(String.format("%s|%s|%s|Open Input Count", this.providerName, mqQueueManager.getName(), shortQueueName), curInputCount, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            int curOutputCount = response.getIntParameterValue("MQIA_OPEN_OUTPUT_COUNT");
            reportMetric(String.format("%s|%s|%s|Open Output Count", this.providerName, mqQueueManager.getName(), shortQueueName), curOutputCount, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            try {
                long lastGetTimestamp = getTimeStamp(response.getStringParameterValue("MQCACF_LAST_GET_DATE"), response.getStringParameterValue("MQCACF_LAST_GET_TIME"));
                reportMetric(String.format("%s|%s|%s|Last Get Time (ms ago)", this.providerName, mqQueueManager.getName(), shortQueueName), getRelativeTime(lastGetTimestamp), "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            } catch (ParseException e) {
                logger.info(String.format("Warning, timestamp could not be parsed for Last Get TimeStamp, error: %s",e.toString()));
            }
            try {
                long lastPutTimestamp = getTimeStamp(response.getStringParameterValue("MQCACF_LAST_PUT_DATE"), response.getStringParameterValue("MQCACF_LAST_PUT_TIME"));
                reportMetric(String.format("%s|%s|%s|Last Put Time (ms ago)", this.providerName, mqQueueManager.getName(), shortQueueName), getRelativeTime(lastPutTimestamp), "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            } catch (ParseException e) {
                logger.info(String.format("Warning, timestamp could not be parsed for Last Put TimeStamp, error: %s",e.toString()));
            }
            int oldMsgAge = response.getIntParameterValue("MQIACF_OLDEST_MSG_AGE");
            reportMetric(String.format("%s|%s|%s|Oldest Message Age", this.providerName, mqQueueManager.getName(), shortQueueName), oldMsgAge, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            int uncommittedMsgs = response.getIntParameterValue("MQIACF_UNCOMMITTED_MSGS");
            reportMetric(String.format("%s|%s|%s|Uncommitted Message Count", this.providerName, mqQueueManager.getName(), shortQueueName), uncommittedMsgs, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
        }
    }

    private void reportQueueManagerStatus() {
        PCFMessageWrapper request = new PCFMessageWrapper(this.interceptor, agent.getObject(), MQConstants.getIntFromConstant("CMQCFC.MQCMD_INQUIRE_Q_MGR_STATUS"));
        request.addParameter(MQConstants.getIntFromConstant("CMQCFC.MQIACF_Q_MGR_STATUS_ATTRS"),
                new Integer[] { MQConstants.getIntFromConstant("CMQCFC.MQIACF_ALL") });
        List<PCFMessageWrapper> responses = agent.send( request );
        for( PCFMessageWrapper response : responses ) {
            logger.trace(String.format("Response %s", response.toString()));
            int status = response.getIntParameterValue( "MQIACF_Q_MGR_STATUS" );
            logger.trace(String.format("MQIACF_Q_MGR_STATUS=%d",status));
            reportMetric(String.format("%s|%s|Status", this.providerName, mqQueueManager.getName()), status, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            int connectionCount = response.getIntParameterValue("MQIACF_CONNECTION_COUNT");
            reportMetric(String.format("%s|%s|ConnectionCount", this.providerName, mqQueueManager.getName()), connectionCount, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
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
