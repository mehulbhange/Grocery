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

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    EditText et_email;
    EditText et_pass;
    Button btn_singup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        et_email = (EditText) findViewById(R.id.et_email);
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
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);

        mAuth.addAuthStateListener(mAuthListener);
    }

    public void startSignUp(){
        String email = et_email.getText().toString();
        String password = et_pass.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(SignUp.this, "Fields are Empty.",
                    Toast.LENGTH_LONG).show();
        }else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUp.this, "Authentication failed.",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });


        }


    }

}
