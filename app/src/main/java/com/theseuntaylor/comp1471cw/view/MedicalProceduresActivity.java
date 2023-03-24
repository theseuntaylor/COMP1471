package com.theseuntaylor.comp1471cw.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_procedures);
        dbHelper = new DatabaseHelper(this);
        floatingActionButton=findViewById(R.id.add_procedure);
        RecyclerView rv = findViewById(R.id.allHospitalsRecyclerView);

        ArrayList<MedicalProcedureType> medicalProcedures = dbHelper.getAllProcedures();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MedicalProceduresActivity.this);
                final EditText edittext = new EditText(MedicalProceduresActivity.this);
                //alert.setMessage("Enter Your Message");
                alert.setTitle("Enter the procedure name");

                alert.setView(edittext);
            //    alert.setView(new Spinner(MedicalProceduresActivity.this));

                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                        String YouEditTextValue = edittext.getText().toString();
                        Toast.makeText(MedicalProceduresActivity.this,YouEditTextValue,Toast.LENGTH_LONG).show();
                        dbHelper.addProcedure(new MedicalProcedureType(YouEditTextValue,"1"));

                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                    }
                });

                alert.show();
            }
        });
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