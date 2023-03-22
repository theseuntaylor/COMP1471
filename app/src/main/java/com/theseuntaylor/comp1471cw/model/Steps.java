package com.theseuntaylor.comp1471cw.model;

public class Steps {
    private int stepId;
    public String stepName;
    public int cleaningProcessId;
    private String sterilisationTypeId;
    private String operatorId;

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public int getCleaningProcessId() {
        return cleaningProcessId;
    }

    public void setCleaningProcessId(int cleaningProcessId) {
        this.cleaningProcessId = cleaningProcessId;
    }

    public String getSterilisationTypeId() {
        return sterilisationTypeId;
    }

    public void setSterilisationTypeId(String sterilisationTypeId) {
        this.sterilisationTypeId = sterilisationTypeId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
}
