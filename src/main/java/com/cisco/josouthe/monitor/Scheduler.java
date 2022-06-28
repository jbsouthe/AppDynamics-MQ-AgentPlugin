package com.cisco.josouthe.monitor;

import com.appdynamics.instrumentation.sdk.logging.ISDKLogger;

import java.util.concurrent.ConcurrentHashMap;

public class Scheduler extends Thread {
    private static final String THREAD_NAME = "AppDynamics MQ Monitor Thread";
    private ISDKLogger logger;
    ConcurrentHashMap<String, BaseJMSMonitor> monitors = null;
    long sleepTime = 30000;
    private static Scheduler instance = null;

    public synchronized static Scheduler getInstance(long sleepTimeMS, ConcurrentHashMap<String, BaseJMSMonitor> monitors, ISDKLogger isdkLogger) {
        if( instance == null )
            instance = new Scheduler(isdkLogger);
        if( sleepTimeMS > 30000 ) { //safety check, we aren't going faster than this
            instance.sleepTime = sleepTimeMS;
        }
        isdkLogger.info(String.format("Setting sleep time between monitoring intervals to %d(ms). Set this with argument -DIBMMQAgentPlugin.sleepTimeMS=#. Default and minimum is 30000ms", instance.sleepTime));
        instance.monitors = monitors;
        return instance;
    }

    private Scheduler(ISDKLogger isdkLogger) {
        this.logger = isdkLogger;
        setDaemon(true);
        try {
            setPriority( (int)getPriority()/2 );
        } catch (Exception e) {
            //we tried, no op
        }
        setName(THREAD_NAME);
        logger.debug("Created a new instance of the Scheduler to run monitors");
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