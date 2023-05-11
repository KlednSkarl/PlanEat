package com.example.ui_presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class meal_plan extends AppCompatActivity implements RecyclerViewInterface{
        public String sched;
        ArrayList<MealModel> mealModels = new ArrayList<>();
            int [] mealImages = {R.drawable.ampalaya,R.drawable.bistek_tgalog,R.drawable.champorado,R.drawable.chicken_adobo
            ,R.drawable.escabeche,R.drawable.friedrice,R.drawable.lugaw,R.drawable.munggo,R.drawable.nilagang_beef,R.drawable.pandesal, R.drawable.pinakbet,R.drawable.pork_humba,
            R.drawable.sinigangfish,R.drawable.taho,R.drawable.tofu_tinola};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);
            RecyclerView recyclerView =     findViewById(R.id.mRecyclerView);
        TextView tvsched = findViewById(R.id.time);



        setMealModels();

        Meal_RecyclerViewAdapter adapter = new Meal_RecyclerViewAdapter(this, mealModels,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String schedule = getIntent().getStringExtra("schedule");
        tvsched.setText(schedule);
        sched=schedule;
    }


    private  void  setMealModels(){
        String[] mealNames = getResources().getStringArray(R.array.menu_meals);
        String[] mealDescription = getResources().getStringArray(R.array.meal_description);

        for (int i =0; i<mealNames.length;i++){

            mealModels.add(new MealModel(mealNames[i],mealDescription[i],mealImages[i]));
        }
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(meal_plan.this,Summary.class);

        intent.putExtra("schedule", sched);
        intent.putExtra("Meal_name", mealModels.get(position).getMeal_Name());
        intent.putExtra("meal_desc", mealModels.get(position).getMeal_Desc());
        intent.putExtra("meal_image", mealModels.get(position).getImage());

        startActivity(intent);
    }
}