package com.example.grocery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeFragment extends Fragment {

    ImageButton btnFruits;
    ImageButton btnVegitables;
    ImageButton btnJuices;
    ImageButton btnBiscuits;
    ImageButton btnColddrinks;
    ImageButton btnOralcare;
    ImageButton btnDetergentpowder;
    ImageButton btnBathingessemtials;



    private StorageReference storageReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        btnFruits = (ImageButton) view.findViewById(R.id.btnfruits);
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference fruits = storageReference.child("/Grocery Items/freshfruits/"+"freshFruits.jpg");
        fruits
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(btnFruits);
            }

        });

        btnVegitables = (ImageButton) view.findViewById(R.id.btnVegitables);
        StorageReference vegitables = storageReference.child("/Grocery Items/freshvegitables/"+"freshVagitables.jpg");
        vegitables.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(btnVegitables);
            }

        });

        btnJuices = (ImageButton) view.findViewById(R.id.btnJuices);
        StorageReference juices = storageReference.child("/Grocery Items/juices/"+"juices.jpg");
        juices.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(btnJuices);
            }

        });

        btnBiscuits = (ImageButton) view.findViewById(R.id.btnBiscuits);
        StorageReference biscuits = storageReference.child("/Grocery Items/biscuits&cookies/"+"Biscuits&Cookies.jpg");
        biscuits.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(btnBiscuits);
            }

        });

        btnColddrinks = (ImageButton) view.findViewById(R.id.btnColddrinks);
        StorageReference coldDrinks = storageReference.child("/Grocery Items/colddrinks/"+"ColdDrinks.jpg");
        coldDrinks.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(btnColddrinks);
            }

        });

        btnOralcare = (ImageButton) view.findViewById(R.id.btnOralcare);
        StorageReference oralCare = storageReference.child("/Grocery Items/oralcare/"+"oralCare.jpg");
        oralCare.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(btnOralcare);
            }

        });

        btnDetergentpowder = (ImageButton) view.findViewById(R.id.btnDetergentpowder);
        StorageReference detergentPowder = storageReference.child("/Grocery Items/detergentpowder/"+"detergentPowder.jpg");
        detergentPowder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(btnDetergentpowder);
            }

        });

        btnBathingessemtials = (ImageButton) view.findViewById(R.id.btnBathingessentials);
        StorageReference bathingEssentials = storageReference.child("/Grocery Items/bathingessentials/"+"BathingEssential.jpg");
        bathingEssentials.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getActivity()).load(uri).into(btnBathingessemtials);
            }

        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);

        btnFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Fruits.class));
            }
        });
        btnVegitables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Vegetables.class));
            }
        });

        btnJuices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Juices.class));
            }
        });

        btnBiscuits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),BiscuitsAndCookies.class));
            }
        });

        btnColddrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ColdDrinks.class));

            }
        });

        btnOralcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),OralCare.class));

            }
        });

        btnDetergentpowder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),DetergentPowder.class));
            }
        });

        btnBathingessemtials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),BathingEssentials.class));
            }
        });

    }



}
