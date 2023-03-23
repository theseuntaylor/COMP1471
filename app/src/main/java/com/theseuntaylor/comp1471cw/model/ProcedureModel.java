package com.theseuntaylor.comp1471cw.model;

public class ProcedureModel {

    int procedure_id;

    public ProcedureModel(int procedure_id, int tray_id, String procedure_name) {
        this.procedure_id = procedure_id;

        this.procedure_name = procedure_name;
    }

    String procedure_name;

    public int getProcedure_id() {
        return procedure_id;
    }

    public void setProcedure_id(int procedure_id) {
        this.procedure_id = procedure_id;
    }


    public String getProcedure_name() {
        return procedure_name;
    }

    public void setProcedure_name(String procedure_name) {
        this.procedure_name = procedure_name;
    }


}
