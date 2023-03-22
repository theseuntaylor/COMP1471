package com.theseuntaylor.comp1471cw.model;

public class SterilisationType {
    private String sterilisationTypeId;
    private String cleaningName;
    private Boolean isReplacementNeeded;
    // fks
    private Boolean sterilisationMachineId;

    public String getSterilisationTypeId() {
        return sterilisationTypeId;
    }

    public void setSterilisationTypeId(String sterilisationTypeId) {
        this.sterilisationTypeId = sterilisationTypeId;
    }

    public String getCleaningName() {
        return cleaningName;
    }

    public void setCleaningName(String cleaningName) {
        this.cleaningName = cleaningName;
    }

    public Boolean getReplacementNeeded() {
        return isReplacementNeeded;
    }

    public void setReplacementNeeded(Boolean replacementNeeded) {
        isReplacementNeeded = replacementNeeded;
    }

    public Boolean getSterilisationMachineId() {
        return sterilisationMachineId;
    }

    public void setSterilisationMachineId(Boolean sterilisationMachineId) {
        this.sterilisationMachineId = sterilisationMachineId;
    }
}
