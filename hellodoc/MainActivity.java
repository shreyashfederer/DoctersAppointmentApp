package com.example.ameya.hellodoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {

    Button patientButton,docButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientButton=(Button)findViewById(R.id.button2);
        docButton=(Button)findViewById(R.id.button3);
        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this,Patient_login.class));
            }
        });
        docButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Doc_Login.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}

