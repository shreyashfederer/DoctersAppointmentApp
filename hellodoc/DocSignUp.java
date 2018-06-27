package com.example.ameya.hellodoc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.PriorityQueue;

import static com.example.ameya.hellodoc.R.id.radioButton;

public class DocSignUp extends AppCompatActivity {
 public static FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button mButtonSignUp;
    private TextView mEmail,mPassword,mconPassword;

    private RadioButton rb1,rb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_sign_up);
        mButtonSignUp=(Button)findViewById(R.id.signupbutton);

        mEmail=(TextView)findViewById(R.id.editText7);
        mPassword=(TextView)findViewById(R.id.editText10);
        mconPassword=(TextView)findViewById(R.id.editText12);
        mButtonSignUp=(Button)findViewById(R.id.button5);

        rb1=(RadioButton)findViewById(radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);

        mAuth=FirebaseAuth.getInstance();
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }
    private void registerUser()
    {
        String email=mEmail.getText().toString();
        String password=mPassword.getText().toString();
        String conPassword=mconPassword.getText().toString();

        if(password.equals(conPassword))
        {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        //startActivity(new Intent(DocSignUp.this, Doc_registration.class));

                        if(rb1.isChecked()) {
                            startActivity(new Intent(DocSignUp.this, Doc_registration.class));
                        }
                        else if(rb2.isChecked())
                        {
                            startActivity(new Intent(DocSignUp.this,Patient_Registration.class));
                        }
                        Toast.makeText(DocSignUp.this,"Successful",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(DocSignUp.this,"Make sure password is greater than 6 chaeacters",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else
        {
            Toast.makeText(DocSignUp.this,"Password didn't match",Toast.LENGTH_SHORT).show();
        }
    }


}
