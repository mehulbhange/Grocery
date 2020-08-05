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

public class BathingEssentials extends AppCompatActivity implements View.OnClickListener{

    private StorageReference storageReference;

    private ImageView iv_dettol;
    private ImageView iv_dove;
    private ImageView iv_cinthol;
    private double Qnt_dettol = 0.0;
    private double Qnt_dove = 0.0;
    private double Qnt_cinthol = 0.0;
    private Data data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bathing_essentials);

        storageReference = FirebaseStorage.getInstance().getReference();

        load_images();


        Button btn_dettol = (Button)findViewById(R.id.btn_dettol);
        btn_dettol.setOnClickListener(this);

        Button btn_dove = (Button)findViewById(R.id.btn_dove);
        btn_dove.setOnClickListener(this);

        Button btn_cinthol = (Button)findViewById(R.id.btn_cinthol);
        btn_cinthol.setOnClickListener(this);

        Button btn_bathing = (Button)findViewById(R.id.btn_bathingessentials);
        btn_bathing.setOnClickListener(this);

    }

    public void load_images(){


        iv_dettol = findViewById(R.id.iv_dettol);
        StorageReference apple = storageReference.child("/Grocery Items/bathingessentials/Dettol.jpg");
        apple.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(BathingEssentials.this).load(uri).into(iv_dettol);
            }

        });
        //DropDown1
        Spinner dropdown1 = findViewById(R.id.spinner1);
        String[] items1 = new String[]{"pack of 4", "pack of 6","pack of 8","pack of 10", "pack of 12","pack of 14","pack of 16"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Qnt_dettol=6;
                        break;
                    case 2:
                        Qnt_dettol=8;
                        break;
                    case 3:
                        Qnt_dettol=10;
                        break;
                    case 4:
                        Qnt_dettol=12;
                        break;
                    case 5:
                        Qnt_dettol=14;
                        break;
                    case 6:
                        Qnt_dettol=16;
                        break;
                    default:
                        Qnt_dettol = 4;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_dettol = 4;
            }
        });

        iv_dove = findViewById(R.id.iv_dove);
        StorageReference banana = storageReference.child("/Grocery Items/bathingessentials/Dove.jpg");
        banana.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with( BathingEssentials.this).load(uri).into(iv_dove);
            }

        });
        //DropDown2
        Spinner dropdown2 = findViewById(R.id.spinner2);
        String[] items2 = new String[]{"pack of 4", "pack of 6","pack of 8","pack of 10", "pack of 12","pack of 14","pack of 16"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch (position){
                    case 1:
                        Qnt_dove=6;
                        break;
                    case 2:
                        Qnt_dove=8;
                        break;
                    case 3:
                        Qnt_dove=10;
                        break;
                    case 4:
                        Qnt_dove=12;
                        break;
                    case 5:
                        Qnt_dove=14;
                        break;
                    case 6:
                        Qnt_dove=16;
                        break;
                    default:
                        Qnt_dove = 4;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_dove = 4;
            }
        });

        iv_cinthol = findViewById(R.id.iv_cinthol);
        StorageReference orange = storageReference.child("/Grocery Items/bathingessentials/cinthol.jpg");
        orange.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(BathingEssentials.this).load(uri).into(iv_cinthol);
            }

        });
        //DropDown3
        Spinner dropdown3 = findViewById(R.id.spinner3);
        String[] items3 = new String[]{"pack of 4", "pack of 6","pack of 8","pack of 10", "pack of 12","pack of 14","pack of 16"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);
        dropdown3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch (position){
                    case 1:
                        Qnt_cinthol=6;
                        break;
                    case 2:
                        Qnt_cinthol=8;
                        break;
                    case 3:
                        Qnt_cinthol=10;
                        break;
                    case 4:
                        Qnt_cinthol=12;
                        break;
                    case 5:
                        Qnt_cinthol=14;
                        break;
                    case 6:
                        Qnt_cinthol=16;
                        break;
                    default:
                        Qnt_cinthol = 4;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_cinthol = 4;
            }
        });




    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_dettol:
                data = new Data();
                data.addItemsToFirebase("Dettol", 25, "items", Qnt_dettol);

                break;

            case R.id.btn_dove:
                // do your code
                data = new Data();
                data.addItemsToFirebase("Dove", 32.5, "items", Qnt_dove);


                break;

            case R.id.btn_cinthol:
                // do your code
                data = new Data();
                data.addItemsToFirebase("Cinthol", 21.25, "items", Qnt_cinthol);

                break;

            case R.id.btn_bathingessentials:
                startActivity(new Intent(BathingEssentials.this,MyCart.class));
                break;

            default:
                break;
        }
    }


}