package com.example.grocery;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

public class Data {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceAmount;
    private DatabaseReference itemReference;
    private CartData cartData;
    private double firebase_total_amount;
    public double total_stored_amount = 0;
    private SharedPreferences sharedPreferences;

    public Data() {
    }



    void addItemsToFirebase(String name, double rate, String unit, double qnt){

        //databaseReferenceAmount.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("TotalAmount").setValue(0);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference("cartData");
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(name);
        databaseReference = mFirebaseDatabase.getReference("cartData/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/"+name);
        databaseReference.child("name").setValue(name);
        databaseReference.child("rate").setValue(rate);
        databaseReference.child("unit").setValue(unit);
        databaseReference.child("qnt").setValue(qnt);
    }

    void addItemsToFirebase(String name, double rate, String unit, double qnt,int number_of_items){

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference("cartData");

        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(name);
        databaseReference = mFirebaseDatabase.getReference("cartData/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/"+name);
        databaseReference.child("name").setValue(name);
        databaseReference.child("rate").setValue(rate);
        databaseReference.child("unit").setValue(unit);
        databaseReference.child("qnt").setValue(qnt);
        databaseReference.child("number_of_items").setValue(number_of_items);

    }

    void removeItemFromFirebase(String name){
        
    }

}
