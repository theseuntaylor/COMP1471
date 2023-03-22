package com.theseuntaylor.comp1471cw.model;

public class CleaningProcess {
    private int processId;
    private String processName;
    private int stepId;

    public CleaningProcess(int processId, String processName, int stepId) {
        this.processId = processId;
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

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

}
