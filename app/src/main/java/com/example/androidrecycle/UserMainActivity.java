package com.example.androidrecycle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.androidrecycle.ui.account.AccountFragment;
import com.example.androidrecycle.ui.add.AddFragment;
import com.example.androidrecycle.ui.home.HomeFragment;
import com.example.androidrecycle.ui.map.MapFragment;
import com.example.androidrecycle.ui.rewards.RewardsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.w3c.dom.Text;

public class UserMainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bottom_nav_bar);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitPopup();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
    }


    HomeFragment homeFragment = new HomeFragment();
    RewardsFragment rewardsFragment = new RewardsFragment();
    AccountFragment accountFragment = new AccountFragment();





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(toolbar);
        TextView textView = (TextView) findViewById(R.id.toolbarTitleTxt);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        int id = item.getItemId();

        if(id == R.id.nav_home){
                textView.setText(R.string.home);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,homeFragment).commit();
            return true;
        } else if (id == R.id.nav_map) {
            textView.setText(R.string.map);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,MapFragment.class, null).setReorderingAllowed(true)
                    .addToBackStack(null).commit();
            return true;
        } else if (id == R.id.nav_add) {
            textView.setText(R.string.add);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,AddFragment.class, null).setReorderingAllowed(true)
                    .addToBackStack(null).commit();
            return true;
        } else if (id == R.id.nav_rewards) {
            textView.setText(R.string.rewards);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,rewardsFragment).commit();
            return true;
        } else if (id == R.id.nav_my_account){
            textView.setText(R.string.my_account);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, accountFragment).commit();
            return true;
        } else {
            return false;
        }
    }


    public void showExitPopup(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View  popupView = inflater.inflate(R.layout.popup_user_exit_light, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setElevation(30);

        popupWindow.showAtLocation(findViewById(R.id.bottom_navigation), Gravity.CENTER, 0, 0);
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
        });

        Button logoutBtn = popupView.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(v -> {
            popupWindow.dismiss();
            Intent intent = new Intent(UserMainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}