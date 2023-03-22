package com.theseuntaylor.comp1471cw.model;

public class SterilisationOperator {
    private String operatorId;
    public String operatorName;

    public SterilisationOperator(String operatorId, String operatorName) {
        this.operatorId = operatorId;
        this.operatorName = operatorName;
    }

    public SterilisationOperator(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
