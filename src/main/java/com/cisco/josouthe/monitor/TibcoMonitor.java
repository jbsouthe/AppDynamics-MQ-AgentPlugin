package com.cisco.josouthe.monitor;

import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.cisco.josouthe.wrapper.tibco.TibjmsConnection;

public class TibcoMonitor extends BaseJMSMonitor {
    TibjmsConnection tibjmsConnection;

    public TibcoMonitor(ASDKPlugin aGenericInterceptor, String key, TibjmsConnection connection ) {
        super(aGenericInterceptor, key);
        this.tibjmsConnection = connection;
    }

    @Override
    public void run() {
        logger.debug(String.format("TIBCO JMS Monitor run method called for %s", tibjmsConnection.toString()));
    }
}
