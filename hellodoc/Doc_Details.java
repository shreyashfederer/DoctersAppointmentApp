package com.example.ameya.hellodoc;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Map;
import java.util.PriorityQueue;

public class Doc_Details extends AppCompatActivity {


    public  DatabaseReference mDatabase;

    private RecyclerView mDocDetails;
    FloatingActionButton mFAB;
    public static String currentuser,key;
    public Query mQuery;


    public static PriorityQueue<PriorityDecide> priorityQueue = new PriorityQueue<PriorityDecide>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc__details);

        currentuser=Doc_Login.mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Doctors").child("information");

        mQuery=mDatabase.orderByChild("user_id").equalTo(currentuser);
        //  Log.v("E_VALUE","Value : "+currentuser);


        mDocDetails = (RecyclerView) findViewById(R.id.doc_details);
        mDocDetails.setHasFixedSize(true);
        mDocDetails.setLayoutManager(new LinearLayoutManager(this));
        mFAB=(FloatingActionButton)findViewById(R.id.fab);


        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyget();
                startActivity(new Intent(Doc_Details.this,ShowAppointmentList.class));





            }
        });

    }

    private void keyget() {

        mQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                key = dataSnapshot.getKey();
                // Log.v("E_VALUE", "Key : " + key);



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

            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(Doc_Details.this,MainActivity.class));
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<Doc_read, Doc_DetailsHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Doc_read, Doc_DetailsHolder>(
                Doc_read.class,
                R.layout.doc_details_page,
                Doc_DetailsHolder.class,
                mQuery
        ) {
            @Override
            protected void populateViewHolder(Doc_DetailsHolder viewHolder, Doc_read model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setAge(model.getAge());
                viewHolder.setAddress(model.getAddress());
                viewHolder.setQualification(model.getQualification());
                viewHolder.setSex(model.getSex());
                viewHolder.setMci_no(model.getMci_no());
                viewHolder.setSpecialization(model.getSpecialization());
                viewHolder.setDoc_pics(getApplicationContext(), model.getDoc_pics());
            }
        };
        mDocDetails.setAdapter(firebaseRecyclerAdapter);
    }

    public static class Doc_DetailsHolder extends RecyclerView.ViewHolder {
        View mView;

        public Doc_DetailsHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String name) {
            TextView mDocName = (TextView) mView.findViewById(R.id.doc_name);
            mDocName.setText(name);
        }

        public void setAge(String age) {
            TextView mDocAge = (TextView) mView.findViewById(R.id.doc_age);
            mDocAge.setText(age);
        }

        public void setQualification(String qualification) {
            TextView mDocQualification = (TextView) mView.findViewById(R.id.doc_qualification);
            mDocQualification.setText(qualification);
        }

        public void setAddress(String address) {
            TextView mDocAddress = (TextView) mView.findViewById(R.id.doc_address);
            mDocAddress.setText(address);
        }

        public void setMci_no(String mci_no) {
            TextView mDocmci = (TextView) mView.findViewById(R.id.doc_mcino);
            mDocmci.setText(mci_no);
        }

        public void setSpecialization(String specialization) {
            TextView mDocSpecialization = (TextView) mView.findViewById(R.id.doc_speciaization);
            mDocSpecialization.setText(specialization);
        }

        public void setSex(String sex) {
            TextView mDocSex = (TextView) mView.findViewById(R.id.doc_sex);
            mDocSex.setText(sex);
        }

        public void setDoc_pics(Context ctx, String doc_pics) {
            ImageView mDocView = (ImageView) mView.findViewById(R.id.doc_pic);
            Picasso.with(ctx).load(doc_pics).into(mDocView);
        }
    }



}


