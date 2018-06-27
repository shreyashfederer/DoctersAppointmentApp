package com.example.ameya.hellodoc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Patient_login extends AppCompatActivity {

    public static FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView mEmailText,mPasswordText;
    private Button mLogin,mSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);


        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener()
        {
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    Toast.makeText(Patient_login.this,"Patient login:Success",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Patient_login.this,Patient_detaills.class));

                }
            }
        };

        mEmailText=(TextView)findViewById(R.id.editText2);
        mPasswordText=(TextView)findViewById(R.id.editText4);
        mLogin=(Button)findViewById(R.id.button4);
        mSignup=(Button)findViewById(R.id.button6);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignin();
            }
        });

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(Patient_login.this,DocSignUp.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignin()
    {
        String email=mEmailText.getText().toString();
        String password=mPasswordText.getText().toString();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
        {
            Toast.makeText(Patient_login.this,"Field is empty",Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful())
                    {
                        Toast.makeText(Patient_login.this,"Invalid email or password",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
