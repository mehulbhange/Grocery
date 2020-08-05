package com.example.grocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Vegetables extends AppCompatActivity implements View.OnClickListener {

    private StorageReference storageReference;

    private ImageView iv_onions;
    private ImageView iv_brinjals;
    private ImageView iv_combo;
    private double Qnt_onions = 0.0;
    private double Qnt_brinjals = 0.0;
    private double Qnt_combo = 0.0;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables);


        storageReference = FirebaseStorage.getInstance().getReference();

        load_images();


        Button btn_onions = (Button)findViewById(R.id.btn_onions);
        btn_onions.setOnClickListener(this);

        Button btn_brinjals = (Button)findViewById(R.id.btn_brinjals);
        btn_brinjals.setOnClickListener(this);

        Button btn_combo = (Button)findViewById(R.id.btn_combo);
        btn_combo.setOnClickListener(this);

        Button btn_vegetables = findViewById(R.id.btn_vegetables);
        btn_vegetables.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_onions:
                data = new Data();
                data.addItemsToFirebase("Onions", 35, "kg", Qnt_onions);

                break;

            case R.id.btn_brinjals:
                // do your code
                data = new Data();
                data.addItemsToFirebase("Brinjals", 26, "kg", Qnt_brinjals);


                break;

            case R.id.btn_combo:
                // do your code
                data = new Data();
                data.addItemsToFirebase("combo", 90, "kg", Qnt_combo);

                break;
                
            case R.id.btn_vegetables:
                startActivity(new Intent(Vegetables.this,MyCart.class));
                break;

            default:
                break;
        }
    }


    public void load_images(){

        iv_onions = findViewById(R.id.iv_onions);
        StorageReference apple = storageReference.child("/Grocery Items/freshvegitables/oni.jpg");
        apple.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(Vegetables.this).load(uri).into(iv_onions);
            }

        });
        //DropDown1
        Spinner dropdown1 = findViewById(R.id.spinner1);
        String[] items1 = new String[]{"1 kg", "1.5 kg","2 kg","2.5 kg", "3 kg","4 kg","5 kg","6 kg", "7 kg"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Qnt_onions=1.5;
                        break;
                    case 2:
                        Qnt_onions=2;
                        break;
                    case 3:
                        Qnt_onions=2.5;
                        break;
                    case 4:
                        Qnt_onions=3;
                        break;
                    case 5:
                        Qnt_onions=4;
                        break;
                    case 6:
                        Qnt_onions=5;
                        break;
                    case 7:
                        Qnt_onions=6;
                        break;
                    case 8:
                        Qnt_onions=7;
                        break;
                    default:
                        Qnt_onions = 1.0;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_onions = 1.0;
            }
        });


        iv_brinjals = findViewById(R.id.iv_brinjals);
        StorageReference banana = storageReference.child("/Grocery Items/freshvegitables/brinjal.jpg");
        banana.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with( Vegetables.this).load(uri).into(iv_brinjals);
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
                        Qnt_brinjals=0.25;
                        break;
                    case 2:
                        Qnt_brinjals=0.5;
                        break;
                    case 3:
                        Qnt_brinjals=1.5;
                        break;
                    case 4:
                        Qnt_brinjals=2;
                        break;
                    case 5:
                        Qnt_brinjals=2.5;
                        break;
                    case 6:
                        Qnt_brinjals=3;
                        break;
                    case 7:
                        Qnt_brinjals=4;
                        break;
                    case 8:
                        Qnt_brinjals=5;
                        break;
                    default:
                        Qnt_brinjals = 1.0;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_brinjals = 1.0;
            }
        });


        iv_combo = findViewById(R.id.iv_combo);
        StorageReference orange = storageReference.child("/Grocery Items/freshvegitables/TOP.jpg");
        orange.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(Vegetables.this).load(uri).into(iv_combo);
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
                        Qnt_combo=0.25;
                        break;
                    case 2:
                        Qnt_combo=0.5;
                        break;
                    case 3:
                        Qnt_combo=1.5;
                        break;
                    case 4:
                        Qnt_combo=2;
                        break;
                    case 5:
                        Qnt_combo=2.5;
                        break;
                    case 6:
                        Qnt_combo=3;
                        break;
                    case 7:
                        Qnt_combo=4;
                        break;
                    case 8:
                        Qnt_combo=5;
                        break;
                    default:
                        Qnt_combo = 1.0;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_combo = 1.0;
            }
        });



    }


}