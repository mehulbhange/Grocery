package com.example.grocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

import static android.widget.Toast.LENGTH_LONG;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    private FirebaseAuth.AuthStateListener mAuthListener;

    EditText et_email;
    EditText et_pass;
    Button btn_singup;
    EditText et_name;
    EditText et_address;
    EditText et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_address = (EditText) findViewById(R.id.et_address);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_pass = (EditText) findViewById(R.id.et_password);
        btn_singup = (Button) findViewById(R.id.btn_signup);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(SignUp.this,Home.class));
                    finish();
                }

            }
        };


    }

    public void onClickSignUp(View view) {
        startSignUp();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void startSignUp(){
        final String email = et_email.getText().toString();
        String password = et_pass.getText().toString();
        final String name = et_name.getText().toString();
        final String address = et_address.getText().toString();
        final String phone = et_phone.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone)){
            Toast.makeText(SignUp.this, " All Fields are Mandatory.", LENGTH_LONG).show();
        }else{

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = mAuth.getCurrentUser();


                                mFirebaseDatabase = FirebaseDatabase.getInstance();
                                databaseReference = mFirebaseDatabase.getReference("userData");

                                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue("UserID");
                                databaseReference = mFirebaseDatabase.getReference("userData/"+FirebaseAuth.getInstance().getCurrentUser().getUid());
                                databaseReference.child("Email").setValue(email);
                                databaseReference.child("Name").setValue(name);
                                databaseReference.child("Address").setValue(address);
                                databaseReference.child("Contact").setValue(phone);


                                // Sign in success, update UI with the signed-in user's information

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUp.this, "Something went wrong.",
                                        LENGTH_LONG).show();
                            }
                        }
                    });


        }


    }

    public void onClickLogin(View view) {
        startActivity(new Intent(SignUp.this,Login.class));
        finish();
    }
}
