package com.theseuntaylor.comp1471cw.model;

public class Steps {
    private int stepId;

    public Steps(int stepId, String stepName, String sterilisationMachineId, String replacementneeded) {
        this.stepId = stepId;
        this.stepName = stepName;
        this.sterilisationMachineId = sterilisationMachineId;
        this.replacementneeded = replacementneeded;
    }

    public String stepName;
    private String sterilisationMachineId;
    private  String replacementneeded;

    public String getReplacementneeded() {
        return replacementneeded;
    }

    public void setReplacementneeded(String replacementneeded) {
        this.replacementneeded = replacementneeded;
    }




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


    public String getSterilisationMachineId() {
        return sterilisationMachineId;
    }

    public void setSterilisationMachineId(String sterilisationMachineId) {
        this.sterilisationMachineId = sterilisationMachineId;
    }


}
