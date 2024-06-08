package com.example.androidrecycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    TextView usernameTextView, passwordTextView;
    Button loginBtn;
    Intent intent;

    //User loginUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_screen);

        usernameTextView = findViewById(R.id.usernameTxt);
        passwordTextView = findViewById(R.id.enterTxt);
        loginBtn = findViewById(R.id.loginBtn);

        TextView hereTxt = findViewById(R.id.hereBacklinkTxt);
        intent = new Intent(LoginActivity.this, RegisterActivity.class);
        hereTxt.setOnClickListener(v -> startActivity(intent));




//        int credentials = 1; //0 for user, 1 for admin, 2 for wrong
        loginBtn.setOnClickListener(v -> {
            String usernameText = usernameTextView.getText().toString();
            String password = passwordTextView.getText().toString();
            JSONObject userResponse = null;
            User currUser = null;


            if(usernameText.isEmpty() || password.isEmpty()){
                showWrongCredentialsPopup(v);
                return;
            }

            try {
                OkHttpHandler okHttpHandler = new OkHttpHandler();
                userResponse = okHttpHandler.login(usernameText, password);
                System.out.println("Response: " + userResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(userResponse == null){
                Toast.makeText(LoginActivity.this, "Server is not responding", Toast.LENGTH_SHORT).show();
                System.out.println("Username: " + usernameText + " Password: " + password);
                return;
            }

            try {
                boolean success = Boolean.parseBoolean(userResponse.getString("success"));
                if(!success){
                    showWrongCredentialsPopup(v);
                    return;
                }else{
                    JSONObject userData = userResponse.getJSONObject("user");
                    int id = Integer.parseInt(userData.getString("id"));
                    String username = userData.getString("username");
                    int role = Integer.parseInt(userData.getString("role"));
                    int currPoints = Integer.parseInt(userData.getString("currentPoints"));
                    currUser = User.getInstance(id, username, role, currPoints);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            if(currUser.getRole() == 0){
                currUser = null;
                intent.setClass(LoginActivity.this, UserMainActivity.class);
                startActivity(intent);
                finish();
            }else if (currUser.getRole()==1){
                currUser = null;
                intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                startActivity(intent);
                finish();
            }else showWrongCredentialsPopup(v);
        });
    }

    public void showWrongCredentialsPopup(View view){

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_credentials_light, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setElevation(30);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        PopupHandler.dimBehind(popupWindow);
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        Button tryAgainBtn = popupView.findViewById(R.id.tryAgainBtn);
        tryAgainBtn.setOnClickListener(v -> popupWindow.dismiss());

        Button registerButton = popupView.findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(v -> {
            popupWindow.dismiss();
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }

}