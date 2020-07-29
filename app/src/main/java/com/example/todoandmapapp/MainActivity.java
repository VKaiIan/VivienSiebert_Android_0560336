package com.example.todoandmapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button timebutton;
    private Button mapbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timebutton = findViewById(R.id.timetableButton);
        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTable();
            }
        });

        mapbutton = findViewById(R.id.campusmapButton);
        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
    }

    public void openTable(){
        Intent intenttable = new Intent(this, TimeTableActivity.class);
        startActivity(intenttable);
    }

    public void openMap(){
        Intent intentmap = new Intent(this, BuildingMapActivity.class);
        startActivity(intentmap);
    }
}