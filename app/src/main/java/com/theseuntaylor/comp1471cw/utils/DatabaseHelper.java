package com.theseuntaylor.comp1471cw.utils;

import static com.theseuntaylor.comp1471cw.utils.Constants.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

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

    public void addSterilisationOperator(SterilisationOperator sterilisationOperator) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues rowValues = new ContentValues();

        // rowValues.put(COLUMN_STERILISATION_OFFICER_ID, sterilisationOperator.getOperatorId());
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
