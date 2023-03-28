package com.theseuntaylor.comp1471cw.model;

public class TraysModel {
    private int id;
    private String name;
    private String time;
    private String date;
    private String traystatus;
    private InstrumentType instrumentType;
    private String cleaningProcessId;

    public String getMedicalProcedureId() {
        return medicalProcedureId;
    }

    public void setMedicalProcedureId(String medicalProcedureId) {
        this.medicalProcedureId = medicalProcedureId;
    }

    private String medicalProcedureId;

    public String getCleaningProcessId() {
        return cleaningProcessId;
    }

    public void setCleaningProcessId(String cleaningProcessId) {
        this.cleaningProcessId = cleaningProcessId;
    }

    public TraysModel(String name) {
        this.name = name;
    }

    public TraysModel(String name, String time, String date, String traystatus, InstrumentType instrumentType, String cleaningProcessId, String medicalProcedureId) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.traystatus = traystatus;
        this.instrumentType = instrumentType;
        this.cleaningProcessId = cleaningProcessId;
        this.medicalProcedureId = medicalProcedureId;
    }

    public TraysModel(int id, String name, String time, String date, String traystatus, InstrumentType instrumentType, String cleaningProcessId, String medicalProcedureId) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.date = date;
        this.traystatus = traystatus;
        this.instrumentType = instrumentType;
        this.cleaningProcessId = cleaningProcessId;
        this.medicalProcedureId = medicalProcedureId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTraystatus() {
        return traystatus;
    }

    public void setTraystatus(String traystatus) {
        this.traystatus = traystatus;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumenttype(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
