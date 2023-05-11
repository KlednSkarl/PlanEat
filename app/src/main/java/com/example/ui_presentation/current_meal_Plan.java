package com.example.ui_presentation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class current_meal_Plan extends AppCompatActivity {

    TextView tvBreakfast, tvLunch,tvDinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_meal_plan);

        tvBreakfast = findViewById(R.id.breakfast);
        tvLunch = findViewById(R.id.lunch);
        tvDinner = findViewById(R.id.dinner);


        String uid = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestore.collection("users").document(uid);

    documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
        @Override




        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {


            if (error!=null){
                Toast.makeText(current_meal_Plan.this, "", Toast.LENGTH_SHORT).show();
            }
            else {
                    tvBreakfast.setText(value.getString("breakfast"));
                    tvLunch.setText(value.getString("lunch"));
                    tvDinner.setText(value.getString("dinner"));
            }
        }
    });

    }


    public void exit(View view) {
        startActivity(new Intent(current_meal_Plan.this,Mainmenu.class));
    }
}