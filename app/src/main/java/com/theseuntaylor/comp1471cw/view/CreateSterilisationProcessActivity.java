package com.theseuntaylor.comp1471cw.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.theseuntaylor.comp1471cw.R;
import com.theseuntaylor.comp1471cw.model.CleaningProcess;
import com.theseuntaylor.comp1471cw.model.InstrumentType;
import com.theseuntaylor.comp1471cw.model.Steps;
import com.theseuntaylor.comp1471cw.model.SterilisationOperator;
import com.theseuntaylor.comp1471cw.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CreateSterilisationProcessActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    ArrayList<SterilisationOperator> sterilisationOperators;
    ArrayList<Steps> steps;
    Button Sterlize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sterilisation_process);
        Spinner operators = findViewById(R.id.spinnerOperator);
        Spinner Steps = findViewById(R.id.spinnerSteps);
        dbHelper = new DatabaseHelper(this);
        sterilisationOperators = dbHelper.getAllSterilisationOperator();
        steps = dbHelper.getSteps();
        List<String> operatorsarray = new ArrayList<>();
        List<String> Stepsarray = new ArrayList<>();

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
        operators.setAdapter(operatoradapter);
        Steps.setAdapter(stepAdapter);
    }
}