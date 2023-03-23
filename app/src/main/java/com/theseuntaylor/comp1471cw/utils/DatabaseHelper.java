package com.theseuntaylor.comp1471cw.utils;

import static com.theseuntaylor.comp1471cw.utils.Constants.CLEANING_PROCESS_CREATE;
import static com.theseuntaylor.comp1471cw.utils.Constants.CLEANING_PROCESS_TABLE;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_CLEANING_PROCESS_ID;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_CLEANING_PROCESS_NAME;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_DATE_STERILISATION;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_INSTRUMENT_TYPE;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_REPLACEMENT_NEEDED;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_STEPS_ID;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_STEPS_NAME;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_STERILISATION_MACHINE_ID;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_STERILISATION_OFFICER_NAME;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_TIME_STERILISATION;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_TRAY_ID;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_TRAY_NAME;
import static com.theseuntaylor.comp1471cw.utils.Constants.COLUMN_TRAY_STATUS;
import static com.theseuntaylor.comp1471cw.utils.Constants.DATABASE_NAME;
import static com.theseuntaylor.comp1471cw.utils.Constants.DATABASE_VERSION;
import static com.theseuntaylor.comp1471cw.utils.Constants.INSTRUMENTS_TABLE;
import static com.theseuntaylor.comp1471cw.utils.Constants.INSTRUMENT_CREATE;
import static com.theseuntaylor.comp1471cw.utils.Constants.ORDER;
import static com.theseuntaylor.comp1471cw.utils.Constants.ORDER_CREATE;
import static com.theseuntaylor.comp1471cw.utils.Constants.PROCEDURE_CREATE;
import static com.theseuntaylor.comp1471cw.utils.Constants.PROCEDURE_NAME;
import static com.theseuntaylor.comp1471cw.utils.Constants.PROCEDURE_TABLE;
import static com.theseuntaylor.comp1471cw.utils.Constants.PROCEDURE_TYPE_ID;
import static com.theseuntaylor.comp1471cw.utils.Constants.STEPS;
import static com.theseuntaylor.comp1471cw.utils.Constants.STEPS_CREATE;
import static com.theseuntaylor.comp1471cw.utils.Constants.STERILISATION_MACHINE;
import static com.theseuntaylor.comp1471cw.utils.Constants.STERILISATION_MACHINE_CREATE;
import static com.theseuntaylor.comp1471cw.utils.Constants.STERILISATION_OFFICER;
import static com.theseuntaylor.comp1471cw.utils.Constants.STERILISATION_OFFICER_CREATE;
import static com.theseuntaylor.comp1471cw.utils.Constants.TABLE_TRAY_NAME;
import static com.theseuntaylor.comp1471cw.utils.Constants.TRAY_CREATE;
import static com.theseuntaylor.comp1471cw.utils.Constants.TRAY_TYPE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.theseuntaylor.comp1471cw.model.CleaningProcess;
import com.theseuntaylor.comp1471cw.model.InstrumentType;
import com.theseuntaylor.comp1471cw.model.MedicalProcedureType;
import com.theseuntaylor.comp1471cw.model.Steps;
import com.theseuntaylor.comp1471cw.model.SterilisationOperator;
import com.theseuntaylor.comp1471cw.model.TraysModel;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TRAY_CREATE);
        sqLiteDatabase.execSQL(INSTRUMENT_CREATE);
        sqLiteDatabase.execSQL(STERILISATION_MACHINE_CREATE);
        sqLiteDatabase.execSQL(CLEANING_PROCESS_CREATE);
        sqLiteDatabase.execSQL(STERILISATION_OFFICER_CREATE);
        sqLiteDatabase.execSQL(STEPS_CREATE);
        sqLiteDatabase.execSQL(ORDER_CREATE);
        sqLiteDatabase.execSQL(PROCEDURE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAY_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CLEANING_PROCESS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STEPS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ORDER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STERILISATION_OFFICER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + INSTRUMENTS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STERILISATION_MACHINE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PROCEDURE_TABLE);

        Log.v(this.getClass().getName(), DATABASE_NAME + " database upgrade to version " +
                newVersion + " - old data lost");
        onCreate(sqLiteDatabase);
    }

    // tray
    public void createTray(TraysModel traysModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues rowValues = new ContentValues();
        rowValues.put(COLUMN_TRAY_NAME, traysModel.getName());
        rowValues.put(COLUMN_DATE_STERILISATION, traysModel.getDate());
        rowValues.put(COLUMN_TIME_STERILISATION, traysModel.getTime());
        rowValues.put(COLUMN_TRAY_STATUS, traysModel.getTraystatus());
        rowValues.put(COLUMN_INSTRUMENT_TYPE, traysModel.getInstrumenttype());
        rowValues.put(COLUMN_CLEANING_PROCESS_ID, traysModel.getCleaningProcessId());
        rowValues.put(PROCEDURE_TYPE_ID, traysModel.getMedicalProcedureId());
        db.insert(TABLE_TRAY_NAME, null, rowValues);

        db.close();
    }

    public ArrayList<TraysModel> getAllTrays() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorOperators = db.rawQuery("SELECT * FROM " + TABLE_TRAY_NAME, null);

        ArrayList<TraysModel> allTrays = new ArrayList<>();

        if (cursorOperators.moveToFirst()) {
            do {
                allTrays.add(new TraysModel(
                        cursorOperators.getInt(0),
                        cursorOperators.getString(1),
                        cursorOperators.getString(3),
                        cursorOperators.getString(2),
                        cursorOperators.getString(4),
                        cursorOperators.getString(6),
                        cursorOperators.getString(5),
                        cursorOperators.getString(7)
                ));
            } while (cursorOperators.moveToNext());
        }

        cursorOperators.close();

        return allTrays;
    }


    public void updateTray(TraysModel tray) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues rowValues = new ContentValues();

        rowValues.put(COLUMN_INSTRUMENT_TYPE, tray.getInstrumenttype());
        db.update(TABLE_TRAY_NAME, rowValues, COLUMN_TRAY_ID + " = ?", new String[]{String.valueOf(tray.getId())});

        db.close();
    }

    public void deleteTray(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRAY_NAME, COLUMN_TRAY_ID + " = ?", new String[]{id});
        db.close();
    }

    // procedure type
    public void addProcedure(MedicalProcedureType model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues rowValues = new ContentValues();

        rowValues.put(PROCEDURE_NAME, model.getMedicalProcedureName());
        db.insert(PROCEDURE_TABLE, null, rowValues);

        db.close();
    }

    public void updateProcedure(MedicalProcedureType model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues rowValues = new ContentValues();

        //rowValues.put(PROCEDURE_TYPE_ID, model.getProcedure_id());
        rowValues.put(PROCEDURE_NAME, model.getMedicalProcedureName());

        db.update(PROCEDURE_TABLE, rowValues, PROCEDURE_TYPE_ID + " = ?", new String[]{String.valueOf(model.getMedicalProcedureId())});

        db.close();
    }

    public void deleteProcedure(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PROCEDURE_TABLE, PROCEDURE_TYPE_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public ArrayList<MedicalProcedureType> getAllProcedures() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorOperators = db.rawQuery("SELECT * FROM " + PROCEDURE_TABLE, null);

        ArrayList<MedicalProcedureType> procedureTypes = new ArrayList<>();

        if (cursorOperators.moveToFirst()) {
            do {
                procedureTypes.add(new MedicalProcedureType(
                        cursorOperators.getString(0),
                        cursorOperators.getString(1),
                        cursorOperators.getString(2)
                ));
            } while (cursorOperators.moveToNext());
        }

        cursorOperators.close();

        return procedureTypes;
    }


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
                // COLUMN_CLEANING_PROCESS_ID
                //COLUMN_STEPS_ID
                //COLUMN_CLEANING_PROCESS_NAME
                cleaningProcesses.add(new CleaningProcess(
                        cursorOperators.getInt(0),
                        cursorOperators.getString(2),
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
    public void addSteps(Steps steps) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues rowValues = new ContentValues();

        rowValues.put(COLUMN_STEPS_NAME, steps.getStepName());
        rowValues.put(COLUMN_REPLACEMENT_NEEDED, steps.getReplacementneeded());
        rowValues.put(COLUMN_STERILISATION_MACHINE_ID, steps.getSterilisationMachineId());

        db.insert(STEPS_CREATE, null, rowValues);

        db.close();

    }

    public ArrayList<Steps> getSteps() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorOperators = db.rawQuery("SELECT * FROM " + STEPS, null);

        ArrayList<Steps> steps = new ArrayList<>();

        if (cursorOperators.moveToFirst()) {
            do {

                steps.add(new Steps(
                        cursorOperators.getInt(0),cursorOperators.getString(1),cursorOperators.getString(2),cursorOperators.getString(3)
                ));
            } while (cursorOperators.moveToNext());
        }

        cursorOperators.close();

        return steps;
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

    public ArrayList<InstrumentType> showallinstruments() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorOperators = db.rawQuery("SELECT * FROM " + INSTRUMENTS_TABLE, null);

        ArrayList<InstrumentType> instrumentTypes = new ArrayList<>();

        if (cursorOperators.moveToFirst()) {
            do {

                instrumentTypes.add(new InstrumentType(
                        cursorOperators.getString(0)
                ));
            } while (cursorOperators.moveToNext());
        }

        cursorOperators.close();

        return instrumentTypes;
    }

    public ArrayList<TraysModel> getTraysWithProcedure(String procedureId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String id = procedureId;

        Cursor cursorOperators = db.rawQuery("SELECT * FROM " + TABLE_TRAY_NAME + " WHERE procedure_id =? ", new String[]{id});
        ArrayList<TraysModel> trays = new ArrayList<>();

        if (cursorOperators.moveToFirst()) {
            do {

                trays.add(new TraysModel(
                        cursorOperators.getString(1)
                ));
            } while (cursorOperators.moveToNext());
        }

        cursorOperators.close();

        return trays;
    }

}

