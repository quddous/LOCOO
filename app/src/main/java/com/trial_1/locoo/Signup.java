package com.trial_1.locoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.trial_1.locoo.databinding.ActivitySignupBinding;
import com.trial_1.locoo.models.users;

public class Signup extends AppCompatActivity {

    ActivitySignupBinding binding;
    private FirebaseAuth auth;
    private Button button2;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_signup);
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        ProgressDialog progressdialog = new ProgressDialog(Signup.this);
        progressdialog.setTitle("Creating acc");
        progressdialog.setMessage("we're creating your account");

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressdialog.show();
                auth.createUserWithEmailAndPassword(binding.suemail.getText().toString(), binding.supassword.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressdialog.dismiss();
                                if(task.isSuccessful()){
                                    users User = new users(binding.suemail.getText().toString(),binding.supassword.getText().toString(),
                                            binding.suname.getText().toString() );
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(User);

                                    Intent intent = new Intent(Signup.this,Signin.class);
                                    startActivity(intent);

                                    Toast.makeText(Signup.this,"user created successfully",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(Signup.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });








    }
}