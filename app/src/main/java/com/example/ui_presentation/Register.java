package com.example.ui_presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.text.BreakIterator;
import java.util.Objects;

public class Register extends AppCompatActivity {
   private TextInputLayout Input_Username, Input_Email, Input_pass, Input_repass;
   public TextInputEditText email_EditText, pass_EditText;


    public ProgressBar pgBar;
   Handler handler = new Handler();
   public String error_prompt = "Field cannot be empty";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


       Input_Email = findViewById(R.id.textInputEmail);
       Input_pass = findViewById(R.id.textInputpass);
       Input_repass = findViewById(R.id.Inputrepass);
       email_EditText = findViewById(R.id.input_email);
       pass_EditText = findViewById(R.id.input_password);






    } // main On create






    private boolean validateEmail(){
        String Inputted_Email = Input_Email.getEditText().getText().toString().trim();

        if (Inputted_Email.isEmpty()){
            Input_Email.setError(error_prompt);
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(Inputted_Email).matches()){
            Input_Email.setError("Please Enter a valid Email Address");
            return false;

        }
        else {
            Input_Email.setError(null);
            return true;
        }

    }  // validate email

    private boolean validatePass(){
        String Inputted_Pass = Input_pass.getEditText().getText().toString().trim();
        String comparing_pass = Input_repass.getEditText().getText().toString().trim();





        if (Inputted_Pass.isEmpty()){
            Input_pass.setError(error_prompt);
            return false;


        }
        else if (Inputted_Pass.length()<6){
            Input_pass.setError("Password is too short");
            return false;
        }
        if (!(Inputted_Pass.equals(comparing_pass))){
            Input_repass.setError("Password does not match");
            Input_pass.setError("Password does not match");
            return false;

        }

        else {
          Input_pass.setError(null);
          Input_repass.setError(null);
          return true;

      }

    } // validate password

    public void validate_credentials(View view) {


        if( !validateEmail() | !validatePass()){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Input_pass.setError(null);
                    Input_repass.setError(null);
                    Input_Email.setError(null);

                }
            },3000);
        }
        else
        {

            String email = email_EditText.getText().toString().trim();
            String pass = pass_EditText.getText().toString().trim();
      FirebaseAuth    mAuth = FirebaseAuth.getInstance();


            FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                            if (task.isSuccessful()) {
                                SignInMethodQueryResult result = task.getResult();
                                if (result.getSignInMethods().isEmpty()) {
                                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()){

                                                Toast.makeText(Register.this, "Success", Toast.LENGTH_SHORT).show();
                                                finish();
                                                Intent i = new Intent(Register.this, Login.class);
                                                startActivity(i);
                                            }
                                            else {

                                            }
                                        }
                                    });
                                } else {
                                    // Email is already registered
                                    Toast.makeText(Register.this, "User Already Exist", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Error occurred while checking email


                            }
                        }
                    });





//            Toast.makeText(Register.this,"Registration Complete",Toast.LENGTH_LONG).show();
//            Intent i = new Intent(Register.this, Login.class);
//            startActivity(i);
        }
    }





    public void gotoLogin(View view) {
        finish();
        Intent i = new Intent(Register.this,Login.class);
        startActivity(i);
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth    mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser!=null){
            finish();
            Intent i = new Intent(Register.this, Mainmenu.class);
            startActivity(i);
        }

    }

}