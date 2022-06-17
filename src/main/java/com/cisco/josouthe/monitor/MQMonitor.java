package com.cisco.josouthe.monitor;

import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.cisco.josouthe.exception.UserNotAuthorizedException;
import com.cisco.josouthe.json.AuthenticationOverrideInfo;
import com.cisco.josouthe.metric.IBMMQMetric;
import com.cisco.josouthe.util.ExceptionUtility;
import com.cisco.josouthe.util.MQConstants;
import com.cisco.josouthe.wrapper.*;

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
        PCFMessageWrapper request = new PCFMessageWrapper(this.interceptor, agent.getObject(), MQConstants.getIntFromConstant("CMQCFC.MQCMD_INQUIRE_Q_MGR_STATUS"));
        request.addParameter(MQConstants.getIntFromConstant("CMQCFC.MQIACF_Q_MGR_STATUS_ATTRS"),
                new Integer[] { MQConstants.getIntFromConstant("CMQCFC.MQIACF_ALL") });
        List<PCFMessageWrapper> responses = agent.send( request );
        for( PCFMessageWrapper response : responses ) {
            logger.trace(String.format("Response %s", response.toString()));
            int status = response.getIntParameterValue( MQConstants.getIntFromConstant("MQIACF_Q_MGR_STATUS") );
            logger.trace(String.format("MQIACF_Q_MGR_STATUS=%d",status));
            reportMetric(String.format("%s|%s|Status", this.providerName, mqQueueManager.getName()), status, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
            int connectionCount = response.getIntParameterValue( MQConstants.getIntFromConstant("MQIACF_CONNECTION_COUNT"));
            reportMetric(String.format("%s|%s|ConnectionCount", this.providerName, mqQueueManager.getName()), connectionCount, "OBSERVATION", "AVERAGE", "INDIVIDUAL");
        }

        if( queues.isEmpty() ) queues.add("queue:///DEV.QUEUE.1"); //for testing
        logger.debug(String.format("Queue names: %s", queues.toString()));
        for( String qName : queues ) {
            String shortQueueName = getQueueName(qName);
            request = new PCFMessageWrapper(this.interceptor, agent.getObject(), MQConstants.getIntFromConstant("CMQCFC.MQCMD_INQUIRE_Q_STATUS"));
            request.addParameter( MQConstants.getIntFromConstant("CMQC.MQCA_Q_NAME"), shortQueueName);
            request.addParameter( MQConstants.getIntFromConstant("CMQCFC.MQIACF_Q_STATUS_ATTRS"), new Integer[]{ MQConstants.getIntFromConstant("CMQCFC.MQIACF_ALL") });
            responses = agent.send(request);
            for( PCFMessageWrapper response : responses ) {
                logger.debug(String.format("Response %s", response.toString()));
            }

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
