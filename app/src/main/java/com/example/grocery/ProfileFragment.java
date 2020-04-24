package com.example.grocery;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    TextView tv_name;
    TextView tv_email;
    TextView tv_address;
    TextView tv_contact;

    Button btn_update;
    Button btn_back;
    ImageView iv_profileimage;
    ImageView iv_userimage;
    private static final int PICK_IMAGE_RUQUEST = 1;
    Bitmap mBitmap;

    private Uri mImageUri;
    private StorageReference storageReference;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    NavigationView navigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tv_email= (TextView) view.findViewById(R.id.tv_email);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_contact = (TextView) view.findViewById(R.id.tv_contact);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        btn_update = (Button) view.findViewById(R.id.btn_update);
        btn_back = (Button) view.findViewById(R.id.btn_back);
        navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        iv_profileimage = (ImageView) view.findViewById(R.id.iv_profileimage);
        System.out.println(iv_profileimage);
        iv_userimage = (ImageView)getActivity().findViewById(R.id.iv_userimage);
        System.out.println(iv_userimage);


        storageReference = FirebaseStorage.getInstance().getReference();


        //Set Image to Profile

        StorageReference mRef = storageReference.child("UserImages/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+".jpg");
        mRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(iv_profileimage);
            }
        });


        return view;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mFirebaseDatabase =FirebaseDatabase.getInstance();

        // Name
        databaseReference = mFirebaseDatabase.getReference("userData/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Name");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Getting the string value of that node
                String name =  dataSnapshot.getValue(String.class);
                tv_name.setText(name);
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
                tv_email.setText(email);
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
                tv_address.setText(address);
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
                tv_contact.setText(contact);
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
                startActivity(new Intent(getActivity(),UpdateProfile.class));
            }
        });

        iv_profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();

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
            try {
                mBitmap = MediaStore.Images.Media.getBitmap( getActivity().getContentResolver() , mImageUri) ;
            } catch (IOException e) {
                e.printStackTrace();
            }
            iv_profileimage.setImageURI(mImageUri);

            StorageReference mRef = storageReference.child("UserImages/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"."+getFileExtension(mImageUri));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            byte[] bytedata = baos.toByteArray();

            UploadTask uploadTask = mRef.putBytes(bytedata);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getActivity(), "Image upload success ", Toast.LENGTH_LONG).show();
                    Picasso.with(getActivity()).load(mImageUri).into(iv_userimage);
                }
            });

        }
    }


    public String getFileExtension(Uri uri){
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


}

