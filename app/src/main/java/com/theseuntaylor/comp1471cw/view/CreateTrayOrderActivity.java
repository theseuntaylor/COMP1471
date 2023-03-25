package com.theseuntaylor.comp1471cw.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.theseuntaylor.comp1471cw.R;
import com.theseuntaylor.comp1471cw.model.CleaningProcess;
import com.theseuntaylor.comp1471cw.model.InstrumentType;
import com.theseuntaylor.comp1471cw.model.MedicalProcedureType;
import com.theseuntaylor.comp1471cw.model.TraysModel;
import com.theseuntaylor.comp1471cw.utils.DatabaseHelper;
import com.theseuntaylor.comp1471cw.utils.TrayStatusEnum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreateTrayOrderActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    ArrayList<CleaningProcess> cleaningProcesses;
    ArrayList<InstrumentType> instrumentTypes;
    Button orderplace;
    private ArrayList<MedicalProcedureType> procedures;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tray_order);
        dbHelper = new DatabaseHelper(this);

        cleaningProcesses = dbHelper.getAllProcess();
        instrumentTypes = dbHelper.showallinstruments();
        procedures = dbHelper.getAllProcedures();

        List<String> processArray = new ArrayList<>();
        List<String> instrumentarray = new ArrayList<>();
        List<String> procedureArray = new ArrayList<>();

        for (CleaningProcess process : cleaningProcesses) {
            processArray.add(process.getProcessName());
        }

        for (MedicalProcedureType procedure :
                procedures) {
            procedureArray.add(procedure.getMedicalProcedureName());
        }
        for (InstrumentType instrument :
                instrumentTypes) {
            instrumentarray.add(instrument.getInstrumentName());
        }

        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Tray Type 1");
        spinnerArray.add("Tray Type 2");
        spinnerArray.add("Tray Type 3");
        spinnerArray.add("Tray Type 4");
        spinnerArray.add("Tray Type 5");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray);
        ArrayAdapter<String> processAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, processArray);
        ArrayAdapter<String> instrumentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, instrumentarray);
        ArrayAdapter<String> procedureAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, procedureArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner trayTypeSpinner = findViewById(R.id.spinnerTrayType);
        Spinner processTypeSpinner = findViewById(R.id.spinnerProcessType);
        Spinner instrumentSpinner = findViewById(R.id.instrumentspinner);
        Spinner procedureSpinner = findViewById(R.id.procedurespinner);


        orderplace = findViewById(R.id.OrderButton);
        orderplace.setOnClickListener(view -> {

            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

            TraysModel model = new TraysModel(
                    trayTypeSpinner.getSelectedItem().toString(),
                    currentTime,
                    currentDate,
                    "In Progress",
                    instrumentSpinner.getSelectedItem().toString(),
                    String.valueOf(cleaningProcesses.get(processTypeSpinner.getSelectedItemPosition()).getStepId()),
                    procedures.get(processTypeSpinner.getSelectedItemPosition()).getMedicalProcedureId());

            dbHelper.createTray(model);

            finish();
        });

        trayTypeSpinner.setAdapter(adapter);
        processTypeSpinner.setAdapter(processAdapter);
        instrumentSpinner.setAdapter(instrumentAdapter);
        procedureSpinner.setAdapter(procedureAdapter);

    }
}