package com.theseuntaylor.comp1471cw.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.theseuntaylor.comp1471cw.R;
import com.theseuntaylor.comp1471cw.model.SterilisationOperator;
import com.theseuntaylor.comp1471cw.utils.DatabaseHelper;

public class ActionsActivity extends AppCompatActivity {

    private Button viewTraysBtn, createTraysBtn, createSterilisationOfficer, sterilisationProcess;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);

        dbHelper = new DatabaseHelper(this);

        viewTraysBtn = findViewById(R.id.viewTraysButton);
        createTraysBtn = findViewById(R.id.createNewTrayButton);
        createSterilisationOfficer = findViewById(R.id.createNewOperator);
        sterilisationProcess = findViewById(R.id.initiateSterilisationProcess);

        actions();
    }

    private void actions() {
        viewTraysBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, ViewTraysActivity.class);
            startActivity(i);
        });
        createTraysBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, CreateTrayActivity.class);
            startActivity(i);
        });
        createSterilisationOfficer.setOnClickListener(view -> {
            Log.e("Sterilisation Officer", "John Doe");
            dbHelper.addSterilisationOperator(new SterilisationOperator( "John Doe"));
        });
        sterilisationProcess.setOnClickListener(view -> {
            Intent i = new Intent(this,CreateSterilisationProcessActivity.class);
            startActivity(i);
        });
    }
}