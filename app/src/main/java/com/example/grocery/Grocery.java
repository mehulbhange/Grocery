package com.example.grocery;

import android.app.Application;

import com.firebase.client.Firebase;

public class Grocery extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
