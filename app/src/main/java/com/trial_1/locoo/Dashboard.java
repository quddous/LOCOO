package com.trial_1.locoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Dashboard extends AppCompatActivity {

    FirebaseAuth auth;
    Button button3;
    Button button4;
    EditText edCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        auth = FirebaseAuth.getInstance();

        getSupportActionBar().setTitle("Toolbar");


        edCode = findViewById(R.id.edCode);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        URL serverURL;
        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultoptions = new JitsiMeetConferenceOptions.Builder().
                    setServerURL(serverURL).setWelcomePageEnabled(false).build();
            JitsiMeet.setDefaultConferenceOptions(defaultoptions);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder().
                        setRoom(edCode.getText().toString()).setWelcomePageEnabled(false).build();

                JitsiMeetActivity.launch(Dashboard.this,options);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu  );
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {

            case R.id.setting:
                Toast.makeText(Dashboard.this,"To be developed", Toast.LENGTH_SHORT).show();
                /*Intent intent1 = new Intent(Dashboard.this,Setting.class);
                startActivity(intent1);*/
                break;

            case R.id.logout:
                Toast.makeText(Dashboard.this,"logging out", Toast.LENGTH_SHORT).show();
                auth.signOut();
                Intent intent2 = new Intent(Dashboard.this,Signin.class);
                startActivity(intent2);
                break;

        }
        return true;
    }
}