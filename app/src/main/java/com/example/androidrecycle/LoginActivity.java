package com.example.androidrecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    TextView usernameTextView, passwordTextView;
    Button loginBtn;

    //User loginUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_screen);

        usernameTextView = findViewById(R.id.usernameTxt);
        passwordTextView = findViewById(R.id.password2Txt);
        loginBtn = findViewById(R.id.loginBtn);

        TextView hereTxt = findViewById(R.id.hereBacklinkTxt);
        final Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        hereTxt.setOnClickListener(v -> startActivity(intent));

        boolean credentials_match = credentialsMatch();
        loginBtn.setOnClickListener(v -> {
            if (credentials_match){

            }else{
                showWrongCredentialsPopup(v);
            }
        });
    }

    public void showWrongCredentialsPopup(View view){

        //inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_credentials_light, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setElevation(30);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupHandler.dimBehind(popupWindow);
        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        // dismiss the popup window using a button
        Button tryAgainBtn = popupView.findViewById(R.id.tryAgainBtn);
        tryAgainBtn.setOnClickListener(v -> popupWindow.dismiss());

        // go to register activity using a button
        Button registerButton = popupView.findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(v -> {
            popupWindow.dismiss();
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private boolean credentialsMatch() {
        String usrnm;
        String pswd;
        String url = "";

        return false;
    }
}