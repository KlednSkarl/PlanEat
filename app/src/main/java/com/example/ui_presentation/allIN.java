package com.example.ui_presentation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class allIN extends AppCompatActivity {
    public String name;

    private TextInputLayout textName,textHeight,textWeight; // textlayout
    public TextInputEditText et_name, et_height, et_weight; // data
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allin);

//       Objects.requireNonNull(getSupportActionBar()).hide(); // taga tago ng action bar tol
        textName = findViewById(R.id.text_username);
        textHeight = findViewById(R.id.text_height);
        textWeight = findViewById(R.id.text_weight);

        et_name = findViewById(R.id.et_username);
        et_height= findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);





//            String name = currentUser.getDisplayName();
//            String email = currentUser.getEmail();



//
//            Toast.makeText(this, "" + currentUser, Toast.LENGTH_SHORT).show();
//            Log.d("MainActivity","" +email);
//







    }
    private boolean validateName(){
           name = textName.getEditText().getText().toString().trim();
        if (name.isEmpty()) {

            textName.setError("Field can't be empty");
            return false;
        }
        else if (name.length()>20){
            textName.setError("User name too long");
            return false;
        }
        else{
            textName.setError(null);
            return true;
        }
    } // validating name

    private boolean validateHeight(){
        String height = textHeight.getEditText().getText().toString().trim();

        if(height.isEmpty()){
            textHeight.setError("Field can't be empty");
            return false;
        }




        else{
            textHeight.setError(null);
            return true;
        }
    } // validate height

    private boolean validateweight(){
        String weight = textWeight.getEditText().getText().toString().trim();


        if(weight.isEmpty()){
         textWeight.setError("Field can't be empty");
         return false;
        }
        else{
            textWeight.setError(null);
            return true;
        }
    } // validate weight

    public void validate(View v){



        if(!validateName() | !validateHeight() | !validateweight()){
            return;
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();



        String uid = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String weight = textWeight.getEditText().getText().toString();
        String height = textHeight.getEditText().getText().toString();
        String username = textName.getEditText().getText().toString();


        DocumentReference documentReference = firestore.collection("users").document(uid);


        Map<String, Object> user = new HashMap<>();
            user.put("weight",weight );
            user.put("height", height );
            user.put("username", username);

        documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


//                    documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//                        @Override
//                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                        }
//                    });


//               documentReference.addSnapshotListener(allIN.this, new EventListener<DocumentSnapshot>() {
//                   @Override
//                   public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                       if (error != null) {
//                           Toast.makeText(allIN.this, "" + error, Toast.LENGTH_SHORT).show();
//                       } else {
//                           et_weight.setText(value.getString("weight"));
//                           et_height.setText(value.getString("height"));
//                           et_name.setText(uid);
//                       }
//                   }
//               });


            }
        });

     Toast.makeText(this, "Hello There "+name, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,Mainmenu.class);
        finish();
        startActivity(intent);


    }

    @Override
    protected void onStart() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String uid = mAuth.getCurrentUser().getUid();
        DocumentReference userRef = db.collection("users").document(uid);

        userRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                assert value != null;
                String erp = (value.getString("username"));

                if (value.exists()){


                    finish();
                    Intent i = new Intent(allIN.this,Mainmenu.class);
                    startActivity(i);
                }
                else if (value.equals(null)){


                }
            }
        });





        super.onStart();
    }
}