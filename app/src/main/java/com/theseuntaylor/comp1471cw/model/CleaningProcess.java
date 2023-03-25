package com.theseuntaylor.comp1471cw.model;

public class CleaningProcess {
    private int officerId;
    private String processName;
    private int stepId;

    public CleaningProcess(int officerId, String processName, int stepId) {
        this.officerId = officerId;
        this.processName = processName;
        this.stepId = stepId;
    }

    public CleaningProcess(String processName, int stepId) {
        this.processName = processName;
        this.stepId = stepId;
    }

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public int getOfficerId() {
        return officerId;
    }

    public void setOfficerId(int officerId) {
        this.officerId = officerId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

}
