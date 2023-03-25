package com.theseuntaylor.comp1471cw.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.storage.StorageVolume;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.theseuntaylor.comp1471cw.R;
import com.theseuntaylor.comp1471cw.model.CleaningProcess;
import com.theseuntaylor.comp1471cw.model.Steps;
import com.theseuntaylor.comp1471cw.model.SterilisationOperator;
import com.theseuntaylor.comp1471cw.model.TraysModel;
import com.theseuntaylor.comp1471cw.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class UpdateTrayActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    ArrayList<SterilisationOperator> sterilisationOperators;
    ArrayList<Steps> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tray);

        Intent i = getIntent();
        String process_id = i.getStringExtra("cleaning_process_id");

        Spinner operators = findViewById(R.id.spinnerOperator);
        Spinner trayState = findViewById(R.id.spinner_trayState);

        Button updateTray = findViewById(R.id.button_updateTray);
        TextView cleaningProcess = findViewById(R.id.textView_cleaningProcess);

        Spinner Steps = findViewById(R.id.spinnerSteps);
        dbHelper = new DatabaseHelper(this);
        sterilisationOperators = dbHelper.getAllSterilisationOperator();
        steps = dbHelper.getSteps();

        List<String> operatorsarray = new ArrayList<>();
        List<String> Stepsarray = new ArrayList<>();

        List<String> trayStateArray = new ArrayList<>();
        trayStateArray.add("In Progress");
        trayStateArray.add("Completed");

        cleaningProcess.setText(dbHelper.getCleaningProcessName(process_id));

        for (SterilisationOperator operator :
                sterilisationOperators) {
            operatorsarray.add(operator.getOperatorName());
        }
        for (Steps step :
                steps) {
            Stepsarray.add(step.getStepName());
        }

        ArrayAdapter<String> operatoradapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operatorsarray);
        ArrayAdapter<String> stepAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Stepsarray);
        ArrayAdapter<String> trayStateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, trayStateArray);

        operators.setAdapter(operatoradapter);
        Steps.setAdapter(stepAdapter);
        trayState.setAdapter(trayStateAdapter);

        updateTray.setOnClickListener(
                view -> {

                    CleaningProcess model = new CleaningProcess(
                            Integer.parseInt(sterilisationOperators.get(operators.getSelectedItemPosition()).getOperatorId().toString())
                            ,dbHelper.getCleaningProcessName(process_id)
                            , steps.get(Steps.getSelectedItemPosition()).getStepId());

                            dbHelper.addCleaningProcess(model);


                }
        );
    }
}