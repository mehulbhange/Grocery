package com.example.grocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i_login = new Intent(MainActivity.this,Login.class);
                startActivity(i_login);
                finish();
            }
        },1000);



        /*
      //  Redirect to the home page without login

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i_login = new Intent(MainActivity.this,Home.class);
                startActivity(i_login);
                finish();
            }
        },1000);
        */


    }
}
