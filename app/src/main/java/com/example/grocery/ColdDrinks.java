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

public class ColdDrinks extends AppCompatActivity implements View.OnClickListener{

    private StorageReference storageReference;

    private ImageView iv_cocacola;
    private ImageView iv_mirinda;
    private ImageView iv_pepsi;
    private double Qnt_cocacola = 0.0;
    private double Qnt_mirinda = 0.0;
    private double Qnt_pepsi = 0.0;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cold_drinks);

        storageReference = FirebaseStorage.getInstance().getReference();

        load_images();


        Button btn_cola = (Button)findViewById(R.id.btn_cocacola);
        btn_cola.setOnClickListener(this);

        Button bnt_mirinda = (Button)findViewById(R.id.btn_mirinda);
        bnt_mirinda.setOnClickListener(this);

        Button btn_pepsi = (Button)findViewById(R.id.btn_pepsi);
        btn_pepsi.setOnClickListener(this);

        Button btn_colddrinks = findViewById(R.id.btn_colddrinks);
        btn_colddrinks.setOnClickListener(this);

    }

    public void load_images(){

        iv_cocacola = findViewById(R.id.iv_cocacola);
        StorageReference apple = storageReference.child("/Grocery Items/colddrinks/coca-cola.jpg");
        apple.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(ColdDrinks.this).load(uri).into(iv_cocacola);
            }

        });
        //DropDown1
        Spinner dropdown1 = findViewById(R.id.spinner1);
        String[] items1 = new String[]{"200 ml", "400 ml","750 ml","1 L", "1.5 L","2 L"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Qnt_cocacola=0.4;
                        break;
                    case 2:
                        Qnt_cocacola=0.75;
                        break;
                    case 3:
                        Qnt_cocacola=1.0;
                        break;
                    case 4:
                        Qnt_cocacola=1.5;
                        break;
                    case 5:
                        Qnt_cocacola=2;
                        break;
                    default:
                        Qnt_cocacola = 0.2;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_cocacola = 0.2;
            }
        });


        iv_mirinda = findViewById(R.id.iv_mirinda);
        StorageReference banana = storageReference.child("/Grocery Items/colddrinks/mirinda.jpg");
        banana.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with( ColdDrinks.this).load(uri).into(iv_mirinda);
            }

        });
        //DropDown2
        Spinner dropdown2 = findViewById(R.id.spinner2);
        String[] items2 = new String[]{"200 ml", "400 ml","750 ml","1 L", "1.5 L","2 L"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch (position){
                    case 1:
                        Qnt_mirinda=0.4;
                        break;
                    case 2:
                        Qnt_mirinda=0.75;
                        break;
                    case 3:
                        Qnt_mirinda=1.0;
                        break;
                    case 4:
                        Qnt_mirinda=1.5;
                        break;
                    case 5:
                        Qnt_mirinda=2;
                        break;
                    default:
                        Qnt_mirinda = 0.2;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_mirinda = 0.2;
            }
        });


        iv_pepsi = findViewById(R.id.iv_pepsi);
        StorageReference orange = storageReference.child("/Grocery Items/colddrinks/pepsi.jpg");
        orange.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(ColdDrinks.this).load(uri).into(iv_pepsi);
            }

        });
        //DropDown3
        Spinner dropdown3 = findViewById(R.id.spinner3);
        String[] items3 = new String[]{"200 ml", "400 ml","750 ml","1 L", "1.5 L","2 L"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);
        dropdown3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch (position){
                    case 1:
                        Qnt_pepsi=0.4;
                        break;
                    case 2:
                        Qnt_pepsi=0.75;
                        break;
                    case 3:
                        Qnt_pepsi=1.0;
                        break;
                    case 4:
                        Qnt_pepsi=1.5;
                        break;
                    case 5:
                        Qnt_pepsi=2;
                        break;
                    default:
                        Qnt_pepsi = 0.2;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_pepsi = 0.2;
            }
        });




    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_cocacola:
                data = new Data();
                data.addItemsToFirebase("CocaCola", 210, "Litre", Qnt_cocacola);

                break;

            case R.id.btn_mirinda:
                // do your code
                data = new Data();
                data.addItemsToFirebase("Mirinda", 210, "Litre", Qnt_mirinda);


                break;

            case R.id.btn_pepsi:
                // do your code
                data = new Data();
                data.addItemsToFirebase("Pepsi", 210, "Litre", Qnt_pepsi);

                break;

            case R.id.btn_colddrinks:
                startActivity(new Intent(ColdDrinks.this,MyCart.class));
                break;

            default:
                break;
        }
    }

}