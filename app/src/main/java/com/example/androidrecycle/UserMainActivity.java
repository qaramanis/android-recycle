package com.example.androidrecycle;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.androidrecycle.databinding.*;
import com.example.androidrecycle.ui.account.AccountFragment;
import com.example.androidrecycle.ui.add.AddFragment;
import com.example.androidrecycle.ui.home.HomeFragment;
import com.example.androidrecycle.ui.map.MapFragment;
import com.example.androidrecycle.ui.rewards.RewardsFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserMainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener, OnMapReadyCallback {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav_bar);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
    }

    HomeFragment homeFragment = new HomeFragment();
    //MapFragment mapFragment = new MapFragment();
    AddFragment addFragment = new AddFragment();
    RewardsFragment rewardsFragment = new RewardsFragment();
    AccountFragment accountFragment = new AccountFragment();


    FragmentContainer container =  new FragmentContainer() {
        @Nullable
        @Override
        public View onFindViewById(int id) {
            return null;
        }

        @Override
        public boolean onHasView() {
            return false;
        }
    };




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.nav_home){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,homeFragment).commit();
            return true;
        } else if (id == R.id.nav_map) {
            SupportMapFragment mapFragment = SupportMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerView, mapFragment).commit();
            mapFragment.getMapAsync(this);
            return true;
        } else if (id == R.id.nav_add) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,addFragment).commit();
            return true;
        } else if (id == R.id.nav_rewards) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,rewardsFragment).commit();
            return true;
        } else if (id == R.id.nav_my_account){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, accountFragment).commit();
            return true;
        } else {
            return false;
        }
    }


    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}