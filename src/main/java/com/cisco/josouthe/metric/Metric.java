package com.cisco.josouthe.metric;

public interface Metric {
    String getName();

    String getAggregationType();

    String getTimeRollUpType();

    String getClusterRollUpType();
}
