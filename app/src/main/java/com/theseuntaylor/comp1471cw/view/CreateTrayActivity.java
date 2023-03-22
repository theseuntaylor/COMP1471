package com.theseuntaylor.comp1471cw.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.theseuntaylor.comp1471cw.R;
import com.theseuntaylor.comp1471cw.model.SterilisationOperator;
import com.theseuntaylor.comp1471cw.utils.DatabaseHelper;

import java.util.ArrayList;

public class CreateTrayActivity extends AppCompatActivity {

    private DatabaseHelper  dbHelper;
    ArrayList<SterilisationOperator> officers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tray);
        dbHelper = new DatabaseHelper(this);

        officers = dbHelper.getAllSterilisationOperator();

//        for (SterilisationOperator officer: officers) {
//            Toast.makeText(this, officer.getOperatorName(), Toast.LENGTH_LONG).show();
//        }
    }
}