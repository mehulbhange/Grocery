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

public class OralCare extends AppCompatActivity implements View.OnClickListener {

    private StorageReference storageReference;

    private ImageView iv_colgatemaxfresh;
    private ImageView iv_colgatestrongteeth;
    private ImageView iv_sensodyne;
    private double Qnt_maxfresh = 0.0;
    private double Qnt_strongteeth = 0.0;
    private double Qnt_sensodyne = 0.0;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oral_care);

        storageReference = FirebaseStorage.getInstance().getReference();

        load_images();


        Button btn_maxfresh = (Button)findViewById(R.id.btn_colgatemaxfresh);
        btn_maxfresh.setOnClickListener(this);

        Button btn_strongteeth = (Button)findViewById(R.id.btn_colgatestrongteeth);
        btn_strongteeth.setOnClickListener(this);

        Button btn_sensodyne = (Button)findViewById(R.id.btn_sensodyne);
        btn_sensodyne.setOnClickListener(this);

        Button btn_oralcare = findViewById(R.id.btn_oralcare);
        btn_oralcare.setOnClickListener(this);

    }

    public void load_images(){

        iv_colgatemaxfresh = findViewById(R.id.iv_colgatemaxfresh);
        StorageReference apple = storageReference.child("/Grocery Items/oralcare/ColgateMaxfresh.jpg");
        apple.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(OralCare.this).load(uri).into(iv_colgatemaxfresh);
            }

        });
        //DropDown1
        Spinner dropdown1 = findViewById(R.id.spinner1);
        String[] items1 = new String[]{"100 gm", "200 gm"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Qnt_maxfresh=200;
                        break;
                    default:
                        Qnt_maxfresh = 100;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_maxfresh = 100;
            }
        });


        iv_colgatestrongteeth = findViewById(R.id.iv_colgatestrongteeth);
        StorageReference banana = storageReference.child("/Grocery Items/oralcare/ColgateStrongTeeth.jpg");
        banana.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with( OralCare.this).load(uri).into(iv_colgatestrongteeth);
            }

        });
        //DropDown2
        Spinner dropdown2 = findViewById(R.id.spinner2);
        String[] items2 = new String[]{"100 gm","200 gm"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch (position){
                    case 1:
                        Qnt_strongteeth= 200;
                        break;
                    default:
                        Qnt_strongteeth = 100;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_strongteeth = 100;
            }
        });


        iv_sensodyne = findViewById(R.id.iv_sensodyne);
        StorageReference orange = storageReference.child("/Grocery Items/oralcare/sensodine.jpg");
        orange.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(OralCare.this).load(uri).into(iv_sensodyne);
            }

        });
        //DropDown3
        Spinner dropdown3 = findViewById(R.id.spinner3);
        String[] items3 = new String[]{"70 gm"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown3.setAdapter(adapter3);
        dropdown3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                switch (position){
                    default:
                        Qnt_sensodyne = 70;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Qnt_sensodyne = 1.0;
            }
        });




    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_colgatemaxfresh:
                data = new Data();
                data.addItemsToFirebase("ColgateMaxFresh", 40, "gm", Qnt_maxfresh);

                break;

            case R.id.btn_colgatestrongteeth:
                // do your code
                data = new Data();
                data.addItemsToFirebase("ColgateStrongTeeth", 40, "gm", Qnt_strongteeth);


                break;

            case R.id.btn_sensodyne:
                // do your code
                data = new Data();
                data.addItemsToFirebase("SensodyneRepairAndProtect", 175, "gm", Qnt_sensodyne);

                break;

            case R.id.btn_oralcare:
                startActivity(new Intent(OralCare.this,MyCart.class));
                break;

            default:
                break;
        }
    }


}