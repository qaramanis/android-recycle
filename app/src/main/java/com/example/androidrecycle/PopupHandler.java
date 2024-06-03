package com.example.androidrecycle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

public class PopupHandler extends AppCompatActivity {


    public PopupHandler(){
        super();
    }

    // dims the background when a popup appears
    @SuppressLint("ObsoleteSdkInt")
    public static void dimBehind(PopupWindow popupWindow) {
        View container = getView(popupWindow);
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.3f;
        wm.updateViewLayout(container, p);
    }

    private static View getView(PopupWindow popupWindow) {
        View container;
        if (popupWindow.getBackground() == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                container = (View) popupWindow.getContentView().getParent();
            } else {
                container = popupWindow.getContentView();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                container = (View) popupWindow.getContentView().getParent().getParent();
            } else {
                container = (View) popupWindow.getContentView().getParent();
            }
        }
        return container;
    }


    public void showExitPopup(Intent intent){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View  popupView = inflater.inflate(R.layout.popup_user_exit_light, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setElevation(30);

        popupWindow.showAtLocation(findViewById(R.id.bottom_navigation), Gravity.CENTER, 0, 0);
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
            startActivity(intent);
            finish();
        });
    }
}

