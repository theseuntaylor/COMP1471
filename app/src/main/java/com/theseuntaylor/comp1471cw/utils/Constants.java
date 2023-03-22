package com.theseuntaylor.comp1471cw.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.theseuntaylor.comp1471cw.model.SterilisationOperator;

import java.util.ArrayList;

public class Constants {
    public static final String DATABASE_NAME = "COMP1471";
    public static final int DATABASE_VERSION = 1;


    // TRAYS
    public static final String TABLE_TRAY_NAME = "Trays";
    public static final String COLUMN_TRAY_ID = "tray_id";
    public static final String COLUMN_DATE_STERLISATION = "date_sterlisation";
    public static final String COLUMN_TIME_STERLISATION = "time_sterlisation";
    public static final String COLUMN_TRAY_STATUS = "tray_status";
    public static final String COLUMN_INSTRUMENT_TYPE = "instrument_type";

    /*static final String TRAY_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " + //tray id
                    "   %s TEXT, " + // tray date
                    "   %s TEXT, " + // tray time
                    "   %s TEXT, " + // tray status
                    "   %s TEXT, " + // instrument_type
                    TABLE_TRAY_NAME, COLUMN_TRAY_ID, COLUMN_DATE_STERLISATION,
            COLUMN_TIME_STERLISATION, COLUMN_TRAY_STATUS, COLUMN_INSTRUMENT_TYPE
    );*/

    public static String TRAY_CREATE = "create table "
            + TABLE_TRAY_NAME + " ("
            + COLUMN_TRAY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DATE_STERLISATION + " TEXT, "
            + COLUMN_TIME_STERLISATION + " TEXT, "
            + COLUMN_TRAY_STATUS + " TEXT, "
            + COLUMN_INSTRUMENT_TYPE + " TEXT)";

    // HOSPITALS
    public static final String TABLE_HOSPITAL_NAME = "Hospitals";
    public static final String COLUMN_HOSPITAL_ID = "hospital_id";
    public static final String COLUMN_HOSPITAL_NAME = "hospital_name";

    public static final String HOSPITAL_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " + // id
                    "   %s TEXT, " + // hospital_name
                    TABLE_HOSPITAL_NAME, COLUMN_HOSPITAL_ID, COLUMN_HOSPITAL_NAME);

    // STERILISATION OFFICER
    public static final String STERILISATION_OFFICER = "sterilisation_officer";
    public static final String COLUMN_STERILISATION_OFFICER_ID = "sterilisation_officer_id";
    public static final String COLUMN_STERILISATION_OFFICER_NAME = "sterilisation_officer_name";

    public static final String STERILISATION_OFFICER_CREATE = "create table "
            + STERILISATION_OFFICER + " ("
            + COLUMN_STERILISATION_OFFICER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STERILISATION_OFFICER_NAME + " TEXT)";

}
