package com.example.ameya.hellodoc;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.core.Context;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Patient_detaills extends AppCompatActivity {

    private FloatingActionButton mfab;

    private DatabaseReference mDatabase;
    private DatabaseReference mCurrentPatient;
    private RecyclerView mPatientDetails;
    private FirebaseAuth mAuth;

    private Query mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detaills);

        mAuth=FirebaseAuth.getInstance();

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Patients");

        String currentuserid=mAuth.getCurrentUser().getUid();
        mCurrentPatient=FirebaseDatabase.getInstance().getReference().child("Patients");
        mQuery=mCurrentPatient.orderByChild("user_id").equalTo(currentuserid);

        mPatientDetails=(RecyclerView)findViewById(R.id.patient_details);
        mPatientDetails.setHasFixedSize(true);
        mPatientDetails.setLayoutManager(new LinearLayoutManager(this));

        mfab=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        mfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(Patient_detaills.this,Appointment_Patient.class));
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(Patient_detaills.this,MainActivity.class));
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();

            FirebaseRecyclerAdapter<Patient_read,PatientDetailsHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Patient_read, PatientDetailsHolder>(
            Patient_read.class,
                    R.layout.patient_details_page,
                    PatientDetailsHolder.class,
                    mQuery
            ) {
                @Override
                protected void populateViewHolder(PatientDetailsHolder viewHolder, Patient_read model, int position) {
                    viewHolder.setName(model.getName());
                    viewHolder.setAge(model.getAge());
                    viewHolder.setAddress(model.getAddress());
                    viewHolder.setImage_URL(getApplicationContext(),model.getImage_URL());
                    //viewHolder.setSex(model.getSex());
                   // viewHolder.setAddress(model.getAddress());
                    //viewHolder.setBlood_Group(model.getBlood_Group());
                   // viewHolder.setPhoneNo(model.getPhoneNo());
                }
            };
            mPatientDetails.setAdapter(firebaseRecyclerAdapter);

    }
    public static  class PatientDetailsHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public PatientDetailsHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
        }
        public void setName(String name)
        {
           TextView mPatientName=(TextView)mView.findViewById(R.id.patient_name);
            mPatientName.setText(name);
        }
        public void setAge(String age)
        {
            TextView mPatientAge=(TextView)mView.findViewById(R.id.patient_age);
            mPatientAge.setText(age);
        }
        public void setImage_URL(android.content.Context ctx, String image_URL)
        {
            ImageView mPatientImage=(ImageView)mView.findViewById(R.id.patient_image);
            Picasso.with(ctx).load(image_URL).into(mPatientImage);
        }
       /* public void setSex(String sex)
        {
            TextView mPatientSex=(TextView)mView.findViewById(R.id.getpatientsex);
            mPatientSex.setText(sex);
        }*/
        public void setAddress(String address)
        {
            TextView mPatientAddress=(TextView)mView.findViewById(R.id.patient_address);
            mPatientAddress.setText(address);
        }
       /* public void setBlood_Group(String blood_group)
        {
            TextView mPatientBG=(TextView)mView.findViewById(R.id.getpatientbloodgroup);
            mPatientBG.setText(blood_group);
        }*/
       /* public void setPhoneNo(String phoneNo)
        {
           TextView mPatientMobieNo=(TextView)mView.findViewById(R.id.getpatientphoneno);
            mPatientMobieNo.setText(phoneNo);
        }*/
    }

}
