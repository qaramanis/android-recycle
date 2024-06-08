package com.example.androidrecycle.user.account;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidrecycle.LoginActivity;
import com.example.androidrecycle.PopupHandler;
import com.example.androidrecycle.R;
import com.example.androidrecycle.CustomListAdapter;
import com.example.androidrecycle.User;
import com.example.androidrecycle.user.home.UserHomeFragment;

import java.util.Arrays;
import java.util.List;

public class UserAccountFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_account, container, false);
        User currUser = User.getInstance();
        TextView name = view.findViewById(R.id.nameTxt);
        name.setText(currUser.getUsername());

        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_account);

        ListView listView = view.findViewById(R.id.account_list);
        List<String> itemList = Arrays.asList("Rewards History", "Logout");


        CustomListAdapter adapter = new CustomListAdapter(requireContext(), itemList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedItem = itemList.get(position);
            switch (selectedItem){
                case "Rewards History":
                    getParentFragmentManager().beginTransaction().replace(R.id.UserFragmentContainerView, RewardsHistoryFragment.class, null)
                            .setReorderingAllowed(true).addToBackStack(null).commit();
                    break;
                case "Logout":
                    showLogoutPopup(view1);
                    break;
            }
        });

        return view;
    }

    public void showLogoutPopup(View view){

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_logout_light, null);

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

        Button cancel = popupView.findViewById(R.id.cancelBtn2);
        cancel.setOnClickListener(v -> popupWindow.dismiss());

        Button registerButton = popupView.findViewById(R.id.logoutBtn2);
        registerButton.setOnClickListener(v -> {
            popupWindow.dismiss();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });
    }
}