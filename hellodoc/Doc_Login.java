package com.example.ameya.hellodoc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Doc_Login extends AppCompatActivity {

    public static FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText mEmailText;
    private EditText mPasswordText;
    private Button mButton,mSignupButton;
    Firebase mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc__login);

        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();

        //setting reference
        mEmailText=(EditText)findViewById(R.id.docemailtext);
        mPasswordText=(EditText)findViewById(R.id.docpasswordtext);
        mButton=(Button)findViewById(R.id.doclogin);
        mSignupButton=(Button)findViewById(R.id.signupbutton);

        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener()
        {
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
           {
               if(firebaseAuth.getCurrentUser()!=null)
               {
                   Toast.makeText(Doc_Login.this,"Doc login:Success",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Doc_Login.this,Doc_Details.class));

               }
           }
        };
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignin();
            }
        });
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(Doc_Login.this,DocSignUp.class));
            }
        });

    }
    @Override
    public void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    private void startSignin()
    {
String email=mEmailText.getText().toString();
        String password=mPasswordText.getText().toString();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
        {
            Toast.makeText(Doc_Login.this,"Field found empty",Toast.LENGTH_SHORT).show();

        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful())
                    {
                       Toast.makeText(Doc_Login.this,"Invalid username or password",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
