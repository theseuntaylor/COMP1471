package com.theseuntaylor.comp1471cw.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.theseuntaylor.comp1471cw.ItemClickListener;
import com.theseuntaylor.comp1471cw.R;
import com.theseuntaylor.comp1471cw.adapter.MedicalProcedureAdapter;
import com.theseuntaylor.comp1471cw.adapter.TrayAdapter;
import com.theseuntaylor.comp1471cw.model.MedicalProcedureType;
import com.theseuntaylor.comp1471cw.model.TraysModel;
import com.theseuntaylor.comp1471cw.utils.DatabaseHelper;

import java.util.ArrayList;

public class MedicalProceduresActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_procedures);
        dbHelper = new DatabaseHelper(this);

        RecyclerView rv = findViewById(R.id.allHospitalsRecyclerView);

        ArrayList<MedicalProcedureType> medicalProcedures = dbHelper.getAllProcedures();

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void edit(int position) {

                MedicalProcedureType medicalProcedure = medicalProcedures.get(position);

                // Intent i = new Intent(MedicalProceduresActivity.this, CreateSterilisationProcessActivity.class);
                // i.putExtra("cleaning_process_id", medicalProcedure.getTrayId());
                // startActivity(i);
            }

            @Override
            public void view(int position) {


                StringBuilder message = new StringBuilder();

                MedicalProcedureType medicalProcedure = medicalProcedures.get(position);

                if (dbHelper.getTraysWithProcedure(medicalProcedure.getMedicalProcedureId()).size() > 0) {
                    for (TraysModel tray : dbHelper.getTraysWithProcedure(medicalProcedure.getMedicalProcedureId()))
                    {
                        message.append(tray.getName()).append(", ");
                    }
                    new AlertDialog.Builder(MedicalProceduresActivity.this).setTitle("View Trays used in Medical " + medicalProcedure.getMedicalProcedureName())
                            .setMessage(message).show();
                } else {
                    Toast.makeText(MedicalProceduresActivity.this, "NO TRAYS ATTACHED TO PROCEDURE", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void delete(int position) {
                dbHelper.deleteProcedure(String.valueOf(medicalProcedures.get(position).getMedicalProcedureId()));
                finish();
            }
        };


        MedicalProcedureAdapter adapter = new MedicalProcedureAdapter(medicalProcedures, itemClickListener);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}