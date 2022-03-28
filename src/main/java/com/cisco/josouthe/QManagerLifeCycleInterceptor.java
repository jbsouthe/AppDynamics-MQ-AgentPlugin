package com.cisco.josouthe;

import com.appdynamics.instrumentation.sdk.Rule;
import com.appdynamics.instrumentation.sdk.SDKClassMatchType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class QManagerLifeCycleInterceptor extends MyBaseInterceptor {
    private Scheduler scheduler = null;
    ConcurrentHashMap<Object, MQMonitor> queueManagerMap = null;

    public QManagerLifeCycleInterceptor() {
        super();
        getLogger().info("Initialized MQ Monitor on Agent");
    }

    @Override
    public Object onMethodBegin(Object objectIntercepted, String className, String methodName, Object[] params) {
        if( methodName.equals("disconnect") && scheduler != null ) { //we want to remove this queue manager before the disconnect runs to be safe
            getLogger().debug("A com.ibm.mq.MQQueueManager is disconnecting, we are running, will attempt to remove it from the scheduler for monitoring");
            queueManagerMap.remove(objectIntercepted);
        }
        return null;
    }

    @Override
    public void onMethodEnd(Object state, Object objectIntercepted, String className, String methodName, Object[] params, Throwable exception, Object returnVal) {
        if( methodName.equals("<init>") ) { //we want to add this queue manager right after it is created
            if( scheduler == null ) initializeScheduler();
            queueManagerMap.put(objectIntercepted, new MQMonitor(objectIntercepted, getLogger()) );
        }
    }

    private void initializeScheduler() {
        if( queueManagerMap == null ) queueManagerMap = new ConcurrentHashMap<Object, MQMonitor>();
        if( scheduler != null ) return;
        scheduler = Scheduler.getInstance(60, queueManagerMap);
        scheduler.start();
    }

    @Override
    public List<Rule> initializeRules() {
        //com.ibm.mq.MQQueueManager <init> and .disconnect()
        ArrayList<Rule> rules = new ArrayList<Rule>();
        rules.add(new Rule.Builder("com.ibm.mq.MQQueueManager")
                .classMatchType(SDKClassMatchType.MATCHES_CLASS)
                .methodMatchString("<init>")
                .build());
        rules.add(new Rule.Builder("com.ibm.mq.MQQueueManager")
                .classMatchType(SDKClassMatchType.MATCHES_CLASS)
                .methodMatchString("disconnect")
                .build());
        return rules;
    }
}
