package com.theseuntaylor.comp1471cw.view;

public class HospitalModel {

    private long id = -1;
    private String hospital_name;
    public HospitalModel(String hospital_name) {
        this.hospital_name = hospital_name;
    }


    public HospitalModel(long id, String hospital_name) {
        this.id = id;
        this.hospital_name = hospital_name;
    }


}
