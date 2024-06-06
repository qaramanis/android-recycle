package com.example.androidrecycle;

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

import com.example.androidrecycle.user.account.UserAccountFragment;
import com.example.androidrecycle.user.add.UserAddFragment;
import com.example.androidrecycle.user.home.UserHomeFragment;
import com.example.androidrecycle.user.map.UserMapFragment;
import com.example.androidrecycle.user.home.FragmentSwitcher;
import com.example.androidrecycle.user.rewards.UserRewardsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserMainActivity extends AppCompatActivity implements FragmentSwitcher,NavigationBarView.OnItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_main);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitPopup();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);


        bottomNavigationView = findViewById(R.id.user_bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.user_home);
    }

    public void switchFragment(int itemId) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.user_bottom_navigation);
        bottomNavigationView.setSelectedItemId(itemId);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        TextView textView = findViewById(R.id.userToolbarTxt);
        int id = item.getItemId();

        if(id == R.id.user_home){
            textView.setText(R.string.home);
            getSupportFragmentManager().beginTransaction().replace(R.id.UserFragmentContainerView, UserHomeFragment.class, null)
                    .setReorderingAllowed(true).addToBackStack(null).commit();
            return true;
        } else if (id == R.id.user_map) {
            textView.setText(R.string.map);
            getSupportFragmentManager().beginTransaction().replace(R.id.UserFragmentContainerView, UserMapFragment.class, null).
                    setReorderingAllowed(true).addToBackStack(null).commit();
            return true;
        } else if (id == R.id.user_add) {
            textView.setText(R.string.add);
            getSupportFragmentManager().beginTransaction().replace(R.id.UserFragmentContainerView, UserAddFragment.class, null)
                    .setReorderingAllowed(true).addToBackStack(null).commit();
            return true;
        } else if (id == R.id.user_rewards) {
            textView.setText(R.string.rewards);
            getSupportFragmentManager().beginTransaction().replace(R.id.UserFragmentContainerView, UserRewardsFragment.class, null)
                    .setReorderingAllowed(true).addToBackStack(null).commit();
            return true;
        } else if (id == R.id.user_account){
            textView.setText(R.string.my_account);
            getSupportFragmentManager().beginTransaction().replace(R.id.UserFragmentContainerView, UserAccountFragment.class, null)
                    .setReorderingAllowed(true).addToBackStack(null).commit();
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

        popupWindow.showAtLocation(findViewById(R.id.user_bottom_navigation), Gravity.CENTER, 0, 0);
        PopupHandler.dimBehind(popupWindow);

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