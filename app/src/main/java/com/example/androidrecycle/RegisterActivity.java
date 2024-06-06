package com.example.androidrecycle;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidrecycle.user.User;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private Intent intent;
    private TextView textViewError;
    private EditText enterPassword;
    private EditText confirmPassword;
    private int credentials = 3;


    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register_screen);

        enterPassword = findViewById(R.id.enterTxt);
        confirmPassword = findViewById(R.id.confirmTxt);
        textViewError = findViewById(R.id.errorTxt);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed before text change
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = enterPassword.getText().toString();
                String confirmPs = confirmPassword.getText().toString();
                if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPs)) {
                    textViewError.setText("Please fill out both fields");
                    textViewError.setVisibility(View.VISIBLE);
//                    credentials = 3;
                } else if (!password.equals(confirmPs)) {
                    textViewError.setText("Passwords do not match");
                    textViewError.setVisibility(View.VISIBLE);
//                    credentials = 3;
                } else {
                    textViewError.setVisibility(View.GONE);
//                    credentials = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed after text change
            }
        };

        enterPassword.addTextChangedListener(textWatcher);
        confirmPassword.addTextChangedListener(textWatcher);

        //0 for user, 1 for taken username, else password error
        Button registerCheck = findViewById(R.id.registerCheckBtn);
        registerCheck.setOnClickListener(v -> {
            String password = enterPassword.getText().toString();
            String username = ((EditText) findViewById(R.id.usernameTxt)).getText().toString();
            JSONObject userResponse = null;
            User currUser = null;

            try {
                OkHttpHandler okHttpHandler = new OkHttpHandler();
                userResponse = okHttpHandler.register(username, password, 0);
                System.out.println("Response: " + userResponse);

            } catch (Exception e) {
                e.printStackTrace();
            }

            if(userResponse == null){
                Toast.makeText(RegisterActivity.this, "Server is not responding", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                boolean succsess = Boolean.parseBoolean(userResponse.getString("success"));
                if(!succsess){
                    return;
                }else{
                    JSONObject userData = userResponse.getJSONObject("user");
                    int id = Integer.parseInt(userData.getString("id"));
                    String username1 = userData.getString("username");
                    int role = Integer.parseInt(userData.getString("role"));
                    currUser = User.getInstance(id, username1, role);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            //TODO check credentials from database
            if(currUser.getRole()==0){
                intent = new Intent(RegisterActivity.this, UserMainActivity.class);
                showRegisteredPopup(v, intent);
            } else if (currUser.getRole()==2) {
                showErrorPopup(v);
            } else Toast.makeText(this,"Check password input", Toast.LENGTH_SHORT).show();

        });

        Button cancelBtn = findViewById(R.id.cancelLightBtn);
        cancelBtn.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }



    public void showRegisteredPopup(View view, Intent intent){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_successful_register_light,null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setElevation(30);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        PopupHandler.dimBehind(popupWindow);
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                startActivity(intent);
                finish();
                return true;
            }
        });

        Button continueBtn = popupView.findViewById(R.id.continueLightBtn);
        continueBtn.setOnClickListener(v -> {
            popupWindow.dismiss();
            startActivity(intent);
            finish();
        });
    }

    public void showErrorPopup(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_register_error_light,null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setElevation(30);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        PopupHandler.dimBehind(popupWindow);

        Button tryAgainBtn = popupView.findViewById(R.id.retryUsernameBtn);
        tryAgainBtn.setOnClickListener(v -> popupWindow.dismiss());
    }
}
