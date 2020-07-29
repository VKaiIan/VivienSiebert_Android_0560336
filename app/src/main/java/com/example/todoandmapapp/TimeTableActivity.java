package com.example.todoandmapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TimeTableActivity extends AppCompatActivity {

    Button mobtn;
    Button tuebtn;
    Button wedbtn;
    Button thubtn;
    Button fribtn;
    Button satbtn;
    Button sunbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);


        mobtn = findViewById(R.id.mondaybutton);
        mobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMonday();
            }
        });

        tuebtn = findViewById(R.id.tuesdaybutton);
        tuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTuesday();
            }
        });

        wedbtn = findViewById(R.id.wednesdaybutton);
        wedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWednesday();
            }
        });

        thubtn = findViewById(R.id.thursdaybutton);
        thubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThursday();
            }
        });

        fribtn = findViewById(R.id.fridaybutton);
        fribtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFriday();
            }
        });

        satbtn = findViewById(R.id.saturdaybutton);
        satbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSaturday();
            }
        });

        sunbtn = findViewById(R.id.sundaybutton);
        sunbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSunday();
            }
        });

    }

    public void openMonday(){
        Intent intentmo = new Intent(this, MondayActivity.class);
        startActivity(intentmo);
    }

    public void openTuesday(){
        Intent intenttue = new Intent(this, TuesdayActivity.class);
        startActivity(intenttue);
    }

    public void openWednesday(){
        Intent intentwed = new Intent(this, WednesdayActivity.class);
        startActivity(intentwed);
    }

    public void openThursday(){
        Intent intentthu = new Intent(this, ThursdayActivity.class);
        startActivity(intentthu);
    }

    public void openFriday(){
        Intent intentfri = new Intent(this, FridayActivity.class);
        startActivity(intentfri);
    }

    public void openSaturday(){
        Intent intentsat = new Intent(this, SaturdayActivity.class);
        startActivity(intentsat);
    }

    public void openSunday(){
        Intent intentsun = new Intent(this, SundayActivity.class);
        startActivity(intentsun);
    }


}