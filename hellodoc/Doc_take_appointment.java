package com.example.ameya.hellodoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;

import java.util.Iterator;
import java.util.Map;

public class Doc_take_appointment extends AppCompatActivity {
        private Button mButton;
    private TextView mName,mQualification,mSpecialization,mTime;
    DatabaseReference mDatabase;
    DatabaseReference mAppointment,mpatient_appoint;


    Patient_read patient_read=new Patient_read();
    public String physic,sugar,name,age;
    FirebaseAuth mAuth;
    public String currentuserid;
   private Query mQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_take_appointment);
        mButton=(Button)findViewById(R.id.appointment_fix);
        mName=(TextView)findViewById(R.id.d_name);
        mQualification=(TextView)findViewById(R.id.d_qualification);
        mSpecialization=(TextView)findViewById(R.id.de_specialization);
        mTime=(TextView)findViewById(R.id.d_time);



         currentuserid=Patient_login.mAuth.getCurrentUser().getUid();
            mDatabase= FirebaseDatabase.getInstance().getReference().child("Patients");
            mAppointment=FirebaseDatabase.getInstance().getReference().child("Doctors").child("Appointments").child(Doc_search.doc_key);


            mQuery=mDatabase.orderByChild("user_id").equalTo(currentuserid);
        Log.v("E_VALUE","Value : "+currentuserid);
       // Bundle bundle=getIntent().getExtras();


        mName.setText(Doc_search.name);
        mQualification.setText(Doc_search.doc_qualification);
        mSpecialization.setText(Doc_search.doc_specialization);
        mTime.setText(Doc_search.doc_times);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatientDetails();
                Toast.makeText(Doc_take_appointment.this, "Appointment has been fixed!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Doc_take_appointment.this,Patient_detaills.class));


            }
        });

    }

    private void PatientDetails() {
        mQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String,String>>() {};
                Map<String,String> map=dataSnapshot.getValue(genericTypeIndicator);



                    physic=map.get("physically");
                    sugar=map.get("sugar");
                    name=map.get("name");
                age=map.get("age");

                mpatient_appoint=mAppointment.child("");
                mpatient_appoint.child(name.toLowerCase()).setValue(currentuserid);


                Log.v("E_VALUE","Value : "+name);
                Log.v("E_VALUE","p : "+physic);
                Log.v("E_VALUE","Value : "+sugar);
                Log.v("E_VALUE","Value : "+age);
               /* PriorityDecide priorityDecide=new PriorityDecide(name,physic,sugar,age);
                Doc_Details.priorityQueue.add(priorityDecide);
                Iterator<PriorityDecide> iterator=Doc_Details.priorityQueue.iterator();
                while(iterator.hasNext())
                {

                    Log.v("E_VALUE","Queue values:"+iterator.next().name);

                }*/

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.getDetails();
            }
        });
    }

}
