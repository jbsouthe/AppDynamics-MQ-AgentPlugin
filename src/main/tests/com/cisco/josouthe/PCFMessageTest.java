package com.cisco.josouthe;

import com.appdynamics.agent.sdk.impl.NoOpLogger;
import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.Rule;
import com.appdynamics.instrumentation.sdk.logging.ISDKLogger;
import com.cisco.josouthe.wrapper.MQQueueManagerWrapper;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.CMQCFC;
import com.ibm.mq.headers.MQDataException;
import com.ibm.mq.headers.pcf.PCFMessage;
import com.ibm.mq.headers.pcf.PCFMessageAgent;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

public class PCFMessageTest extends TestCase {

    /*
    @Test
    public void testPCFMessageAgentSendAPI() throws IOException, MQDataException, MQException {
        Hashtable connectionProperties = new Hashtable<String,Object>(); //jmsConnectionFactoryWrapper.getPropertyHashTable("XMSC_WMQ_HOST_NAME", "XMSC_WMQ_PORT", "XMSC_WMQ_CHANNEL", "XMSC_USERID", "XMSC_PASSWORD");
        connectionProperties.put("transport", "MQSeries Client");
        connectionProperties.put("hostname", "localhost");
        connectionProperties.put("port", 1414);
        connectionProperties.put("channel", "MONITOR.SRVCONN");
        connectionProperties.put("userID", "app");
        connectionProperties.put("password", "passw0rd");
        MQQueueManager queueManager = new MQQueueManager("QM1", connectionProperties);
        PCFMessageAgent pcfMessageAgent = new PCFMessageAgent(queueManager);
        pcfMessageAgent.setWaitInterval(5);
        PCFMessage message = new PCFMessage(CMQCFC.MQCMD_INQUIRE_Q_MGR_STATUS);
        message.addParameter(CMQCFC.MQIACF_Q_MGR_STATUS_ATTRS, new int[] { CMQCFC.MQIACF_ALL });
        //message.addParameter(CMQCFC.MQIACF_Q_MGR_STATUS_ATTRS, CMQCFC.MQIACF_ALL ); //this fails ever time, must be an array of int
        System.out.println(String.format("request %s",message.toString()));
        PCFMessage[] responses = pcfMessageAgent.send(message);
        for( PCFMessage response : responses ) {
            System.out.println(String.format("response %s", response.toString()));
        }
    }

    @Test
    public void testPCFMessageAgentSendChannelMetrics() throws IOException, MQDataException, MQException {
        Hashtable connectionProperties = new Hashtable<String,Object>(); //jmsConnectionFactoryWrapper.getPropertyHashTable("XMSC_WMQ_HOST_NAME", "XMSC_WMQ_PORT", "XMSC_WMQ_CHANNEL", "XMSC_USERID", "XMSC_PASSWORD");
        connectionProperties.put("transport", "MQSeries Client");
        connectionProperties.put("hostname", "localhost");
        connectionProperties.put("port", 1414);
        connectionProperties.put("channel", "MONITOR.SRVCONN");
        connectionProperties.put("userID", "app");
        connectionProperties.put("password", "passw0rd");
        MQQueueManager queueManager = new MQQueueManager("QM1", connectionProperties);
        PCFMessageAgent pcfMessageAgent = new PCFMessageAgent(queueManager);
        pcfMessageAgent.setWaitInterval(5);
        PCFMessage message = new PCFMessage(CMQCFC.MQCMD_INQUIRE_CHANNEL_STATUS);
        message.addParameter(CMQCFC.MQCACH_CHANNEL_NAME, "DEV.APP.SVRCONN");
        System.out.println(String.format("request %s",message.toString()));
        PCFMessage[] responses = pcfMessageAgent.send(message);
        for( PCFMessage response : responses ) {
            System.out.println(String.format("response %s", response.toString()));
        }
    }
     */

    @Test
    public void testPCFMessageAgentSendTopicMetrics() throws IOException, MQDataException, MQException {
        Hashtable connectionProperties = new Hashtable<String,Object>(); //jmsConnectionFactoryWrapper.getPropertyHashTable("XMSC_WMQ_HOST_NAME", "XMSC_WMQ_PORT", "XMSC_WMQ_CHANNEL", "XMSC_USERID", "XMSC_PASSWORD");
        connectionProperties.put("transport", "MQSeries Client");
        connectionProperties.put("hostname", "localhost");
        connectionProperties.put("port", 1414);
        connectionProperties.put("channel", "MONITOR.SRVCONN");
        connectionProperties.put("userID", "app");
        connectionProperties.put("password", "passw0rd");
        MQQueueManager queueManager = new MQQueueManager("QM1", connectionProperties);
        PCFMessageAgent pcfMessageAgent = new PCFMessageAgent(queueManager);
        pcfMessageAgent.setWaitInterval(5);
        PCFMessage message = new PCFMessage(CMQCFC.MQCMD_INQUIRE_TOPIC_STATUS);
        //message.addParameter(CMQCFC.MQIACF_TOPIC_STATUS_TYPE, CMQCFC.MQIACF_TOPIC_SUB );
        message.addParameter(CMQC.MQCA_TOPIC_STRING, "#");
        System.out.println(String.format("request %s",message.toString()));
        PCFMessage[] responses = pcfMessageAgent.send(message);
        for( PCFMessage response : responses ) {
            System.out.println(String.format("response %s", response.toString()));
        }
    }



    /*
    @Test
    public void testPCFMessageAgentSendReflection() throws Exception {
        //not yet working

        ASDKPlugin asdkPlugin = new ASDKPluginImpl();
        Hashtable connectionProperties = new Hashtable<String,Object>(); //jmsConnectionFactoryWrapper.getPropertyHashTable("XMSC_WMQ_HOST_NAME", "XMSC_WMQ_PORT", "XMSC_WMQ_CHANNEL", "XMSC_USERID", "XMSC_PASSWORD");
        connectionProperties.put("transport", "MQSeries Client");
        connectionProperties.put("hostname", "localhost");
        connectionProperties.put("port", 1414);
        connectionProperties.put("channel", "MONITOR.SRVCONN");
        connectionProperties.put("userID", "app");
        connectionProperties.put("password", "passw0rd");
        MQQueueManagerWrapper queueManager = new MQQueueManagerWrapper( asdkPlugin, "QM1", connectionProperties);


    }

     */

    public class ASDKPluginImpl extends ASDKPlugin {

        ISDKLogger logger;

        public ASDKPluginImpl() {

        }

        private ISDKLogger createLogger() {
            return new NoOpLogger();
        }

        @Override
        public List<Rule> initializeRules() {
            return null;
        }
    }
}
