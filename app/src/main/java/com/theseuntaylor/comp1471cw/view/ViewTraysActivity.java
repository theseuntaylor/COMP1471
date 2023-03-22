package com.theseuntaylor.comp1471cw.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.theseuntaylor.comp1471cw.ItemClickListener;
import com.theseuntaylor.comp1471cw.R;
import com.theseuntaylor.comp1471cw.adapter.TrayAdapter;
import com.theseuntaylor.comp1471cw.model.TraysModel;
import com.theseuntaylor.comp1471cw.utils.DatabaseHelper;

import java.util.ArrayList;

public class ViewTraysActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ArrayList<TraysModel> trays;
    private TrayAdapter adapter;

    ItemClickListener itemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trays);
        dbHelper = new DatabaseHelper(this);

        RecyclerView rv = findViewById(R.id.allTraysRecyclerView);

        itemClickListener = new ItemClickListener(){
            @Override
            public void editTray(int position) {

                TraysModel tray = trays.get(position);
                Intent i = new Intent(ViewTraysActivity.this, CreateSterilisationProcessActivity.class);
                i.putExtra("cleaning_process_id", tray.getCleaningProcessId());
                startActivity(i);
            }

            @Override
            public void deleteTray(int position) {
                dbHelper.deleteTray(String.valueOf(trays.get(position).getId()));
            }
        };

        trays = dbHelper.getAllTrays();
        adapter = new TrayAdapter(trays, itemClickListener);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}