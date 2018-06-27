package com.example.ameya.hellodoc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PatientRegisteration2 extends AppCompatActivity {


    private ProgressDialog mProgressDialog;
    private EditText mPatientWeight;
    private Spinner mBloodGroup, mSex;
    private RadioButton rb1, rb2,rb3,rb4;
    private CheckBox cb1, cb2;

    public static String phisycallydisabled, sugar;
        public DatabaseReference mPatientDatabase,newPatient;
    public StorageReference mPatientStorage;
    public String patientweight, bloodgroup, sex;


    FirebaseUser patient_user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registeration2);

        mPatientDatabase=FirebaseDatabase.getInstance().getReference().child("Patients");
        mPatientStorage=FirebaseStorage.getInstance().getReference();

        mPatientWeight = (EditText) findViewById(R.id.patientweight);

        mBloodGroup = (Spinner) findViewById(R.id.bloodgroupspinner);
        mSex = (Spinner) findViewById(R.id.patientsexspinner);

        rb1 = (RadioButton) findViewById(R.id.yesradio);
        rb2 = (RadioButton) findViewById(R.id.noradio);

        rb3 = (RadioButton) findViewById(R.id.yesradio2);
        rb4 = (RadioButton) findViewById(R.id.noradio2);
        mProgressDialog=new ProgressDialog(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.done) {
            storePatientDetails();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Patient_Registration.GALLERY_REQUEST && resultCode==RESULT_OK)
        {
            Patient_Registration. patientimage=data.getData();
            Patient_Registration.mPatientImage.setImageURI(Patient_Registration.patientimage);
        }
    }

    private void storePatientDetails() {

        mProgressDialog.setMessage("Awesome!!" + "\n" + " Please wait while Data getting uploaded");
        mProgressDialog.show();
       final String patientweight = mPatientWeight.getText().toString();
       final String bloodgroup = mBloodGroup.getSelectedItem().toString();
        final String sex = mSex.getSelectedItem().toString();

        if (rb1.isChecked()) {
            sugar = "Yes";
        } else if (rb2.isChecked()) {
            sugar = "No";
        }
        if (rb3.isChecked()) {
            phisycallydisabled = "Yes";
        } else if (rb4.isChecked()) {
            phisycallydisabled = "No";
        }


        StorageReference path=mPatientStorage.child("Patient_Photos").child(Patient_Registration.patientimage.getLastPathSegment());
        path.putFile(Patient_Registration.patientimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                @SuppressWarnings("VisibleForTests")Uri  donwloadurl = taskSnapshot.getDownloadUrl();

                newPatient = mPatientDatabase.push();

                newPatient.child("name").setValue(Patient_Registration.patientname);
                newPatient.child("age").setValue(Patient_Registration.patientage);
                newPatient.child("phoneNo").setValue(Patient_Registration.patinetphone);
                newPatient.child("address").setValue(Patient_Registration.patientaddress);
                newPatient.child("image_URL").setValue(donwloadurl.toString());
                newPatient.child("user_id").setValue(DocSignUp.mAuth.getCurrentUser().getUid());

                newPatient.child("weight").setValue(patientweight);
                newPatient.child("blood_Group").setValue(bloodgroup);
                newPatient.child("sex").setValue(sex);
                newPatient.child("sugar").setValue(sugar);
                newPatient.child("physically").setValue(phisycallydisabled);
            }
        });




        mProgressDialog.dismiss();
        startActivity(new Intent(PatientRegisteration2.this, Patient_detaills.class));
    }
}

