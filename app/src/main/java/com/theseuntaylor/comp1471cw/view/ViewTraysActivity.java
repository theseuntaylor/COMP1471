package com.theseuntaylor.comp1471cw.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.theseuntaylor.comp1471cw.AppController;
import com.theseuntaylor.comp1471cw.ItemClickListener;
import com.theseuntaylor.comp1471cw.R;
import com.theseuntaylor.comp1471cw.adapter.TrayAdapter;
import com.theseuntaylor.comp1471cw.model.TraysModel;
import com.theseuntaylor.comp1471cw.utils.DatabaseHelper;

import java.util.ArrayList;

public class ViewTraysActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ArrayList<TraysModel> trays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trays);
        AppController ac = AppController.getInstance();
        dbHelper = ac.databaseHelper;

        RecyclerView rv = findViewById(R.id.allTraysRecyclerView);

        trays = dbHelper.getAllTrays();

        TrayAdapter adapter = new TrayAdapter(trays, getItemClickListener(trays));

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @NonNull
    private ItemClickListener getItemClickListener(ArrayList<TraysModel> trays) {
        return new ItemClickListener() {
            @Override
            public void edit(int position) {
                TraysModel tray = trays.get(position);
                Intent i = new Intent(ViewTraysActivity.this, UpdateTrayActivity.class);
                i.putExtra("cleaning_process_id", tray.getCleaningProcessId());
                i.putExtra("tray_id", tray.getId());
                startActivity(i);
            }

            @Override
            public void view(int position) {
                TraysModel tray = trays.get(position);
                Toast.makeText(
                        ViewTraysActivity.this,
                        "You tapped on: " + tray.getName(),
                        Toast.LENGTH_SHORT
                ).show();
            }

            @Override
            public void delete(int position) {
                dbHelper.deleteTray(String.valueOf(trays.get(position).getId()));
                finish();
            }
        };
    }
}