package com.cisco.josouthe.monitor;

import com.appdynamics.agent.api.AppdynamicsAgent;
import com.appdynamics.agent.api.Transaction;
import com.appdynamics.apm.appagent.api.DataScope;
import com.appdynamics.instrumentation.sdk.template.AGenericInterceptor;
import com.cisco.josouthe.wrapper.JmsConnectionFactoryWrapper;

import java.util.HashSet;
import java.util.Map;

public abstract class BaseJMSMonitor implements Comparable{
    protected JmsConnectionFactoryWrapper connectionFactoryWrapper;
    protected AGenericInterceptor interceptor;

    public BaseJMSMonitor( AGenericInterceptor aGenericInterceptor, JmsConnectionFactoryWrapper connectionFactoryWrapper) {
        this.connectionFactoryWrapper=connectionFactoryWrapper;
        this.interceptor=aGenericInterceptor;
    }

    public abstract void run();

    protected void collectSnapshotData(String name, String value) {
        collectSnapshotData(AppdynamicsAgent.getTransaction(), name, value);
    }

    protected void collectSnapshotData(Transaction transaction, String name, String value) {
        if (transaction == null) return;
        transaction.collectData(name, value, new HashSet<DataScope>() {{
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
        interceptor.getLogger().debug("Begin reportMetric name: " + metricName + " = " + metricValue + " aggregation type: " + aggregationType + " time rollup type: " + timeRollupType + " cluster rollup type: " + clusterRollupType);
        AppdynamicsAgent.getMetricPublisher().reportMetric(metricName, metricValue, aggregationType, timeRollupType, clusterRollupType);
        interceptor.getLogger().debug("Finish reportMetric name: " + metricName + " = " + metricValue + " aggregation type: " + aggregationType + " time rollup type: " + timeRollupType + " cluster rollup type: " + clusterRollupType);
    }

    @Override
    public int compareTo(Object o) {
        if( o.toString().equals(this.toString()))
            return 0;
        return -1;
    }
}
