package com.example.ameya.hellodoc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Patient_Registration extends AppCompatActivity {


    private EditText mPatientName,mPatienPhone,mPatientAddress;
    private EditText mPatientAge;
    public static   Uri patientimage=null;
    public static ImageButton mPatientImage;
    public static final int GALLERY_REQUEST=1;
    private ProgressDialog mProgressDialog;

    public  FirebaseUser patient_user= FirebaseAuth.getInstance().getCurrentUser();
     public static String patientname,patinetphone,patientaddress;
    public static String patientage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__registration);


        mProgressDialog=new ProgressDialog(this);




        mPatientName=(EditText)findViewById(R.id.patientname) ;
        mPatientAge= (EditText)findViewById(R.id.patient_age);
        mPatienPhone=(EditText)findViewById(R.id.patientphone);
        mPatientAddress=(EditText)findViewById(R.id.patientaddress);

        mPatientImage=(ImageButton)findViewById(R.id.patientphoto);
        mPatientImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST);
            }
        });
    }


    public void storePatientDetails1()
    {
        mProgressDialog.setMessage("Please wait...Dont press back" +"\n"+
                "Few more fields to be filled");
        mProgressDialog.show();

          patientname=mPatientName.getText().toString();
          patientage=mPatientAge.getText().toString();
          patinetphone=mPatienPhone.getText().toString();
         patientaddress=mPatientAddress.getText().toString();


    mProgressDialog.dismiss();
    startActivity(new Intent(Patient_Registration.this, PatientRegisteration2.class));



                }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK)
        {
             patientimage=data.getData();
            mPatientImage.setImageURI(patientimage);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.forward) {
           storePatientDetails1();
        }
        return super.onOptionsItemSelected(item);
    }

}
