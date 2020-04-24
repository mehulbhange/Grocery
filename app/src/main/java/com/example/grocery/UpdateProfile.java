package com.example.grocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.widget.Toast.LENGTH_LONG;

public class UpdateProfile extends AppCompatActivity {

    EditText et_name;
    EditText et_mail;
    EditText et_address;
    EditText et_phone;

    final String[] m_Text = new String[1];

    Bitmap mBitmap;

    private Uri mImageUri;
    private StorageReference storageReference;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    FirebaseStorage storage = FirebaseStorage.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        et_name = (EditText) findViewById(R.id.et_name);
        et_mail = (EditText) findViewById(R.id.et_mail);
        et_address = (EditText) findViewById(R.id.et_address);
        et_phone = (EditText) findViewById(R.id.et_phone);
        storageReference = FirebaseStorage.getInstance().getReference();
        mFirebaseDatabase =FirebaseDatabase.getInstance();

        // Name
        databaseReference = mFirebaseDatabase.getReference("userData/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Name");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Getting the string value of that node
                String name =  dataSnapshot.getValue(String.class);
                et_name.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //Email
        databaseReference = mFirebaseDatabase.getReference("userData/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Email");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Getting the string value of that node
                String email =  dataSnapshot.getValue(String.class);
                et_mail.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //Address
        databaseReference = mFirebaseDatabase.getReference("userData/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Address");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Getting the string value of that node
                String address =  dataSnapshot.getValue(String.class);
                et_address.setText(address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        // Contact

        databaseReference = mFirebaseDatabase.getReference("userData/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Contact");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Getting the string value of that node
                String contact =  dataSnapshot.getValue(String.class);
                et_phone.setText(contact);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }

    public void onClickSave(View view) {


        //Verify Password

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Password");
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text[0] = input.getText().toString();
                verifyUser();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });

        builder.show();



    }

    public void onClickCancel(View view) {
        finish();
    }

    public void verifyUser() {

        final String email = et_mail.getText().toString();
        final String name = et_name.getText().toString();
        final String address = et_address.getText().toString();
        final String phone = et_phone.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone)) {
            Toast.makeText(UpdateProfile.this, " All Fields are Mandatory.", LENGTH_LONG).show();
        } else {

            mAuth.signInWithEmailAndPassword(email, m_Text[0])
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = mAuth.getCurrentUser();


                                mFirebaseDatabase = FirebaseDatabase.getInstance();
                                databaseReference = mFirebaseDatabase.getReference("userData");

                                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue("UserID");
                                databaseReference = mFirebaseDatabase.getReference("userData/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
                                databaseReference.child("Email").setValue(email);
                                databaseReference.child("Name").setValue(name);
                                databaseReference.child("Address").setValue(address);
                                databaseReference.child("Contact").setValue(phone);

                                Toast.makeText(UpdateProfile.this, "Updated.",
                                        LENGTH_LONG).show();
                                finish();
                                // Sign in success, update UI with the signed-in user's information

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(UpdateProfile.this, "Invalid Password.",
                                        LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
