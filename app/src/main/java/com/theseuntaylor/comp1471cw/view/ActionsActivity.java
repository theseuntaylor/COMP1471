package com.theseuntaylor.comp1471cw.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.theseuntaylor.comp1471cw.R;

public class ActionsActivity extends AppCompatActivity {

    private Button viewTraysBtn, createTraysBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);

        viewTraysBtn = findViewById(R.id.viewTraysButton);
        createTraysBtn = findViewById(R.id.createNewTrayButton);

        actions();
    }

    private void actions() {
        viewTraysBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, ViewTraysActivity.class);
            startActivity(i);
        });
        createTraysBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, CreateTrayActivity.class);
            startActivity(i);
        });
    }
}