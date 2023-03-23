package com.theseuntaylor.comp1471cw.model;

public class TraysModel {
    private int id;


    //   private String trayid;
    private String name;
    private String time;
    private String date;
    private String traystatus;
    private String instrumenttype;
    private String cleaningProcessId;

    public String getCleaningProcessId() {
        return cleaningProcessId;
    }

    public void setCleaningProcessId(String cleaningProcessId) {
        this.cleaningProcessId = cleaningProcessId;
    }

    public TraysModel(String name, String time, String date, String traystatus, String instrumenttype, String cleaningProcessId) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.traystatus = traystatus;
        this.instrumenttype = instrumenttype;
        this.cleaningProcessId = cleaningProcessId;
    }

    public TraysModel(int id, String name, String time, String date, String traystatus, String instrumenttype, String cleaningProcessId) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.date = date;
        this.traystatus = traystatus;
        this.instrumenttype = instrumenttype;
        this.cleaningProcessId = cleaningProcessId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public String getTrayid() {
//        return trayid;
//    }
//
//    public void setTrayid(String trayid) {
//        this.trayid = trayid;
//    }

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

    public String getInstrumenttype() {
        return instrumenttype;
    }

    public void setInstrumenttype(String instrumenttype) {
        this.instrumenttype = instrumenttype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
