package com.example.androidrecycle.user.rewards;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidrecycle.LoginActivity;
import com.example.androidrecycle.PopupHandler;
import com.example.androidrecycle.R;
import com.example.androidrecycle.RegisterActivity;
import com.example.androidrecycle.User;
import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.Arrays;
import java.util.List;

public class UserRewardsFragment extends Fragment implements com.example.androidrecycle.ui.rewards.CarouselAdapter.OnItemClickListener{

    private List<Integer> imageList;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_rewards, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        imageList = Arrays.asList(
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image
                );

        com.example.androidrecycle.ui.rewards.CarouselAdapter adapter = new com.example.androidrecycle.ui.rewards.CarouselAdapter(requireContext(), imageList);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new CarouselLayoutManager());
        return view;
    }


    @Override
    public void onItemClick(int position) {
        int clickedImageRes = imageList.get(position);
        if (User.getInstance().getPoints() >=100){
            showAvailableRewardsPopup(getView());
        }else{
            showNotEnoughPointsPopup(getView());
        }
    }

    public void showAvailableRewardsPopup(View view){

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_rewards_light, null);

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

        Button redeemBtn = popupView.findViewById(R.id.redeemBtn);
        redeemBtn.setOnClickListener(v -> {
            popupWindow.dismiss();
            Toast.makeText(requireContext(), "Request sent to admin.\nWaiting for approval", Toast.LENGTH_SHORT).show();
            User.getInstance().setPoints(-100);
            //TODO backend missing
            }
        );

        Button canelBtn = popupView.findViewById(R.id.redeemCancelBtn);
        canelBtn.setOnClickListener(v -> {
            popupWindow.dismiss();
        });
    }
    public void showNotEnoughPointsPopup(View view) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_not_enough_points_light, null);

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

        Button okBtn = popupView.findViewById(R.id.okBtn);
        okBtn.setOnClickListener(v -> {
            popupWindow.dismiss();
        });
    }
}