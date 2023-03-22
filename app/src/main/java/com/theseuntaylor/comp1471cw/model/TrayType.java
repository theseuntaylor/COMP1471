package com.theseuntaylor.comp1471cw.model;

public class TrayType {
    private String trayId;
    private String dateOfLastSterilisation;
    private String timeOfLastSterilisation;
    private String trayStatus;

    // foreign key
    private String cleaningProcess;

    public String getTrayId() {
        return trayId;
    }

    public void setTrayId(String trayId) {
        this.trayId = trayId;
    }

    public String getDateOfLastSterilisation() {
        return dateOfLastSterilisation;
    }

    public void setDateOfLastSterilisation(String dateOfLastSterilisation) {
        this.dateOfLastSterilisation = dateOfLastSterilisation;
    }

    public String getTimeOfLastSterilisation() {
        return timeOfLastSterilisation;
    }

    public void setTimeOfLastSterilisation(String timeOfLastSterilisation) {
        this.timeOfLastSterilisation = timeOfLastSterilisation;
    }

    public String getTrayStatus() {
        return trayStatus;
    }

    public void setTrayStatus(String trayStatus) {
        this.trayStatus = trayStatus;
    }

    public String getCleaningProcess() {
        return cleaningProcess;
    }

    public void setCleaningProcess(String cleaningProcess) {
        this.cleaningProcess = cleaningProcess;
    }
}
