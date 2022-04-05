package com.cisco.josouthe;

import com.appdynamics.agent.api.AppdynamicsAgent;
import com.appdynamics.agent.api.Transaction;
import com.appdynamics.apm.appagent.api.DataScope;
import com.appdynamics.instrumentation.sdk.logging.ISDKLogger;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;

public class MQMonitor {
    private MQQueueManager queueManagerObject;
    private String queueManagerName;
    private ISDKLogger logger;
    private ConcurrentSkipListSet<MQQueueMonitor> queueMonitors;
    private long creationTime;

    public MQMonitor(String queueManagerName, Object queueManagerObject, ISDKLogger logger) {
        this.creationTime = System.currentTimeMillis();
        this.queueManagerObject = (MQQueueManager) queueManagerObject;
        this.queueManagerName = queueManagerName;
        this.logger = logger;
        queueMonitors = new ConcurrentSkipListSet<>();
    }

    public String getQueueManagerName() { return queueManagerName; }

    public String toString() {
        return String.format("MQMonitor Queue Manager '%s' #of Queues monitoring: %d Age of Queue Manager: %d (ms)", this.queueManagerName, this.queueMonitors.size(), System.currentTimeMillis()-this.creationTime);
    }

    private boolean ignoreQueueNamed( String qName ) {
        //TODO get config and allow customers to build a black/white list
        if( qName.toUpperCase().startsWith("SYSTEM") ) return true;
        return false;
    }

    public void addQueueToMonitor(String queueName, int openOptionsArg, MQQueue mqQueue) {
        if( ignoreQueueNamed( queueName ) ) {
            logger.info("Ignoring Queue: "+ queueName);
            return;
        }
        logger.info(String.format("Add Queue called for %s open options arg: '%d'", queueName, openOptionsArg));
        this.queueMonitors.add( new MQQueueMonitor(queueName, openOptionsArg, mqQueue) );
        try {
            logger.info(String.format("Test pulling queue depth=%d and max=%d for %s",mqQueue.getCurrentDepth(), mqQueue.getMaximumDepth(), queueName));
        } catch (MQException e) {
            logger.info("MQException tring to test queue depth pull: "+e.toString());
        }
    }

    public void addQueueToMonitor(String queueName, String topicString, MQMessage mqMessage) {
        if( ignoreQueueNamed( queueName ) ) {
            logger.info("Ignoring Queue: "+ queueName);
            return;
        }
        logger.info(String.format("Add Queue called for %s topic: '%s' NOT YET IMPLEMENTED", queueName, topicString));
    }

    public void run() {
        //go get some status stuff and use the AppdynamicsAgent to send some metrics, events, and analytics data
        logger.info(String.format("Run method called for %s monitor, nothing yet to do, %s", getQueueManagerName(), this.toString()));
    }

    public Object getKey() { return queueManagerObject; }

    protected void collectSnapshotData(String name, String value ) {
        collectSnapshotData(AppdynamicsAgent.getTransaction(), name, value);
    }

    protected void collectSnapshotData(Transaction transaction, String name, String value ) {
        if(transaction == null) return;
        transaction.collectData( name, value, new HashSet<DataScope>(){{add(DataScope.SNAPSHOTS);}} );
    }

