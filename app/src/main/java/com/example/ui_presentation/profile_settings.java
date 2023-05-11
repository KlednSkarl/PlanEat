package com.example.ui_presentation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile_settings extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            CircleImageView profileImage = findViewById(R.id.profile_image);
            profileImage.setImageURI(imageUri);


        }
    }
    TextView tv_username,tv_weight,tv_height,tv_BMI,tv_email,tv_recommendation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestore.collection("users").document(uid);
         tv_username = findViewById(R.id.tv_username);
         tv_weight = findViewById(R.id.tv_weight);
         tv_height = findViewById(R.id.tv_height);
         tv_BMI = findViewById(R.id.tv_BMI);
         tv_email = findViewById(R.id.tv_email);
        tv_recommendation = findViewById(R.id.tv_recommendation);
        Button edit = findViewById(R.id.edit_profile);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profile_settings.this,allIN.class));
            }
        });

         documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {


             @Override
             public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {


                 if (error!=null){
                     Toast.makeText(profile_settings.this, "Good bye", Toast.LENGTH_SHORT).show();
                 }
                 else {
                     tv_username.setText(value.getString("username"));
                     tv_weight.setText(value.getString("weight"));
                     tv_height.setText(value.getString("height"));
                     tv_email.setText(uid);

                     float height = Float.parseFloat(tv_height.getText().toString())/100 ;
                     float weight = Float.parseFloat(tv_weight.getText().toString());
                   Log.d("Height", String.valueOf(height));
                     float bmi = weight / (height * height);

                     tv_BMI.setText(String.valueOf(bmi));

                 if (bmi<18.5){
                     tv_recommendation.setText("Consider consuming greater amount of foods");



                 }
                 else if (bmi>18.5 && bmi<24.9){
                     tv_recommendation.setText("Good job living healthy");
                     
                 } else if (bmi>24.9 && bmi<29.9) {
                     tv_recommendation.setText("Consider having a more proper diet");
                 }
                 else if (bmi>29.9) {
                     tv_recommendation.setText("Seek medical assistance");
                 }
                 else{
                     Toast.makeText(profile_settings.this, "No valid bmi acquired", Toast.LENGTH_SHORT).show();
                 }

                 }




             }
         });

//        String inputHeight = tv_height.getText().toString().trim();
//        String inputWeight = tv_weight.getText().toString().trim();





//
//        int inputValue = Integer.parseInt(inputHeight);
//        int inputValue1 = Integer.parseInt(inputWeight);
//
//        int bmi = inputValue1/(inputValue*inputValue);
//
//        tv_BMI.setText(bmi);

    }


    public void selectImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }




}