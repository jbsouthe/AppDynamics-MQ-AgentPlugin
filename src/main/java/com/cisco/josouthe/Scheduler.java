package com.cisco.josouthe;

import com.cisco.josouthe.monitor.BaseJMSMonitor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class Scheduler extends Thread {
    private static final String THREAD_NAME = "AppDynamics MQ Monitor Thread";
    ConcurrentHashMap<String, BaseJMSMonitor> monitors = null;
    long sleepTime = 30000;
    private static Scheduler instance = null;

    public synchronized static Scheduler getInstance(long sleepTimeMS, ConcurrentHashMap<String, BaseJMSMonitor> monitors ) {
        if( instance == null )
            instance = new Scheduler();
        if( sleepTimeMS > 30000 ) instance.sleepTime = sleepTimeMS; //safety check, we aren't going faster than this
        instance.monitors = monitors;
        return instance;
    }

    private Scheduler() {
        setDaemon(true);
        try {
            setPriority( (int)getPriority()/2 );
        } catch (Exception e) {
            //we tried, no op
        }
        setName(THREAD_NAME);
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while(true) {
            //run the monitors
            for( BaseJMSMonitor monitor : monitors.values() ) {
                monitor.runIt();
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                //no op
            }
        }
    }
}