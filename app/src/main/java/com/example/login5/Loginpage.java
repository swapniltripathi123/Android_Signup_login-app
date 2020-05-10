package com.example.login5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Loginpage extends AppCompatActivity {
    EditText edt4, edt5;
    Button btn1;
    TextView tv5;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        edt4 = (EditText) findViewById(R.id.edt4);
        edt5 = (EditText) findViewById(R.id.edt5);
        btn1 = (Button) findViewById(R.id.btn1);
        tv5 = (TextView) findViewById(R.id.tv5);
        fAuth = (FirebaseAuth)FirebaseAuth.getInstance();



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt4.getText().toString().trim();
                String pwd = edt5.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    edt4.setError("Please enter emailId");
                    return;
                } if (TextUtils.isEmpty(pwd)) {
                    edt5.setError("Please enter password");
                    return;

                }
                if(pwd.length()<6){
                    edt5.setError("Password length should be > 6");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(Loginpage.this, "login ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Loginpage.this, Home.class));
                        } else {
                            Toast.makeText(Loginpage.this, "error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        });
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Loginpage.this, MainActivity.class);
                startActivity(i);
            }
        });
    }}
