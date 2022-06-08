package com.cisco.josouthe.monitor;

import junit.framework.TestCase;

import java.net.MalformedURLException;
import java.net.URI;

public class MQMonitorTest extends TestCase {

    public void testGetQueueName() {
        String queueName = "queue:///DEV.QUEUE.1";
        String finalName = queueName;
        try {
            URI url = new URI(queueName);
            finalName = url.getPath();
        } catch (Exception exception) {
            System.out.println(String.format("Error attempting to get path from '%s' Exception: %s", queueName, exception.toString()));
        }
        finalName = (finalName.startsWith("/") ? finalName.substring(1) : finalName);
        System.out.println(String.format("in '%s' -> '%s' out",queueName, finalName));
        assert "DEV.QUEUE.1".equals(finalName);
    }
}