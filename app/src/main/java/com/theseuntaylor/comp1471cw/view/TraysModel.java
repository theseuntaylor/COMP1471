package com.theseuntaylor.comp1471cw.view;

public class TraysModel {
    private long id = -1;
 //   private String trayid;
    private String time;
    private String date;
    private String traystatus;
    private String instrumenttype;

    public TraysModel(String time, String date, String traystatus, String instrumenttype) {
        this.time = time;
        this.date = date;
        this.traystatus = traystatus;
        this.instrumenttype = instrumenttype;
    }


    public TraysModel(long id, String time, String date, String traystatus, String instrumenttype) {
        this.id = id;
        this.time = time;
        this.date = date;
        this.traystatus = traystatus;
        this.instrumenttype = instrumenttype;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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


}
