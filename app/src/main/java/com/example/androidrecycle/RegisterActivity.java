package com.example.androidrecycle;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register_screen);
        boolean credentials = true;
        Button registerCheck = findViewById(R.id.registerCheckBtn);
        registerCheck.setOnClickListener(v -> {
            if(credentials){
                onButtonShowPopupWindowClickWhenRegistrationComplete(v);
            }else{
                int i=0;
            }
        });

        Button cancelBtn = findViewById(R.id.cancelLightBtn);
        cancelBtn.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }


    public void onButtonShowPopupWindowClickWhenRegistrationComplete(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_successful_register_light,null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setElevation(30);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupHandler.dimBehind(popupWindow);
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                Intent intent = new Intent(RegisterActivity.this, UserMainActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

        Button continueBtn = popupView.findViewById(R.id.continueLightBtn);
        continueBtn.setOnClickListener(v -> {
            popupWindow.dismiss();
            Intent intent = new Intent(RegisterActivity.this, UserMainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
