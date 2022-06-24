package com.cisco.josouthe;

import com.appdynamics.agent.sdk.impl.NoOpLogger;
import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.Rule;
import com.appdynamics.instrumentation.sdk.logging.ISDKLogger;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.CMQCFC;
import com.ibm.mq.headers.MQDataException;
import com.ibm.mq.headers.pcf.PCFMessage;
import com.ibm.mq.headers.pcf.PCFMessageAgent;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

public class PCFMessageTest extends Assume {
    MQQueueManager queueManager = null;
    PCFMessageAgent pcfMessageAgent = null;

    @Before
    public void setUp() {
        Properties testProps = new Properties();
        try {
            testProps.load(new FileInputStream(new File("TestAuth.properties")));
        } catch ( IOException ioException ) {
            System.out.println("Setup the ./TestAuth.properties file so the unit tests can run, message: "+ ioException.toString());
        }
        try {
            Hashtable connectionProperties = new Hashtable<String, Object>(); //jmsConnectionFactoryWrapper.getPropertyHashTable("XMSC_WMQ_HOST_NAME", "XMSC_WMQ_PORT", "XMSC_WMQ_CHANNEL", "XMSC_USERID", "XMSC_PASSWORD");
            connectionProperties.put("transport", testProps.getProperty("mq.transport","MQSeries Client"));
            connectionProperties.put("hostname", testProps.getProperty("mq.hostname","localhost"));
            connectionProperties.put("port", Integer.valueOf(testProps.getProperty("mq.port", "1414") ) );
            connectionProperties.put("channel", testProps.getProperty("mq.channel","MONITOR.SRVCONN"));
            connectionProperties.put("userID", testProps.getProperty("mq.user"));
            connectionProperties.put("password", testProps.getProperty("mq.pass"));
            queueManager = new MQQueueManager("QM1", connectionProperties);
            pcfMessageAgent = new PCFMessageAgent(queueManager);
            pcfMessageAgent.setWaitInterval(5);
        } catch (Exception e) {}
        assumeNotNull( pcfMessageAgent );
    }

    @Test
    public void testPCFMessageAgentSendAPI() throws IOException, MQDataException, MQException {
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
    public void testPCFMessageAgentSendQueueMetrics() throws IOException, MQDataException, MQException {
        PCFMessage message = new PCFMessage(CMQCFC.MQCMD_INQUIRE_Q_STATUS);
        message.addParameter(CMQC.MQCA_Q_NAME, "DEV.QUEUE.1");
        message.addParameter(CMQCFC.MQIACF_Q_STATUS_ATTRS, new int[] { CMQCFC.MQIACF_ALL });
        System.out.println(String.format("request %s",message.toString()));
        PCFMessage[] responses = pcfMessageAgent.send(message);
        for( PCFMessage response : responses ) {
            System.out.println(String.format("response %s", response.toString()));
        }
    }

    @Test
    public void testPCFMessageAgentSendChannelMetrics() throws IOException, MQDataException, MQException {
        PCFMessage message = new PCFMessage(CMQCFC.MQCMD_INQUIRE_CHANNEL_STATUS);
        message.addParameter(CMQCFC.MQCACH_CHANNEL_NAME, "MONITOR.SRVCONN");
        System.out.println(String.format("request %s",message.toString()));
        PCFMessage[] responses = pcfMessageAgent.send(message);
        for( PCFMessage response : responses ) {
            System.out.println(String.format("response %s", response.toString()));
        }
    }

    @Test
    public void testPCFMessageAgentSendTopicMetrics() throws IOException, MQDataException, MQException {
        PCFMessage message = new PCFMessage(CMQCFC.MQCMD_INQUIRE_TOPIC_STATUS);
        //message.addParameter(CMQCFC.MQIACF_TOPIC_STATUS_TYPE, CMQCFC.MQIACF_TOPIC_SUB );
        message.addParameter(CMQC.MQCA_TOPIC_STRING, "#");
        System.out.println(String.format("request %s",message.toString()));
        PCFMessage[] responses = pcfMessageAgent.send(message);
        for( PCFMessage response : responses ) {
            System.out.println(String.format("response %s", response.toString()));
        }
    }
    

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
