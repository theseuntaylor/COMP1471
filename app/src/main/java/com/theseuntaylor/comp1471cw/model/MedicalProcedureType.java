package com.theseuntaylor.comp1471cw.model;

public class MedicalProcedureType {
    private String medicalProcedureId;
    private String medicalProcedureName;

    // fks
    private String trayId;
    private String patientId;

    public String getMedicalProcedureId() {
        return medicalProcedureId;
    }

    public void setMedicalProcedureId(String medicalProcedureId) {
        this.medicalProcedureId = medicalProcedureId;
    }

    public String getMedicalProcedureName() {
        return medicalProcedureName;
    }

    public void setMedicalProcedureName(String medicalProcedureName) {
        this.medicalProcedureName = medicalProcedureName;
    }

    public String getTrayId() {
        return trayId;
    }

    public void setTrayId(String trayId) {
        this.trayId = trayId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
