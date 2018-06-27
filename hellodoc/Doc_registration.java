package com.example.ameya.hellodoc;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.PriorityQueue;

public class Doc_registration extends AppCompatActivity {

    public EditText mdob;
    public Calendar myCalendar;
    private FirebaseAuth mAuth;
    private EditText mDocName,mDocAge,mDocQuali,mDocAddress,mMCINo;
    private Spinner mSex,mSpecialization;

    private Uri imageuri=null;
    private StorageReference mStorage;
    private ProgressDialog mProgressDialog;
    private DatabaseReference mDatabase;



    private ImageButton mSelectImage;
    private static final int GALLERY_REQUEST=1;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_registration);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Doctors").child("information");
        mStorage=FirebaseStorage.getInstance().getReference();
        mProgressDialog=new ProgressDialog(this);



        mDocName=(EditText)findViewById(R.id.docname);
        mDocAge=(EditText)findViewById(R.id.docage);
        mDocQuali=(EditText)findViewById(R.id.docquali);
        mDocAddress=(EditText)findViewById(R.id.docaddress);
        mMCINo=(EditText)findViewById(R.id.mcino);

        mSex=(Spinner)findViewById(R.id.spinner);
        mSpecialization=(Spinner)findViewById(R.id.spinner2);

       // PriorityQueue<String> maxheap=new PriorityQueue<String>(Collections.reverseOrder());




        mSelectImage=(ImageButton)findViewById(R.id.docimage);
        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST);
            }
        });



    }

    private void storeDocDetails() {
        mProgressDialog.setMessage("Please wait...Data being uploaded");
        mProgressDialog.show();
        final String docname=mDocName.getText().toString();
        final String docAge=mDocAge.getText().toString();
        final String docquali=mDocQuali.getText().toString();
        final String docaddress=mDocAddress.getText().toString();
        final String docmci=mMCINo.getText().toString();
        final String specialization=mSpecialization.getSelectedItem().toString();
        final String Sex=mSex.getSelectedItem().toString();
        if(!TextUtils.isEmpty(docname) && !TextUtils.isEmpty(docAge) && !TextUtils.isEmpty(docquali)&& !TextUtils.isEmpty(docaddress) && !TextUtils.isEmpty(docmci) && imageuri!=null)
        {
             StorageReference path=mStorage.child("Doctor_Photos").child(imageuri.getLastPathSegment());
            path.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                  @SuppressWarnings("VisibleForTests") Uri donwloadurl=taskSnapshot.getDownloadUrl();



                    DatabaseReference newDoctor=mDatabase.push();
                    newDoctor.child("name").setValue(docname);
                    newDoctor.child("age").setValue(docAge);
                    newDoctor.child("qualification").setValue(docquali);
                    newDoctor.child("address").setValue(docaddress);
                    newDoctor.child("mci_no").setValue(docmci);
                    newDoctor.child("doc_pics").setValue(donwloadurl.toString());
                    newDoctor.child("sex").setValue(Sex);
                    newDoctor.child("specialization").setValue(specialization);
                    newDoctor.child("user_id").setValue(DocSignUp.mAuth.getCurrentUser().getUid());

                    mProgressDialog.dismiss();
                    startActivity(new Intent(Doc_registration.this,Doc_Details.class));
                }
            });
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_REQUEST && resultCode==RESULT_OK)
        {
             imageuri=data.getData();
            mSelectImage.setImageURI(imageuri);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.done) {
            storeDocDetails();
        }
        return super.onOptionsItemSelected(item);
    }
}
