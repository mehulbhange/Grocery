package com.example.grocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Fruits extends AppCompatActivity implements View.OnClickListener {

    private StorageReference storageReference;

    ImageView iv_apple;
    ImageView iv_grapes;
    ImageView iv_oranges;
    double Qnt_apples= 0.0;
    double Qnt_grapes= 0.0;
    double Qnt_oranges= 0.0;

    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);
        storageReference = FirebaseStorage.getInstance().getReference();

        load_images();

        Button btn_apples = (Button)findViewById(R.id.btn_apples);
        btn_apples.setOnClickListener(this);

        Button btn_grapes = (Button)findViewById(R.id.btn_grapes);
        btn_grapes.setOnClickListener(this);

        Button btn_oranges = (Button)findViewById(R.id.btn_oranges);
        btn_oranges.setOnClickListener(this);

        Button btn_fruits = (Button)findViewById(R.id.btn_fruits);
        btn_fruits.setOnClickListener(this);


    }

    public void load_images(){

        iv_apple = findViewById(R.id.iv_apple);
        StorageReference apple = storageReference.child("/Grocery Items/freshfruits/apples.jpg");
        apple.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(Fruits.this).load(uri).into(iv_apple);
            }

        });
        //DropDown1
        Spinner dropdown1 = findViewById(R.id.spinner1);
        String[] items1 = new String[]{"1 kg", "0.25 kg","0.5 kg","1.5 kg", "2 kg","2.5 kg","3 kg","4 kg", "5 kg"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch (position){
                    case 1:
                        Qnt_apples=0.25;
                        break;
                    case 2:
                        Qnt_apples=0.5;
                        break;
                    case 3:
                        Qnt_apples=1.5;
                        break;
                    case 4:
                        Qnt_apples=2;
                        break;
                    case 5:
                        Qnt_apples=2.5;
                        break;
                    case 6:
                        Qnt_apples=3;
                        break;
                    case 7:
                        Qnt_apples=4;
                        break;
                    case 8:
                        Qnt_apples=5;
                        break;
                    default:
                        Qnt_apples = 1.0;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_apples = 1.0;
            }
        });


        iv_grapes = findViewById(R.id.iv_grapes);
        StorageReference banana = storageReference.child("/Grocery Items/freshfruits/grapes.jpg");
        banana.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(Fruits.this).load(uri).into(iv_grapes);
            }

        });
        //DropDown2
        Spinner dropdown2 = findViewById(R.id.spinner2);
        String[] items2 = new String[]{"1 kg","0.25 kg","0.5 kg", "1.5 kg", "2 kg","2.5 kg","3 kg","4 kg", "5 kg"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch (position){
                    case 1:
                        Qnt_grapes=0.25;
                        break;
                    case 2:
                        Qnt_grapes=0.5;
                        break;
                    case 3:
                        Qnt_grapes=1.5;
                        break;
                    case 4:
                        Qnt_grapes=2;
                        break;
                    case 5:
                        Qnt_grapes=2.5;
                        break;
                    case 6:
                        Qnt_grapes=3;
                        break;
                    case 7:
                        Qnt_grapes=4;
                        break;
                    case 8:
                        Qnt_grapes=5;
                        break;
                    default:
                        Qnt_grapes = 1.0;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_apples = 1.0;
            }
        });


        iv_oranges = findViewById(R.id.iv_oranges);
        StorageReference orange = storageReference.child("/Grocery Items/freshfruits/orages.jpg");
        orange.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(Fruits.this).load(uri).into(iv_oranges);
            }

        });
        //DropDown3
        Spinner dropdown3 = findViewById(R.id.spinner3);
        String[] items3 = new String[]{"1 kg", "0.25 kg","0.5 kg","1.5 kg", "2 kg","2.5 kg","3 kg","4 kg", "5 kg"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);
        dropdown3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch (position){
                    case 1:
                        Qnt_oranges=0.25;
                        break;
                    case 2:
                        Qnt_oranges=0.5;
                        break;
                    case 3:
                        Qnt_oranges=1.5;
                        break;
                    case 4:
                        Qnt_oranges=2;
                        break;
                    case 5:
                        Qnt_oranges=2.5;
                        break;
                    case 6:
                        Qnt_oranges=3;
                        break;
                    case 7:
                        Qnt_oranges=4;
                        break;
                    case 8:
                        Qnt_oranges=5;
                        break;
                    default:
                        Qnt_oranges = 1.0;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_apples = 1.0;
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_apples:
                data = new Data();
                data.addItemsToFirebase("Apples",160,"kg",Qnt_apples);

                break;

            case R.id.btn_grapes:
                // do your code
                data = new Data();
                data.addItemsToFirebase("Grapes",60,"kg",Qnt_grapes);


                break;

            case R.id.btn_oranges:
                // do your code
                data = new Data();
                data.addItemsToFirebase("Oranges",100,"kg",Qnt_oranges);

                break;

            case R.id.btn_fruits:
                startActivity(new Intent(Fruits.this,MyCart.class));

            default:
                break;
        }

    }




}
