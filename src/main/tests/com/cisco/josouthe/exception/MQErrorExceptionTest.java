package com.cisco.josouthe.exception;

import com.cisco.josouthe.util.ExceptionUtility;
import com.ibm.mq.MQException;
import com.ibm.mq.headers.MQExceptionWrapper;
import junit.framework.TestCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MQErrorExceptionTest extends TestCase {

    public void testExceptionCreation() throws Exception {
        Pattern mqExceptionMessagePattern = Pattern.compile("^(?<errorType>.+): Completion Code \\'(?<completionCode>\\d+)\\', Reason \\'(?<reasonCode>\\d+)\\'\\.");

        MQExceptionWrapper mqExceptionWrapper = new MQExceptionWrapper(new MQException("MQJE001: Completion Code '2', Reason '2033'.", "MessageID", 2033, 2) );
        System.out.println(String.format("Exception Message: %s", mqExceptionWrapper.getMessage()));
        Matcher matcher = mqExceptionMessagePattern.matcher(mqExceptionWrapper.getMessage());
        if( matcher.find() ) {
            String errorType = matcher.group("errorType");
            int completionCode = Integer.parseInt(matcher.group("completionCode"));
            int reasonCode = Integer.parseInt(matcher.group("reasonCode"));
            System.out.println(String.format("Error Type: %s completion: %d reason: %d",errorType,completionCode,reasonCode));
        }
        MQErrorException mqErrorException = ExceptionUtility.processException(mqExceptionWrapper);
        System.out.println(String.format("Exception Message: %s", mqErrorException.getMessage()));
        mqErrorException.printStackTrace(System.out);
    }

}