    /*
                { "operation": "publishEvent", "eventSummary": "Summary Text of Event", "severity": "INFO, WARN, ERROR" "eventType": "String Type of Event", "details": [ { "key": "the name of the detail", "value": "the value of the detail" } ] }
                eventType - the type of the event. Values allowed: [ERROR, APPLICATION_ERROR, APPLICATION_INFO, STALL, BT_SLA_VIOLATION, DEADLOCK, MEMORY_LEAK, MEMORY_LEAK_DIAGNOSTICS, LOW_HEAP_MEMORY, ALERT, CUSTOM, APP_SERVER_RESTART, BT_SLOW,
                                                                    SYSTEM_LOG, INFO_INSTRUMENTATION_VISIBILITY, AGENT_EVENT, INFO_BT_SNAPSHOT, AGENT_STATUS, SERIES_SLOW, SERIES_ERROR, ACTIVITY_TRACE, OBJECT_CONTENT_SUMMARY, DIAGNOSTIC_SESSION,
                                                                    HIGH_END_TO_END_LATENCY, APPLICATION_CONFIG_CHANGE, APPLICATION_DEPLOYMENT, AGENT_DIAGNOSTICS, MEMORY, LICENSE, CONTROLLER_AGENT_VERSION_INCOMPATIBILITY, CONTROLLER_EVENT_UPLOAD_LIMIT_REACHED,
                                                                    CONTROLLER_RSD_UPLOAD_LIMIT_REACHED, CONTROLLER_METRIC_REG_LIMIT_REACHED, CONTROLLER_ERROR_ADD_REG_LIMIT_REACHED, CONTROLLER_ASYNC_ADD_REG_LIMIT_REACHED, AGENT_METRIC_REG_LIMIT_REACHED,
                                                                    AGENT_ADD_BLACKLIST_REG_LIMIT_REACHED, AGENT_ASYNC_ADD_REG_LIMIT_REACHED, AGENT_ERROR_ADD_REG_LIMIT_REACHED, AGENT_METRIC_BLACKLIST_REG_LIMIT_REACHED, DISK_SPACE, INTERNAL_UI_EVENT,
                                                                    APPDYNAMICS_DATA, APPDYNAMICS_INTERNAL_DIAGNOSTICS, APPDYNAMICS_CONFIGURATION_WARNINGS, AZURE_AUTO_SCALING, POLICY_OPEN, POLICY_OPEN_WARNING, POLICY_OPEN_CRITICAL, POLICY_CLOSE,
                                                                    POLICY_UPGRADED, POLICY_DOWNGRADED, RESOURCE_POOL_LIMIT, THREAD_DUMP_ACTION_STARTED, EUM_CLOUD_BROWSER_EVENT, THREAD_DUMP_ACTION_END, THREAD_DUMP_ACTION_FAILED, RUN_LOCAL_SCRIPT_ACTION_STARTED,
                                                                    RUN_LOCAL_SCRIPT_ACTION_END, RUN_LOCAL_SCRIPT_ACTION_FAILED, RUNBOOK_DIAGNOSTIC_SESSION_STARTED, RUNBOOK_DIAGNOSTIC_SESSION_END, RUNBOOK_DIAGNOSTIC_SESSION_FAILED, CUSTOM_ACTION_STARTED,
                                                                    CUSTOM_ACTION_END, CUSTOM_ACTION_FAILED, WORKFLOW_ACTION_STARTED, WORKFLOW_ACTION_END, WORKFLOW_ACTION_FAILED, NORMAL, SLOW, VERY_SLOW, BUSINESS_ERROR, ALREADY_ADJUDICATED,
                                                                    ADJUDICATION_CANCELLED, EMAIL_SENT, SMS_SENT]
     */
    protected  void publishEvent( String eventSummary, String severity, String eventType, Map<String,String> details ) {
        logger.debug("Begin publishEvent event summary: "+eventSummary+" severity: "+ severity +" event type: "+ eventType);
        AppdynamicsAgent.getEventPublisher().publishEvent(eventSummary, severity, eventType, details);
        logger.debug("Finish publishEvent event summary: "+eventSummary+" severity: "+ severity +" event type: "+ eventType);
    }

    /*
    { "operation": "reportMetric", "name": "Name|of|Metric|Pipe|Delimited", "value": longValue(NOT_A_STRING!),
            "aggregationType": "Values allowed: [AVERAGE, ADVANCED_AVERAGE, SUM, OBSERVATION, OBSERVATION_FOREVERINCREASING]",
            "timeRollupType": "Values allowed: [AVERAGE, SUM, CURRENT]",
            "clusterRollupType": "Values allowed: [INDIVIDUAL, COLLECTIVE]" }
     */
    protected  void reportMetric( String metricName, long metricValue, String aggregationType, String timeRollupType, String clusterRollupType ) {
        logger.debug("Begin reportMetric name: "+ metricName +" = "+ metricValue +" aggregation type: "+ aggregationType + " time rollup type: "+ timeRollupType +" cluster rollup type: "+ clusterRollupType);
        AppdynamicsAgent.getMetricPublisher().reportMetric(metricName, metricValue, aggregationType, timeRollupType, clusterRollupType );
        logger.debug("Finish reportMetric name: "+ metricName +" = "+ metricValue +" aggregation type: "+ aggregationType + " time rollup type: "+ timeRollupType +" cluster rollup type: "+ clusterRollupType);
    }
}
