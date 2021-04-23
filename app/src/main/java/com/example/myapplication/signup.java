package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    private EditText email, fullName, username, password;
    private Button signUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initials();
        onClickListeners();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    private void initials() {

        email = findViewById(R.id.email);
        fullName = findViewById(R.id.full_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.sign_up);
    }

    private void onClickListeners() {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String mail = email.getText().toString();
//                String name = fullName.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                mAuth = FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(user,pass)
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    username.setText("");
                                    password.setText("");
                                    Toast.makeText(getApplicationContext(),"Registered successfully",Toast.LENGTH_LONG).show();
                                    Intent homeIntent=new Intent(signup.this,login.class);
                                    startActivity(homeIntent);
                                } else {
                                    username.setText("");
                                    password.setText("");

                                    Toast.makeText(getApplicationContext(),"Registered not successfully",Toast.LENGTH_LONG).show();

                                }
                            }
                        });

            }
        });
    }

    private void setFocus(EditText field, String error) {
        Toast toast = Toast.makeText(getApplicationContext(), "Enter your " + error + "!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        field.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(field, InputMethodManager.SHOW_IMPLICIT);
    }

    // Whether it is in conflict with information in database or not.
    private boolean isInConflict() {
        // ...
        return false;
    }
}
