package com.example.ameya.hellodoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import static android.R.attr.data;
import static android.R.attr.entries;

public class Doc_search extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView mListView;
    public ArrayList<String> entries=new ArrayList<>();
    public ArrayAdapter<String>arrayAdapter;
    public static String doc_key,name,doc_qualification,doc_picture,doc_specialization,doc_times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_search);


        mListView=(ListView)findViewById(R.id.listview);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Doctors").child("information");
        getDocs();
         arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,entries);
        mListView.setAdapter(arrayAdapter);

    }


    public static class DocDB
    {
        public  String name,specialization,key;

        public DocDB(String name,String specialization)
        {
            this.name=name;
           this.specialization=specialization;
           // this.key=key;
        }
    }

    private void getDocs() {

      mDatabase.addChildEventListener(new ChildEventListener() {
          @Override
          public void onChildAdded(DataSnapshot dataSnapshot, String s) {
              GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
              Map<String,String> map=dataSnapshot.getValue(genericTypeIndicator);


                 doc_key=dataSnapshot.getKey();
              Log.v("E_VALUE","KKey : "+doc_key);
              Toast.makeText(Doc_search.this,Appointment_Patient.getspecialization,Toast.LENGTH_SHORT).show();
              if(map.get("specialization").equals(Appointment_Patient.getspecialization)) {
                   name=map.get("name");
                  arrayAdapter.add(name);

                   doc_specialization=map.get("specialization");
                  //doc_key=map.get(dataSnapshot.getKey());
                  doc_picture=map.get("doc_pics");
                  doc_qualification=map.get("qualification");
                  doc_times=map.get("mci_no");


              }
              arrayAdapter.notifyDataSetChanged();

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


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Doc_search.this,Doc_take_appointment.class);
                intent.putExtra("Doc_Name",mListView.getItemIdAtPosition(i));
                startActivity(intent);

            }
        });
    }
}
