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

public class BiscuitsAndCookies extends AppCompatActivity implements View.OnClickListener {

    private StorageReference storageReference;

    private ImageView iv_bourbon;
    private ImageView iv_goodday;
    private ImageView iv_oreo;
    private double Qnt_bourbon = 0.0;
    private double Qnt_goodday = 0.0;
    private double Qnt_oreo = 0.0;
    private int number_of_items = 0;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biscuits_and_cookies);

        storageReference = FirebaseStorage.getInstance().getReference();

        load_images();


        Button btn_bourbon = (Button)findViewById(R.id.btn_bourbon);
        btn_bourbon.setOnClickListener((View.OnClickListener) this);

        Button btn_goodday = (Button)findViewById(R.id.btn_goodday);
        btn_goodday.setOnClickListener(this);

        Button btn_oreo = (Button)findViewById(R.id.btn_oreo);
        btn_oreo.setOnClickListener(this);

        Button btn_biscuits = findViewById(R.id.btn_biscuitsandcookies);
        btn_biscuits.setOnClickListener(this);

    }

    public void load_images(){

        iv_bourbon = findViewById(R.id.iv_bourbon);
        StorageReference bourbon = storageReference.child("/Grocery Items/biscuits&cookies/BourBan.jpg");
        bourbon.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(BiscuitsAndCookies.this).load(uri).into(iv_bourbon);
            }

        });
        //DropDown1
        Spinner dropdown1 = findViewById(R.id.spinner1);
        String[] items1 = new String[]{"40 gm", "80 gm","120 gm","160 gm"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Qnt_bourbon=80;
                        number_of_items = 2;
                        break;
                    case 2:
                        Qnt_bourbon=120;
                        number_of_items = 3;
                        break;
                    case 3:
                        Qnt_bourbon=160;
                        number_of_items = 4;
                        break;
                    default:
                        Qnt_bourbon = 40;
                        number_of_items = 1;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_bourbon = 40;
            }
        });

        iv_goodday = findViewById(R.id.iv_goodday);
        StorageReference goodday = storageReference.child("/Grocery Items/biscuits&cookies/GoodDat.jpg");
        goodday.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(BiscuitsAndCookies.this).load(uri).into(iv_goodday);
            }

        });
        //DropDown1
        Spinner dropdown2 = findViewById(R.id.spinner2);
        String[] items2 = new String[]{"45 gm", "90 gm","140 gm","190 gm"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Qnt_goodday=90;
                        number_of_items = 2;
                        break;
                    case 2:
                        Qnt_goodday=140;
                        number_of_items = 3;
                        break;
                    case 3:
                        Qnt_goodday=190;
                        number_of_items = 4;
                        break;
                    default:
                        Qnt_goodday = 45;
                        number_of_items = 1;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_goodday = 45;
            }
        });

        iv_oreo = findViewById(R.id.iv_oreo);
        StorageReference oreo = storageReference.child("/Grocery Items/biscuits&cookies/Oreo.jpg");
        oreo.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(BiscuitsAndCookies.this).load(uri).into(iv_oreo);
            }

        });
        //DropDown1
        Spinner dropdown3 = findViewById(R.id.spinner3);
        String[] items3 = new String[]{"35 gm", "70 gm","110 gm","150 gm"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);
        dropdown3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Qnt_oreo=70;
                        number_of_items = 2;
                        break;
                    case 2:
                        Qnt_oreo=110;
                        number_of_items = 3;
                        break;
                    case 3:
                        Qnt_oreo=150;
                        number_of_items = 4;
                        break;
                    default:
                        Qnt_oreo = 35;
                        number_of_items = 1;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_oreo = 35;
            }
        });


    }

    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_bourbon:
                data = new Data();
                data.addItemsToFirebase("Bourbon", 5, "gm", Qnt_bourbon,number_of_items);

                break;

            case R.id.btn_goodday:
                // do your code
                data = new Data();
                data.addItemsToFirebase("Goodday", 5, "gm", Qnt_goodday,number_of_items);


                break;

            case R.id.btn_oreo:
                // do your code
                data = new Data();
                data.addItemsToFirebase("Oreo", 5, "gm", Qnt_oreo,number_of_items);

                break;

            case R.id.btn_biscuitsandcookies:
                startActivity(new Intent(BiscuitsAndCookies.this,MyCart.class));
                break;

            default:
                break;
        }
    }



}