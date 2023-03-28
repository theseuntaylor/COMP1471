package com.theseuntaylor.comp1471cw.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.theseuntaylor.comp1471cw.AppController;
import com.theseuntaylor.comp1471cw.R;
import com.theseuntaylor.comp1471cw.model.CleaningProcess;
import com.theseuntaylor.comp1471cw.model.InstrumentType;
import com.theseuntaylor.comp1471cw.model.MedicalProcedureType;
import com.theseuntaylor.comp1471cw.model.TraysModel;
import com.theseuntaylor.comp1471cw.utils.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreateTrayOrderActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    private Button placeOrder;
    private Spinner trayTypeSpinner, processTypeSpinner, instrumentSpinner, procedureSpinner;

    private ArrayList<CleaningProcess> cleaningProcesses;
    private ArrayList<MedicalProcedureType> procedures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tray_order);
        AppController ac = AppController.getInstance();
        dbHelper = ac.databaseHelper;

        setUpViews();

        cleaningProcesses = dbHelper.getAllProcess();
        ArrayList<InstrumentType> instrumentTypes = dbHelper.showallinstruments();
        procedures = dbHelper.getAllProcedures();

        List<String> processArray = new ArrayList<>();
        List<String> instrumentArray = new ArrayList<>();
        List<String> procedureArray = new ArrayList<>();

        for (CleaningProcess process : cleaningProcesses)
            processArray.add(process.getProcessName());
        for (MedicalProcedureType procedure : procedures)
            procedureArray.add(procedure.getMedicalProcedureName());
        for (InstrumentType instrument : instrumentTypes)
            instrumentArray.add(instrument.getInstrumentName());

        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Tray Type 1");
        spinnerArray.add("Tray Type 2");
        spinnerArray.add("Tray Type 3");
        spinnerArray.add("Tray Type 4");
        spinnerArray.add("Tray Type 5");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray);
        ArrayAdapter<String> processAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, processArray);
        ArrayAdapter<String> instrumentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, instrumentArray);
        ArrayAdapter<String> procedureAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, procedureArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        placeOrder.setOnClickListener(
                view -> createTrayOrder(
                        trayTypeSpinner, processTypeSpinner, instrumentSpinner
                )
        );

        trayTypeSpinner.setAdapter(adapter);
        processTypeSpinner.setAdapter(processAdapter);
        instrumentSpinner.setAdapter(instrumentAdapter);
        procedureSpinner.setAdapter(procedureAdapter);

    }

    private void setUpViews() {
        trayTypeSpinner = findViewById(R.id.spinnerTrayType);
        processTypeSpinner = findViewById(R.id.spinnerProcessType);
        instrumentSpinner = findViewById(R.id.instrumentspinner);
        procedureSpinner = findViewById(R.id.procedurespinner);
        placeOrder = findViewById(R.id.OrderButton);
    }

    private void createTrayOrder(Spinner trayTypeSpinner, Spinner processTypeSpinner, Spinner instrumentSpinner) {
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        TraysModel model = new TraysModel(
                trayTypeSpinner.getSelectedItem().toString(),
                currentTime,
                currentDate,
                "In Progress",
                new InstrumentType(instrumentSpinner.getSelectedItem().toString()),
                String.valueOf(cleaningProcesses.get(processTypeSpinner.getSelectedItemPosition()).getStepId()),
                procedures.get(processTypeSpinner.getSelectedItemPosition()).getMedicalProcedureId());

        dbHelper.createTray(model);

        finish();
    }
}