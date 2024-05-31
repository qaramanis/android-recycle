package com.example.androidrecycle;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.bottom_nav_bar);


        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitPopup(findViewById(R.id.popup_exit_user));
            }
        };

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
    }

    HomeFragment homeFragment = new HomeFragment();
    MapFragment mapFragment = new MapFragment();
    AddFragment addFragment = new AddFragment();
    RewardsFragment rewardsFragment = new RewardsFragment();
    AccountFragment accountFragment = new AccountFragment();





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.nav_home){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,homeFragment).commit();
            return true;
        } else if (id == R.id.nav_map) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerView, mapFragment).commit();
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
                .title("Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

  public void showExitPopup(View view){
      LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
      View popupView = inflater.inflate(R.layout.popup_user_exit_light, null);

      int width = LinearLayout.LayoutParams.WRAP_CONTENT;
      int height = LinearLayout.LayoutParams.WRAP_CONTENT;
      boolean focusable = true;
      final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
      popupWindow.setElevation(30);

      popupWindow.showAtLocation(view, Gravity.CENTER, 0,0);
      popupHandler.dimBehind(popupWindow);

      popupView.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
              popupWindow.dismiss();
              return true;
          }
      });

      Button exitBtn = popupView.findViewById(R.id.exitBtn);
      exitBtn.setOnClickListener(v -> {
          popupWindow.dismiss();
          finish();
          System.exit(0);
      });

      Button logoutBtn = popupView.findViewById(R.id.logoutBtn);
      logoutBtn.setOnClickListener(v -> {
          popupWindow.dismiss();
          Intent intent = new Intent(UserMainActivity.this, LoginActivity.class);
          startActivity(intent);
      });
  }


}