package com.example.grocery;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;
    EditText et_name;
    EditText et_email;
    EditText et_address;
    EditText et_contact;
    Button btn_update;
    Button btn_back;
    ImageView iv_profileimage;
    ImageView iv_userimage;
    private static final int PICK_IMAGE_RUQUEST = 1;

    private Uri mImageUri;
    private StorageReference storageReference;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    NavigationView navigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        et_email= (EditText) view.findViewById(R.id.et_email);
        et_address = (EditText) view.findViewById(R.id.et_address);
        et_contact = (EditText) view.findViewById(R.id.et_contact);
        et_name = (EditText) view.findViewById(R.id.et_name);
        btn_update = (Button) view.findViewById(R.id.btn_update);
        btn_back = (Button) view.findViewById(R.id.btn_back);
        navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        iv_profileimage = (ImageView) view.findViewById(R.id.iv_profileimage);
        iv_userimage = (ImageView) view.findViewById(R.id.iv_userimage);

        storageReference = FirebaseStorage.getInstance().getReference();

        iv_profileimage.setClickable(false);

        et_name.setFocusable(false);
        et_address.setFocusable(false);
        et_email.setFocusable(false);
        et_contact.setFocusable(false);

        return view;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mFirebaseDatabase =FirebaseDatabase.getInstance();

        //Set Image to Profile

        StorageReference mRef = storageReference.child("UserImages/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+".jpg");
        mRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(iv_profileimage);
            }
        });



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
                et_email.setText(email);
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
                et_contact.setText(contact);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv_profileimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openFileChooser();
                    }
                });


                btn_back.setText("Cancel");
                btn_update.setText("Save");
                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       StorageReference mRef = storageReference.child("UserImages/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"."+getFileExtension(mImageUri));

                       mRef.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                           @Override
                           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                               Toast.makeText(getActivity(), "Image upload success ", Toast.LENGTH_LONG).show();

                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {

                           }
                       });
                    }
                });

            }
        });



    }

    public void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_RUQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_RUQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageUri = data.getData();
            iv_profileimage.setImageURI(mImageUri);
        }
    }

    public String getFileExtension(Uri uri){
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

}

