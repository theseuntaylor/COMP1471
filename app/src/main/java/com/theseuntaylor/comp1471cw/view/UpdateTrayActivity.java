package com.theseuntaylor.comp1471cw.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.theseuntaylor.comp1471cw.AppController;
import com.theseuntaylor.comp1471cw.R;
import com.theseuntaylor.comp1471cw.model.Steps;
import com.theseuntaylor.comp1471cw.model.SterilisationOperator;
import com.theseuntaylor.comp1471cw.model.TraysModel;
import com.theseuntaylor.comp1471cw.utils.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UpdateTrayActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private Spinner operators, trayState, steps;
    private Button updateTray;
    private TextView cleaningProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tray);

        AppController ac = AppController.getInstance();
        dbHelper = ac.databaseHelper;

        Intent i = getIntent();
        String process_id = i.getStringExtra("cleaning_process_id");
        int tray_id = i.getIntExtra("tray_id", -1);

        setUpViews();

        ArrayList<SterilisationOperator> sterilisationOperators = dbHelper.getAllSterilisationOperator();
        ArrayList<com.theseuntaylor.comp1471cw.model.Steps> steps = dbHelper.getSteps();

        List<String> operatorsArray = new ArrayList<>();
        List<String> stepsArray = new ArrayList<>();

        List<String> trayStateArray = new ArrayList<>();
        trayStateArray.add("In Progress");
        trayStateArray.add("Completed");
        trayStateArray.add("Ready for Delivery");

        cleaningProcess.setText(dbHelper.getCleaningProcessName(process_id));

        for (SterilisationOperator operator : sterilisationOperators)
            operatorsArray.add(operator.getOperatorName());

        for (Steps step : steps) stepsArray.add(step.getStepName());

        ArrayAdapter<String> operatoradapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, operatorsArray
        );
        ArrayAdapter<String> stepAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, stepsArray
        );
        ArrayAdapter<String> trayStateAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, trayStateArray
        );

        operators.setAdapter(operatoradapter);
        this.steps.setAdapter(stepAdapter);
        trayState.setAdapter(trayStateAdapter);

        updateTray.setOnClickListener(view -> updateTray(tray_id));
    }

    private void updateTray(int tray_id) {
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        String trayId = String.valueOf(tray_id);
        TraysModel model = dbHelper.getSingleTray(trayId);

        model.setTraystatus(trayState.getSelectedItem().toString());

        model.setTime(currentTime);
        model.setDate(currentDate);
        model.setTraystatus(trayState.getSelectedItem().toString());

        dbHelper.updateTray(model);

        finish();
    }

    private void setUpViews() {
        operators = findViewById(R.id.spinnerOperator);
        trayState = findViewById(R.id.spinner_trayState);
        updateTray = findViewById(R.id.button_updateTray);
        cleaningProcess = findViewById(R.id.textView_cleaningProcess);
        steps = findViewById(R.id.spinnerSteps);
    }
}