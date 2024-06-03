package com.example.androidrecycle;

import android.app.Activity;
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
import androidx.compose.ui.text.InternalTextApi;

import com.example.androidrecycle.user.account.AccountFragment;
import com.example.androidrecycle.user.add.AddFragment;
import com.example.androidrecycle.user.home.HomeFragment;
import com.example.androidrecycle.user.map.MapFragment;
import com.example.androidrecycle.user.home.FragmentSwitcher;
import com.example.androidrecycle.user.rewards.RewardsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserMainActivity extends AppCompatActivity implements FragmentSwitcher,NavigationBarView.OnItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    Intent intent = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_main);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                PopupHandler handler = new PopupHandler();
                handler.showExitPopup(intent);
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.user_home);

    }

    public void switchFragment(int itemId) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(itemId);
    }


    HomeFragment homeFragment = new HomeFragment();
    RewardsFragment rewardsFragment = new RewardsFragment();
    AccountFragment accountFragment = new AccountFragment();





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.topToolbar);
        setSupportActionBar(toolbar);
        TextView textView = (TextView) findViewById(R.id.toolbarTitleTxt);

        int id = item.getItemId();

        if(id == R.id.user_home){
                textView.setText(R.string.home);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,homeFragment).commit();
            return true;
        } else if (id == R.id.user_map) {
            textView.setText(R.string.map);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,MapFragment.class, null).setReorderingAllowed(true)
                    .addToBackStack(null).commit();
            return true;
        } else if (id == R.id.user_add) {
            textView.setText(R.string.add);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,AddFragment.class, null).setReorderingAllowed(true)
                    .addToBackStack(null).commit();
            return true;
        } else if (id == R.id.user_rewards) {
            textView.setText(R.string.rewards);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,rewardsFragment).commit();
            return true;
        } else if (id == R.id.user_account){
            textView.setText(R.string.my_account);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, accountFragment).commit();
            return true;
        } else {
            return false;
        }
    }


}