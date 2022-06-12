package com.trial_1.locoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.trial_1.locoo.databinding.ActivitySigninBinding;
import com.trial_1.locoo.databinding.ActivitySignupBinding;

import java.util.Objects;

public class Signin extends AppCompatActivity {

    private Button button;
    private TextView createacc;


    ActivitySigninBinding binding;
    ProgressDialog progressdialog;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressdialog = new ProgressDialog(Signin.this);
        progressdialog.setTitle("logingin");
        progressdialog.setMessage("we're logingin your account");

        createacc = findViewById(R.id.createacc);
        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signin.this,Signup.class);
                startActivity(intent);
            }
        });

        button = findViewById(R.id.button);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressdialog.show();
                auth.signInWithEmailAndPassword(binding.siemail.getText().toString(), binding.sipassword.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressdialog.dismiss();
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(Signin.this, Dashboard.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Signin.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });
    }
}