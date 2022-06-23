package com.cisco.josouthe.monitor;

import com.appdynamics.agent.api.AppdynamicsAgent;
import com.appdynamics.agent.api.Transaction;
import com.appdynamics.apm.appagent.api.DataScope;
import com.appdynamics.instrumentation.sdk.ASDKPlugin;
import com.appdynamics.instrumentation.sdk.logging.ISDKLogger;
import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.cisco.josouthe.wrapper.JmsConnectionFactoryWrapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public abstract class BaseJMSMonitor implements Comparable{
    protected ASDKPlugin interceptor;
    protected ISDKLogger logger;
    protected Set<String> queues = new HashSet<>();
    protected Set<String> channels = new HashSet<>();
    protected Set<String> topics = new HashSet<>();
    private String key;
    private Date creationTime;
    private long lastRunTimestamp;
    private String metricPrefix = "Custom Metrics|";
    private SimpleDateFormat mqDateFormatter;

    public BaseJMSMonitor( ASDKPlugin aGenericInterceptor, String key) {
        this.interceptor=aGenericInterceptor;
        this.key=key;
        this.creationTime= new Date();
        this.logger = interceptor.getLogger();
        mqDateFormatter = new SimpleDateFormat("yyyy-M-d H.m.s");
        mqDateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public abstract void run();

    public void runIt() {
        this.lastRunTimestamp = System.currentTimeMillis();
        run();
    }

    public String toString() {
        return String.format("%s %d queues: %s last run %d ms ago", this.key, this.queues.size(), this.queues, System.currentTimeMillis()-this.lastRunTimestamp );
    }

    public synchronized void addQueue( String name ) { queues.add(name); }
    public synchronized void addChannel( String name ) { channels.add(name); }
    public synchronized void addTopic(String name) { topics.add(name); }

    protected void collectSnapshotData(String name, Object value) {
        collectSnapshotData(AppdynamicsAgent.getTransaction(), name, value);
    }

    protected void collectSnapshotData(Transaction transaction, String name, Object value) {
        if (transaction == null) return;
        transaction.collectData(name, String.valueOf(value), new HashSet<DataScope>() {{
            add(DataScope.SNAPSHOTS);
        }});
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
    protected void publishEvent(String eventSummary, String severity, String eventType, Map<String, String> details) {
        interceptor.getLogger().debug("Begin publishEvent event summary: " + eventSummary + " severity: " + severity + " event type: " + eventType);
        AppdynamicsAgent.getEventPublisher().publishEvent(eventSummary, severity, eventType, details);
        interceptor.getLogger().debug("Finish publishEvent event summary: " + eventSummary + " severity: " + severity + " event type: " + eventType);
    }

    /*
        { "operation": "reportMetric", "name": "Name|of|Metric|Pipe|Delimited", "value": longValue(NOT_A_STRING!),
                "aggregationType": "Values allowed: [AVERAGE, ADVANCED_AVERAGE, SUM, OBSERVATION, OBSERVATION_FOREVERINCREASING]",
                "timeRollupType": "Values allowed: [AVERAGE, SUM, CURRENT]",
                "clusterRollupType": "Values allowed: [INDIVIDUAL, COLLECTIVE]" }
         */
    protected void reportMetric(String metricName, long metricValue, String aggregationType, String timeRollupType, String clusterRollupType) {
        interceptor.getLogger().debug("reportMetric name: " + metricPrefix + metricName + " = " + metricValue + " aggregation type: " + aggregationType + " time rollup type: " + timeRollupType + " cluster rollup type: " + clusterRollupType);
        AppdynamicsAgent.getMetricPublisher().reportMetric(metricPrefix + metricName, metricValue, aggregationType, timeRollupType, clusterRollupType);
    }

    @Override
    public int compareTo(Object o) {
        if( o.toString().equals(this.toString()))
            return 0;
        return -1;
    }

    public long getTimeStamp( String dateString, String timeString ) throws ParseException {
        return mqDateFormatter.parse(String.format("%s %s Z",dateString, timeString)).getTime();
    }

    public long getRelativeTime(long timestamp) {
        return Instant.now().toEpochMilli() - timestamp;
    }
}
