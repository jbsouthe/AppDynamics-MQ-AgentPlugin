package com.cisco.josouthe;

import com.appdynamics.instrumentation.sdk.Rule;
import com.appdynamics.instrumentation.sdk.SDKClassMatchType;
import com.appdynamics.instrumentation.sdk.SDKStringMatchType;
import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class QManagerLifeCycleInterceptor extends AGenericInterceptor {
    private static String MQQUEUEMANAGER = "com.ibm.mq.MQQueueManager";
    private Scheduler scheduler = null;
    ConcurrentHashMap<Object, MQMonitor> queueManagerObjectMap = null;
    ConcurrentHashMap<String, MQMonitor> queueManagerNameMap = null;

    public QManagerLifeCycleInterceptor() {
        super();
        getLogger().info("Initialized MQ Monitor on Agent");
    }

    @Override
    public Object onMethodBegin(Object objectIntercepted, String className, String methodName, Object[] params) {
        getLogger().info(String.format("onMethodBegin called for %s.%s( %s ) object: %s",className, methodName, printParameters(params), String.valueOf(objectIntercepted)));
        if( className.equals(MQQUEUEMANAGER) && methodName.equals("disconnect") && scheduler != null ) { //we want to remove this queue manager before the disconnect runs to be safe
            getLogger().info("A com.ibm.mq.MQQueueManager is disconnecting, we are running, will attempt to remove it from the scheduler for monitoring");
            queueManagerNameMap.remove( queueManagerObjectMap.get(objectIntercepted).getQueueManagerName() );
            MQMonitor mqMonitor = queueManagerObjectMap.remove(objectIntercepted);
            getLogger().info("Removed "+ String.valueOf(mqMonitor) );
            return null;
        }
        return null;
    }

    private Object printParameters(Object[] params) {
        if( params==null || params.length==0 ) return "";
        StringBuilder sb = new StringBuilder();
        for( int i=0; i< params.length; i++) {
            sb.append("'");
            sb.append(params[i]);
            sb.append("'");
            if( i< params.length-1) sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public void onMethodEnd(Object state, Object objectIntercepted, String className, String methodName, Object[] params, Throwable exception, Object returnVal) {
        getLogger().info(String.format("onMethodEnd called for %s.%s( %s ) object: %s",className, methodName, printParameters(params), String.valueOf(objectIntercepted)));

        if( className.equals(MQQUEUEMANAGER) && methodName.equals("<init>") ) { //we want to add this queue manager right after it is created
            if( scheduler == null ) initializeScheduler();
            String queueManagerName = (String) params[0];
            queueManagerObjectMap.put(objectIntercepted, new MQMonitor(queueManagerName, objectIntercepted, getLogger(), this) );
            queueManagerNameMap.put( queueManagerName, queueManagerObjectMap.get(objectIntercepted));
            return;
        }
        if( className.equals(MQQUEUEMANAGER) && methodName.equals("accessQueue") ) {
            String queueName = (String) params[0];
            int openOptionsArg = (Integer) params[1];
            MQMonitor mqMonitor = queueManagerObjectMap.get(objectIntercepted);
            if( mqMonitor != null ) {
                getLogger().info("Adding queue to monitor: "+ queueName +" openOptions: "+ openOptionsArg);
                mqMonitor.addQueueToMonitorMQQueue(queueName, openOptionsArg, returnVal);
                getLogger().info("Added queue to monitor: "+ queueName +" openOptions: "+ openOptionsArg);
            }
            return;
        }
        if( className.equals(MQQUEUEMANAGER) && methodName.equals("put") ) {
            if( params.length == 5 && "com.ibm.mq.MQMessage".equals(params[4].getClass().getCanonicalName()) ) {
                int type = (Integer) params[0];
                String qName = (String) params[1];
                String qmName = (String) params[2];
                String topicString = (String) params[3];
                if (type == 1 ) { //CMQC.MQOT_Q
                    MQMonitor mqMonitor = queueManagerNameMap.get(qmName);
                    if( mqMonitor != null ) {
                        getLogger().info("Adding queue to monitor: "+ qName +" with topic: "+ topicString);
                        mqMonitor.addQueueToMonitorMQMessage(qName, topicString, params[4]);
                        getLogger().info("Added queue to monitor: "+ qName +" with topic: "+ topicString);
                    }
                }
            } else {
                String qName = (String) params[0];
                String qmName = (String) params[1];
                MQMonitor mqMonitor = queueManagerNameMap.get(qmName);
                if( mqMonitor != null ) {
                    mqMonitor.addQueueToMonitorMQMessage(qName, null, params[2] );
                }
            }
        }
    }

    private void initializeScheduler() {
        if( scheduler != null ) return;
        if( queueManagerObjectMap == null ) queueManagerObjectMap = new ConcurrentHashMap<>();
        if( queueManagerNameMap == null ) queueManagerNameMap = new ConcurrentHashMap<>();
        scheduler = Scheduler.getInstance(60, queueManagerObjectMap);
        scheduler.start();
        getLogger().info("Initialized Scheduler to monitor MQ");

    }

    @Override
    public List<Rule> initializeRules() {
        //com.ibm.mq.MQQueueManager <init> and .disconnect() https://www.ibm.com/docs/api/v1/content/SSFKSJ_9.2.0/com.ibm.mq.javadoc.doc/WMQJavaClasses/com/ibm/mq/MQQueueManager.html
        ArrayList<Rule> rules = new ArrayList<Rule>();
        for( String method : new String[]{ "<init>", "disconnect", "accessQueue"})
        rules.add(new Rule.Builder(MQQUEUEMANAGER)
                .classMatchType(SDKClassMatchType.MATCHES_CLASS)
                .methodMatchString(method)
                .methodStringMatchType(SDKStringMatchType.EQUALS)
                .build());

        rules.add(new Rule.Builder(MQQUEUEMANAGER)
                .classMatchType(SDKClassMatchType.MATCHES_CLASS)
                .methodMatchString("put")
                .methodStringMatchType(SDKStringMatchType.EQUALS)
                .withParams("java.lang.Integer", "java.lang.String", "java.lang.String", "java.lang.String", "com.ibm.mq.MQMessage")
                .build());
        rules.add(new Rule.Builder(MQQUEUEMANAGER)
                .classMatchType(SDKClassMatchType.MATCHES_CLASS)
                .methodMatchString("put")
                .methodStringMatchType(SDKStringMatchType.EQUALS)
                .withParams("java.lang.Integer", "java.lang.String", "java.lang.String", "java.lang.String", "com.ibm.mq.MQMessage", "com.ibm.mq.MQPutMessageOptions")
                .build());
        rules.add(new Rule.Builder(MQQUEUEMANAGER)
                .classMatchType(SDKClassMatchType.MATCHES_CLASS)
                .methodMatchString("put")
                .methodStringMatchType(SDKStringMatchType.EQUALS)
                .withParams("java.lang.String", "java.lang.String", "com.ibm.mq.MQMessage")
                .build());
        rules.add(new Rule.Builder(MQQUEUEMANAGER)
                .classMatchType(SDKClassMatchType.MATCHES_CLASS)
                .methodMatchString("put")
                .methodStringMatchType(SDKStringMatchType.EQUALS)
                .withParams("java.lang.String", "java.lang.String", "com.ibm.mq.MQMessage", "com.ibm.mq.MQPutMessageOptions")
                .build());
        rules.add(new Rule.Builder(MQQUEUEMANAGER)
                .classMatchType(SDKClassMatchType.MATCHES_CLASS)
                .methodMatchString("put")
                .methodStringMatchType(SDKStringMatchType.EQUALS)
                .withParams("java.lang.String", "java.lang.String", "com.ibm.mq.MQMessage", "com.ibm.mq.MQPutMessageOptions", "java.lang.String")
                .build());
        return rules;
    }
}
