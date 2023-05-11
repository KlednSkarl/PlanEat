package com.example.ui_presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private TextInputLayout text_Email, text_pass;
    Handler handler = new Handler();
    TextInputEditText email_input, pass_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
                text_Email = findViewById(R.id.textInputEmail);
                text_pass = findViewById(R.id.textInputpass);
                email_input = findViewById(R.id.input_email);
                pass_input = findViewById(R.id.input_password);

    }

    private boolean validateEmail(){
        String email = text_Email.getEditText().getText().toString().trim();
        if (email.isEmpty()){
            text_Email.setError("Field can't be empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            text_Email.setError("Wrong credentials inputted");
            return false;
        }
        else {
            text_Email.setError(null);
            return true;
        }

    }

    private boolean validatePass(){
        String pass = text_pass.getEditText().getText().toString().trim();
        if (pass.isEmpty()){
            text_pass.setError("Field can't be empty");
            return false;
        }
        else
            text_pass.setError(null);
            return true;
    }


    public void gotoReg(View view) {
        finish();
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }

    public void gotoMainMenu(View view) {

        if (!validateEmail() | !validatePass()){

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    text_Email.setError(null);
                    text_pass.setError(null);
                }
            }, 3000);


        }
        else
        {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            String email_log = email_input.getText().toString().trim();
            String pass_log = pass_input.getText().toString().trim();

            mAuth.signInWithEmailAndPassword(email_log,pass_log).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login.this, "Welcome to Plan Eat", Toast.LENGTH_SHORT).show();
                       finish();
                        Intent i = new Intent(Login.this, allIN.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(Login.this, "Log In Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });




        }
    }
}