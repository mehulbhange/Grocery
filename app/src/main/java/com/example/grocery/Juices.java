package com.example.grocery;

import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Juices extends AppCompatActivity implements View.OnClickListener{


    private StorageReference storageReference;
    private ImageView iv_mangojuice;
    private ImageView iv_orangejuice;
    private ImageView iv_mixfruitjuice;
    private double Qnt_mangojuice = 0.0;
    private double Qnt_orangejuice = 0.0;
    private double Qnt_mix = 0.0;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juices);

        storageReference = FirebaseStorage.getInstance().getReference();

        load_images();

        Button btn_mangojuice = (Button)findViewById(R.id.btn_mangojuice);
        btn_mangojuice.setOnClickListener(this);

        Button btn_orangejuice = (Button)findViewById(R.id.btn_orangejuice);
        btn_orangejuice.setOnClickListener(this);

        Button btn_mixfruitjuice = (Button)findViewById(R.id.btn_mixfruitjuice);
        btn_mixfruitjuice.setOnClickListener(this);

        Button btn_juices = findViewById(R.id.btn_juices);
        btn_juices.setOnClickListener(this);


    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_mangojuice:
                data = new Data();
                data.addItemsToFirebase("MangoJuice", 42, "kg", Qnt_mangojuice);

                break;

            case R.id.btn_orangejuice:
                // do your code
                data = new Data();
                data.addItemsToFirebase("OrangeJuice", 57, "kg", Qnt_orangejuice);


                break;

            case R.id.btn_mixfruitjuice:
                // do your code
                data = new Data();
                data.addItemsToFirebase("MixFruitJuice", 90, "kg", Qnt_mix);

                break;

            case R.id.btn_juices:
                startActivity(new Intent(Juices.this,MyCart.class));
                break;

            default:
                break;
        }
    }


    public void load_images(){

        iv_mangojuice = findViewById(R.id.iv_mangojuice);
        StorageReference apple = storageReference.child("/Grocery Items/juices/mangoJuice.jpg");
        apple.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(Juices.this).load(uri).into(iv_mangojuice);
            }

        });
        //DropDown1
        Spinner dropdown1 = findViewById(R.id.spinner1);
        String[] items1 = new String[]{"250 ml", "500 ml","750 ml","1 L", "1.5 L","2 L"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Qnt_mangojuice=0.5;
                        break;
                    case 2:
                        Qnt_mangojuice=0.750;
                        break;
                    case 3:
                        Qnt_mangojuice=1;
                        break;
                    case 4:
                        Qnt_mangojuice=1.5;
                        break;
                    case 5:
                        Qnt_mangojuice=2;
                        break;
                    default:
                        Qnt_mangojuice = 0.250;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_mangojuice = 0.25;
            }
        });


        iv_orangejuice = findViewById(R.id.iv_orangejuice);
        StorageReference banana = storageReference.child("/Grocery Items/juices/orangeJuice.jpg");
        banana.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with( Juices.this).load(uri).into(iv_orangejuice);
            }

        });
        //DropDown2
        Spinner dropdown2 = findViewById(R.id.spinner2);
        String[] items2 = new String[]{"250 ml", "500 ml","750 ml","1 L", "1.5 L","2 L"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Qnt_orangejuice=0.5;
                        break;
                    case 2:
                        Qnt_orangejuice=0.75;
                        break;
                    case 3:
                        Qnt_orangejuice=1;
                        break;
                    case 4:
                        Qnt_orangejuice=1.5;
                        break;
                    case 5:
                        Qnt_orangejuice=2;
                        break;
                    default:
                        Qnt_orangejuice = 0.25;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_orangejuice = 0.25;
            }
        });


        iv_mixfruitjuice = findViewById(R.id.iv_mixfruitjuice);
        StorageReference orange = storageReference.child("/Grocery Items/juices/MixFruiteJuice.jpg");
        orange.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(Juices.this).load(uri).into(iv_mixfruitjuice);
            }

        });
        //DropDown3
        Spinner dropdown3 = findViewById(R.id.spinner3);
        String[] items3 = new String[]{"250 ml", "500 ml","750 ml","1 L", "1.5 L","2 L"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);
        dropdown3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch (position){
                    case 1:
                        Qnt_mix=0.25;
                        break;
                    case 2:
                        Qnt_mix=0.5;
                        break;
                    case 3:
                        Qnt_mix=1;
                        break;
                    case 4:
                        Qnt_mix=1.5;
                        break;
                    case 5:
                        Qnt_mix=2;
                        break;
                    default:
                        Qnt_mix = 0.250;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_mix = 0.25;
            }
        });



    }
}