package com.example.ameya.hellodoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Appointment_Patient extends AppCompatActivity {

    public static String getspecialization;
    public EditText mDescription;
    public Button mButton;


    private Spinner mSpinner;
    @Override
    protected void home

    Appointment_Patient(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment__patient);

        mSpinner=(Spinner)findViewById(R.id.specialization_spinner);
        mDescription=(EditText)findViewById(R.id.description);
        mButton=(Button)findViewById(R.id.search_doc);

        String desc=mDescription.getText().toString();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getspecialization=mSpinner.getSelectedItem().toString();
            startActivity(new Intent(Appointment_Patient.this,Doc_search.class));
            }
        });


    }
}
