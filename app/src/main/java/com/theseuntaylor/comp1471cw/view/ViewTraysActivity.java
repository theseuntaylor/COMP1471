package com.theseuntaylor.comp1471cw.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

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
        dbHelper = new DatabaseHelper(this);

        RecyclerView rv = findViewById(R.id.allTraysRecyclerView);

        trays = dbHelper.getAllTrays();

        ItemClickListener itemClickListener = new ItemClickListener(){
            @Override
            public void edit(int position) {

                TraysModel tray = trays.get(position);
                Intent i = new Intent(ViewTraysActivity.this, UpdateTrayActivity.class);
                i.putExtra("cleaning_process_id", tray.getCleaningProcessId());
                startActivity(i);
            }

            @Override
            public void view(int position) {

            }

            @Override
            public void delete(int position) {
                dbHelper.deleteTray(String.valueOf(trays.get(position).getId()));
                finish();
            }
        };

        TrayAdapter adapter = new TrayAdapter(trays, itemClickListener);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}