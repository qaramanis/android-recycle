package com.example.androidrecycle.user.home;

import com.example.androidrecycle.OkHttpHandler;
import com.example.androidrecycle.User;
import com.example.androidrecycle.user.add.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidrecycle.R;

import org.json.JSONException;
import org.json.JSONObject;

public class UserHomeFragment extends Fragment{

    int currPoints = 30;
    Integer totalPoints = 0;
    int paperPoints, glassPoints, aluminumPoints;
    private FragmentSwitcher fragmentSwitcher;
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        TextView progressText = view.findViewById(R.id.progress_text);

        currPoints = User.getInstance().getPoints();
        progressText.setText(String.format("%d/100", currPoints));
        progressBar.setProgress(currPoints);

        JSONObject userResponse = null;
        User currUser = User.getInstance();
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            userResponse = okHttpHandler.getMyPointsHistory(currUser.getId());
            System.out.println("Response: " + userResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONObject userData = userResponse.getJSONObject("pointsHistory");
            paperPoints = Integer.parseInt(userData.getString("paper"));
            glassPoints = Integer.parseInt(userData.getString("glass"));
            aluminumPoints = Integer.parseInt(userData.getString("aluminum"));
            totalPoints =  paperPoints + glassPoints + aluminumPoints;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        TextView availableText = view.findViewById(R.id.avaialbleTxt);
        if(currPoints>=100)
            availableText.setText("Rewards are available");
        else
            availableText.setVisibility(View.GONE);

        availableText.setOnClickListener(v -> fragmentSwitcher.switchFragment(R.id.user_rewards));

        TextView paperText = view.findViewById(R.id.paperPointsTxt);
        paperText.setText(String.format("Paper points: %d", paperPoints));

        TextView glassText = view.findViewById(R.id.glassPointTxt);
        glassText.setText(String.format("Glass points: %d", glassPoints));

        TextView aluminumText = view.findViewById(R.id.aluminumPointsTxt);
        aluminumText.setText(String.format("Aluminum points: %d", aluminumPoints));

        TextView totalText = view.findViewById(R.id.totalText);
        totalText.setText(String.format("Total points: %d", totalPoints));

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if (context instanceof FragmentSwitcher){
            fragmentSwitcher = (FragmentSwitcher) context;
        }else {
            throw new RuntimeException(context + "must implement Fragment Switcher");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentSwitcher = null;
    }

}