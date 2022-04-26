package com.cisco.josouthe.metric;

public class IBMMQMetric implements Metric {
    private String name;
    private String ibmConstantName;
    private String aggregationType="OBSERVATION";
    private String timeRollUpType="AVERAGE";
    private String clusterRollUpType="INDIVIDUAL";
    private Integer ibmConstantInt;

    public IBMMQMetric( String name, String ibmConstantName, Integer ibmConstantInt, String aggregationType, String timeRollUpType, String clusterRollUpType) {
        this.name=name;
        this.ibmConstantName=ibmConstantName;
        this.ibmConstantInt=ibmConstantInt;
        if( aggregationType != null ) this.aggregationType=aggregationType;
        if( timeRollUpType != null ) this.timeRollUpType=timeRollUpType;
        if( clusterRollUpType != null ) this.clusterRollUpType=clusterRollUpType;
    }


    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIbmConstantName() {
        return ibmConstantName;
    }

    public void setIbmConstantName(String ibmConstantName) {
        this.ibmConstantName = ibmConstantName;
    }

    @Override
    public String getAggregationType() {
        return aggregationType;
    }

    public void setAggregationType(String aggregationType) {
        this.aggregationType = aggregationType;
    }

    @Override
    public String getTimeRollUpType() {
        return timeRollUpType;
    }

    public void setTimeRollUpType(String timeRollUpType) {
        this.timeRollUpType = timeRollUpType;
    }

    @Override
    public String getClusterRollUpType() {
        return clusterRollUpType;
    }

    public void setClusterRollUpType(String clusterRollUpType) {
        this.clusterRollUpType = clusterRollUpType;
    }

    public Integer getIbmConstantInt() {
        return ibmConstantInt;
    }

    public void setIbmConstantInt(Integer ibmConstantInt) {
        this.ibmConstantInt = ibmConstantInt;
    }
}
