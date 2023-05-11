package com.example.ui_presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Planning_phase extends AppCompatActivity {

    Button btn_morning, btn_lunch, btn_dinner, view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_phase);
        btn_morning = findViewById(R.id.breakfast);
        btn_lunch = findViewById(R.id.lunch);
        btn_dinner = findViewById(R.id.dinner);

        Intent i = new Intent(Planning_phase.this,meal_plan.class);

        btn_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("schedule","Breakfast" );
                startActivity(i);
            }
        });

        btn_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("schedule", "Lunch");
                startActivity(i);
            }
        });

        btn_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("schedule", "Dinner");
                startActivity(i);
            }
        });



    }
}