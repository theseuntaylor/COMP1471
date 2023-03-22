package com.theseuntaylor.comp1471cw.view;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "COMP1471";
    private static final String TABLE_TRAY_NAME = "Trays";
    private static final String TABLE_HOSPITAL_NAME = "Hospitals";
    private SQLiteDatabase database;
    public static final String COLUMN_TRAY_ID = "tray_id";
    public static final String COLUMN_DATE_STERLISATION = "date_sterlisation";
    public static final String COLUMN_TIME_STERLISATION = "time_sterlisation";
    public static final String COLUMN_TRAY_STATUS = "tray_status";
    public static final String COLUMN_INSTRUMENT_TYPE = "instrument_type";


    public static final String COLUMN_HOSPITAL_ID = "hospital_id";
    public static final String COLUMN_HOSPITAL_NAME = "hospital_name";

    private static final String TRAY_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " + //tray id
                    "   %s TEXT, " + // tray date
                    "   %s TEXT, " + // tray time
                    "   %s TEXT, " + // tray status
                    "   %s TEXT, " + // instrument_type
            TABLE_TRAY_NAME, COLUMN_TRAY_ID, COLUMN_DATE_STERLISATION,
            COLUMN_TIME_STERLISATION, COLUMN_TRAY_STATUS, COLUMN_INSTRUMENT_TYPE
            );

    private static final String HOSPITAL_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " + // id
                    "   %s TEXT, " + // hospital_name
                    TABLE_HOSPITAL_NAME, COLUMN_HOSPITAL_ID, COLUMN_HOSPITAL_NAME);

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TRAY_CREATE);
        sqLiteDatabase.execSQL(HOSPITAL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAY_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HOSPITAL_NAME);

        Log.v(this.getClass().getName(), DATABASE_NAME + " database upgrade to version " +
                newVersion + " - old data lost");
        onCreate(sqLiteDatabase);
    }
    public long createTray(TraysModel traysModel)
    {
        ContentValues rowValues = new ContentValues();
        rowValues.put(COLUMN_DATE_STERLISATION, traysModel.getDate());
        rowValues.put(COLUMN_TIME_STERLISATION, traysModel.getTime());
        rowValues.put(COLUMN_TRAY_STATUS, traysModel.getTraystatus());
        rowValues.put(COLUMN_INSTRUMENT_TYPE, traysModel.getInstrumenttype());
        return database.insertOrThrow(TABLE_TRAY_NAME, null, rowValues);
    }

}
