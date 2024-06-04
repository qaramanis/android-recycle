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
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidrecycle.admin.account.AdminAccountFragment;
import com.example.androidrecycle.admin.home.AdminHomeFragment;
import com.example.androidrecycle.admin.requests.AdminRequestsFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminMainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_main);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitPopup();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.admin_home);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        TextView textView = findViewById(R.id.adminToolbarTxt);

        int id = item.getItemId();

        if(id == R.id.admin_home){
            textView.setText(R.string.home);
            getSupportFragmentManager().beginTransaction().replace(R.id.AdminFragmentContainerView, AdminHomeFragment.class, null)
                    .setReorderingAllowed(true).addToBackStack(null).commit();
            return true;
        } else if (id == R.id.admin_requests) {
            textView.setText(R.string.requests);
            getSupportFragmentManager().beginTransaction().replace(R.id.AdminFragmentContainerView, AdminRequestsFragment.class, null)
                    .setReorderingAllowed(true).addToBackStack(null).commit();
            return true;
        } else if (id == R.id.admin_account) {
            textView.setText(R.string.my_account);
            getSupportFragmentManager().beginTransaction().replace(R.id.AdminFragmentContainerView, AdminAccountFragment.class, null)
                    .setReorderingAllowed(true).addToBackStack(null).commit();
            return true;
        } else {
            return false;
        }
    }

    public void showExitPopup(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_user_exit_light, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setElevation(30);

        popupWindow.showAtLocation(findViewById(R.id.adminToolbar), Gravity.CENTER, 0, 0);
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
            Intent intent = new Intent(AdminMainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

}