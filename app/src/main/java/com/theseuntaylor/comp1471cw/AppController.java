package com.theseuntaylor.comp1471cw;

import android.app.Application;

import com.theseuntaylor.comp1471cw.utils.DatabaseHelper;

public class AppController extends Application {

    private static AppController mInstance;
    public DatabaseHelper databaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initialiseDatabase(mInstance);
    }

    private void initialiseDatabase(AppController mInstance) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(mInstance);
        }
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

}