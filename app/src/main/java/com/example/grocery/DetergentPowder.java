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

public class DetergentPowder extends AppCompatActivity implements View.OnClickListener{

    private StorageReference storageReference;

    private ImageView iv_surfexcel;
    private ImageView iv_arial;
    private ImageView iv_tide;
    private double Qnt_surfexcel = 0.0;
    private double Qnt_arial = 0.0;
    private double Qnt_tide = 0.0;
    private Data data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detergent_powder);

        storageReference = FirebaseStorage.getInstance().getReference();

        load_images();


        Button btn_surfexcel = (Button)findViewById(R.id.btn_surfexcel);
        btn_surfexcel.setOnClickListener(this);

        Button btn_arial = (Button)findViewById(R.id.btn_arial);
        btn_arial.setOnClickListener(this);

        Button btn_tide = (Button)findViewById(R.id.btn_tide);
        btn_tide.setOnClickListener(this);

        Button btn_detergent  = findViewById(R.id.btn_detergent);
        btn_detergent.setOnClickListener(this);

    }

    public void load_images(){

        iv_surfexcel = findViewById(R.id.iv_surfexcel);
        StorageReference apple = storageReference.child("/Grocery Items/detergentpowder/surfExel.jpg");
        apple.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(DetergentPowder.this).load(uri).into(iv_surfexcel);
            }

        });
        //DropDown1
        Spinner dropdown1 = findViewById(R.id.spinner1);
        String[] items1 = new String[]{"1 kg", "250 gm","500 gm","750 gm", "1.5 kg","2 kg","3 kg"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Qnt_surfexcel=0.25;
                        break;
                    case 2:
                        Qnt_surfexcel=0.50;
                        break;
                    case 3:
                        Qnt_surfexcel=0.75;
                        break;
                    case 4:
                        Qnt_surfexcel=1.5;
                        break;
                    case 5:
                        Qnt_surfexcel=2;
                        break;
                    case 6:
                        Qnt_surfexcel=3;
                        break;
                    default:
                        Qnt_surfexcel = 1.0;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_surfexcel = 1.0;
            }
        });


        iv_arial = findViewById(R.id.iv_arial);
        StorageReference banana = storageReference.child("/Grocery Items/detergentpowder/arial.jpg");
        banana.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with( DetergentPowder.this).load(uri).into(iv_arial);
            }

        });
        //DropDown2
        Spinner dropdown2 = findViewById(R.id.spinner2);
        String[] items2 = new String[]{"1 kg", "250 gm","500 gm","750 gm", "1.5 kg","2 kg","3 kg"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch (position){
                    case 1:
                        Qnt_arial=0.25;
                        break;
                    case 2:
                        Qnt_arial=0.50;
                        break;
                    case 3:
                        Qnt_arial=0.75;
                        break;
                    case 4:
                        Qnt_arial=1.5;
                        break;
                    case 5:
                        Qnt_arial=2;
                        break;
                    case 6:
                        Qnt_arial=3;
                        break;
                    default:
                        Qnt_arial = 1.0;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_arial = 1.0;
            }
        });


        iv_tide = findViewById(R.id.iv_tide);
        StorageReference orange = storageReference.child("/Grocery Items/detergentpowder/tide.jpg");
        orange.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(DetergentPowder.this).load(uri).into(iv_tide);
            }

        });
        //DropDown3
        Spinner dropdown3 = findViewById(R.id.spinner3);
        String[] items3 = new String[]{"1 kg", "250 gm","500 gm","750 gm", "1.5 kg","2 kg","3 kg"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);
        dropdown3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch (position){
                    case 1:
                        Qnt_tide=0.25;
                        break;
                    case 2:
                        Qnt_tide=0.50;
                        break;
                    case 3:
                        Qnt_tide=0.75;
                        break;
                    case 4:
                        Qnt_tide=1.5;
                        break;
                    case 5:
                        Qnt_tide=2;
                        break;
                    case 6:
                        Qnt_tide=3;
                        break;
                    default:
                        Qnt_tide = 1.0;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_tide = 1.0;
            }
        });

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_surfexcel:
                data = new Data();
                data.addItemsToFirebase("SurfExcel", 100, "kg", Qnt_surfexcel);

                break;

            case R.id.btn_arial:
                // do your code
                data = new Data();
                data.addItemsToFirebase("Arial", 100, "kg", Qnt_arial);


                break;

            case R.id.btn_tide:
                // do your code
                data = new Data();
                data.addItemsToFirebase("Tide", 100, "kg", Qnt_tide);

                break;

            case R.id.btn_detergent:
                startActivity(new Intent(DetergentPowder.this,MyCart.class));
                break;

            default:
                break;
        }
    }

}