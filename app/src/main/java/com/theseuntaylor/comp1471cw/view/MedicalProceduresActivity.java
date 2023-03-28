package com.theseuntaylor.comp1471cw.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theseuntaylor.comp1471cw.AppController;
import com.theseuntaylor.comp1471cw.ItemClickListener;
import com.theseuntaylor.comp1471cw.R;
import com.theseuntaylor.comp1471cw.adapter.MedicalProcedureAdapter;
import com.theseuntaylor.comp1471cw.model.MedicalProcedureType;
import com.theseuntaylor.comp1471cw.model.TraysModel;
import com.theseuntaylor.comp1471cw.utils.DatabaseHelper;

import java.util.ArrayList;

public class MedicalProceduresActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private FloatingActionButton floatingActionButton;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_procedures);
        AppController ac = AppController.getInstance();
        dbHelper = ac.databaseHelper;

        setUpViews();

        ArrayList<MedicalProcedureType> medicalProcedures = dbHelper.getAllProcedures();

        floatingActionButton.setOnClickListener(view -> addNewProcedure(true, null));

        MedicalProcedureAdapter adapter = new MedicalProcedureAdapter(medicalProcedures, getItemClickListener(medicalProcedures));
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpViews() {
        floatingActionButton = findViewById(R.id.add_procedure);
        rv = findViewById(R.id.allHospitalsRecyclerView);
    }

    @NonNull
    private ItemClickListener getItemClickListener(ArrayList<MedicalProcedureType> medicalProcedures) {
        return new ItemClickListener() {
            @Override
            public void edit(int position) {
                MedicalProcedureType medicalProcedure = medicalProcedures.get(position);
                addNewProcedure(false, medicalProcedure);
            }

            @Override
            public void view(int position) {
                StringBuilder message = new StringBuilder();
                MedicalProcedureType medicalProcedure = medicalProcedures.get(position);
                if (dbHelper.getTraysWithProcedure(medicalProcedure.getMedicalProcedureId()).size() > 0) {
                    for (TraysModel tray : dbHelper.getTraysWithProcedure(medicalProcedure.getMedicalProcedureId())) {
                        message.append(tray.getName()).append(", ");
                    }
                    new AlertDialog.Builder(MedicalProceduresActivity.this)
                            .setTitle("View Trays used in Medical " + medicalProcedure.getMedicalProcedureName())
                            .setMessage(message).show();
                } else {
                    Toast.makeText(
                            MedicalProceduresActivity.this,
                            "NO TRAYS ATTACHED TO PROCEDURE",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void delete(int position) {
                dbHelper.deleteProcedure(String.valueOf(medicalProcedures.get(position).getMedicalProcedureId()));
                finish();
            }
        };
    }

    private void addNewProcedure(Boolean isNewProcedure, MedicalProcedureType model) {

        AlertDialog.Builder alert = new AlertDialog.Builder(MedicalProceduresActivity.this);
        final EditText edittext = new EditText(MedicalProceduresActivity.this);
        alert.setTitle("Enter the procedure name");
        alert.setView(edittext);

        if (isNewProcedure) {
            alert.setPositiveButton("Add", (dialog, whichButton) -> {
                String YouEditTextValue = edittext.getText().toString();
                dbHelper.addProcedure(new MedicalProcedureType(YouEditTextValue, "1"));
            });
        } else {
            alert.setPositiveButton("Update", (dialog, whichButton) -> {
                String YouEditTextValue = edittext.getText().toString();

                model.setMedicalProcedureName(YouEditTextValue);

                dbHelper.updateProcedure(model);
            });
        }

        alert.setNegativeButton("Cancel", (dialog, whichButton) -> dialog.dismiss());

        alert.show();
    }
}