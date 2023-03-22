package com.theseuntaylor.comp1471cw.utils;

import static com.theseuntaylor.comp1471cw.utils.Constants.CLEANING_PROCESS_CREATE;
import static com.theseuntaylor.comp1471cw.utils.Constants.CLEANING_PROCESS_TABLE;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_CLEANING_PROCESS_ID;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_CLEANING_PROCESS_NAME;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_DATE_STERILISATION;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_INSTRUMENT_TYPE;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_STEPS_ID;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_STEPS_NAME;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_STERILISATION_OFFICER_NAME;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_TIME_STERILISATION;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_TRAY_STATUS;
import static com.theseuntaylor.comp1471cw.utils.Constants.DATABASE_NAME;
import static com.theseuntaylor.comp1471cw.utils.Constants.DATABASE_VERSION;
import static com.theseuntaylor.comp1471cw.utils.Constants.ORDER;
import static com.theseuntaylor.comp1471cw.utils.Constants.ORDER_CREATE;
import static com.theseuntaylor.comp1471cw.utils.Constants.STEPS;
import static com.theseuntaylor.comp1471cw.utils.Constants.STEPS_CREATE;
import static com.theseuntaylor.comp1471cw.utils.Constants.STERILISATION_OFFICER;
import static com.theseuntaylor.comp1471cw.utils.Constants.STERILISATION_OFFICER_CREATE;
import static com.theseuntaylor.comp1471cw.utils.Constants.TABLE_TRAY_NAME;
import static com.theseuntaylor.comp1471cw.utils.Constants.TRAY_CREATE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.theseuntaylor.comp1471cw.model.CleaningProcess;
import com.theseuntaylor.comp1471cw.model.Steps;
import com.theseuntaylor.comp1471cw.model.SterilisationOperator;
import com.theseuntaylor.comp1471cw.model.TraysModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TRAY_CREATE);
        sqLiteDatabase.execSQL(CLEANING_PROCESS_CREATE);
        sqLiteDatabase.execSQL(STERILISATION_OFFICER_CREATE);
        sqLiteDatabase.execSQL(STEPS_CREATE);
        sqLiteDatabase.execSQL(ORDER_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAY_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CLEANING_PROCESS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STEPS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ORDER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STERILISATION_OFFICER);

        Log.v(this.getClass().getName(), DATABASE_NAME + " database upgrade to version " +
                newVersion + " - old data lost");
        onCreate(sqLiteDatabase);
    }

    public long createTray(TraysModel traysModel) {
        ContentValues rowValues = new ContentValues();
        rowValues.put(COLUMN_DATE_STERILISATION, traysModel.getDate());
        rowValues.put(COLUMN_TIME_STERILISATION, traysModel.getTime());
        rowValues.put(COLUMN_TRAY_STATUS, traysModel.getTraystatus());
        rowValues.put(COLUMN_INSTRUMENT_TYPE, traysModel.getInstrumenttype());
        return database.insertOrThrow(TABLE_TRAY_NAME, null, rowValues);
    }

    // procedure type

    // tray


    // process
    public void addCleaningProcess(CleaningProcess cleaningProcess, String stepId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues rowValues = new ContentValues();

        rowValues.put(COLUMN_STEPS_ID, stepId);
        rowValues.put(COLUMN_CLEANING_PROCESS_NAME, cleaningProcess.getProcessName());

        db.insert(CLEANING_PROCESS_TABLE, null, rowValues);

        db.close();
    }

    public ArrayList<CleaningProcess> getAllProcess() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorOperators = db.rawQuery("SELECT * FROM " + CLEANING_PROCESS_TABLE, null);

        ArrayList<CleaningProcess> cleaningProcesses = new ArrayList<>();

        if (cursorOperators.moveToFirst()) {
            do {
                cleaningProcesses.add(new CleaningProcess(
                        cursorOperators.getString(0),
                        cursorOperators.getInt(1)
                ));
            } while (cursorOperators.moveToNext());
        }

        cursorOperators.close();

        return cleaningProcesses;
    }

    public void deleteCleaningProcess(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CLEANING_PROCESS_TABLE, COLUMN_CLEANING_PROCESS_ID + " = ?", new String[]{id});
        db.close();
    }

    public void updateCleaningProcess(CleaningProcess cleaningProcess) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues rowValues = new ContentValues();

        // rowValues.put(COLUMN_CLEANING_PROCESS_ID, cleaningProcess.getOperatorName());
        rowValues.put(COLUMN_STEPS_ID, cleaningProcess.getStepId());
        rowValues.put(COLUMN_CLEANING_PROCESS_NAME, cleaningProcess.getProcessName());

        db.update(CLEANING_PROCESS_TABLE, rowValues, COLUMN_CLEANING_PROCESS_ID + " = ?", new String[]{String.valueOf(cleaningProcess.getProcessId())});

        db.close();
    }

    // STEPS
    /*public void addSteps() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues rowValues = new ContentValues();

        rowValues.put(COLUMN_STEPS_NAME, );
        rowValues.put(COLUMN_CLEANING_PROCESS_NAME, cleaningProcess.getProcessName());

        db.insert(STEPS_CREATE, null, rowValues);

        db.close();

    }*/

    public ArrayList<Steps> getSteps() {
        return null;
    }

    public void deleteSteps() {

    }

    public void updateSteps() {

    }

    // add and get sterilisation operators

    public void addSterilisationOperator(SterilisationOperator sterilisationOperator) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues rowValues = new ContentValues();

        rowValues.put(COLUMN_STERILISATION_OFFICER_NAME, sterilisationOperator.getOperatorName());

        db.insert(STERILISATION_OFFICER, null, rowValues);

        db.close();
    }

    public ArrayList<SterilisationOperator> getAllSterilisationOperator() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorOperators = db.rawQuery("SELECT * FROM " + STERILISATION_OFFICER, null);

        ArrayList<SterilisationOperator> sterilisationOperators = new ArrayList<>();

        if (cursorOperators.moveToFirst()) {
            do {
                sterilisationOperators.add(new SterilisationOperator(
                        cursorOperators.getString(0), cursorOperators.getString(1)
                ));
            } while (cursorOperators.moveToNext());
        }

        cursorOperators.close();

        return sterilisationOperators;

    }

}
