package com.example.grocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Home<savedInstanceState> extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar mToolbar;
    private NavigationView navigationView;

    private TextView tv_name;
    private TextView tv_usermail;
    private ImageView iv_userimage;
    private ImageView iv_profileimage;


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private StorageReference storageReference;

    private StorageTask mUploadTask;
    private Uri mImageUri;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



            //To upload the user Profile picture to the Database

            storageReference = FirebaseStorage.getInstance().getReference("UserImages");
            databaseReference = FirebaseDatabase.getInstance().getReference("userData");

            //------------------------------------------------------------------

            // TO get the username of the user from the database and set it to profile

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            View headerView = navigationView.getHeaderView(0);
            tv_name = (TextView) headerView.findViewById(R.id.tv_username);
            tv_usermail = (TextView) headerView.findViewById(R.id.tv_usermail);
            iv_userimage = (ImageView) headerView.findViewById(R.id.iv_userimage);

            storageReference = FirebaseStorage.getInstance().getReference();

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = mFirebaseDatabase.getReference("userData/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Name");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //Getting the string value of that node
                    String value = dataSnapshot.getValue(String.class);
                    tv_name.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Home.this, "Operation cancelled:", Toast.LENGTH_LONG).show();

                }
            });

            databaseReference = mFirebaseDatabase.getReference("userData/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/Email");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //Getting the string value of that node
                    String value = dataSnapshot.getValue(String.class);
                    tv_usermail.setText(value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Home.this, "Operation cancelled: ", Toast.LENGTH_LONG).show();

                }
            });

            //-------------------------------------------------------------


            mToolbar = findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);

            mDrawerLayout = findViewById(R.id.drawer_layout);
            actionBarDrawerToggle = new ActionBarDrawerToggle(
                    this,
                    mDrawerLayout,
                    mToolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            );

            mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();

            navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

            //OnClickCart Button

            ImageView iv_cart = (ImageView) findViewById(R.id.open_cart);
            iv_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Home.this, MyCart.class));
                }
            });

            //to check if home page is empty load the home page
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).addToBackStack(null).commit();
                navigationView.setCheckedItem(R.id.home_fragment);

            }

            StorageReference mRef = storageReference.child("UserImages/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + ".jpg");
            mRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.with(Home.this).load(uri).into(iv_userimage);
                }

            });



    }

    @Override
    public void onBackPressed() {

        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(currentFragment instanceof HomeFragment){
            finish();
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.home:
                    Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    if (currentFragment instanceof HomeFragment) {
                        break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new HomeFragment()).addToBackStack(null).commit();
                    break;

                case R.id.profile:
                    Fragment currentFragmentprofile = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    if (currentFragmentprofile instanceof ProfileFragment) {
                        break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new ProfileFragment()).addToBackStack(null).commit();


                    break;

                case R.id.notification:
                    Fragment currentFragmentNotification = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    if (currentFragmentNotification instanceof NotificationFragment) {
                        break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new NotificationFragment()).addToBackStack(null).commit();
                    break;

                case R.id.myCart:
                    Fragment currentFragmentMyCart = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    if (currentFragmentMyCart instanceof MyCartFragment) {
                        break;
                    } else {
                        startActivity(new Intent(Home.this, MyCart.class));
                    }

                    break;

                case R.id.myOrder:
                    Fragment currentFragmentMyOrder = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    if (currentFragmentMyOrder instanceof OrdersFragment) {
                        break;
                    }
                    break;

                case R.id.logout:
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.signOut();
                    Intent i = new Intent(Home.this, Login.class);
                    startActivity(i);
                    finish();
                    break;
            }
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
