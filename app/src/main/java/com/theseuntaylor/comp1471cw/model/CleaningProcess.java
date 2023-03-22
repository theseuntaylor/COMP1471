package com.theseuntaylor.comp1471cw.model;

public class CleaningProcess {
    private String cleaningProcessId;
    public String cleaningProcessName;
    private String sterilisationTypeId;
    private String operatorId;

    public String getCleaningProcessId() {
        return cleaningProcessId;
    }

    public void setCleaningProcessId(String cleaningProcessId) {
        this.cleaningProcessId = cleaningProcessId;
    }

    public String getCleaningProcessName() {
        return cleaningProcessName;
    }

    public void setCleaningProcessName(String cleaningProcessName) {
        this.cleaningProcessName = cleaningProcessName;
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
