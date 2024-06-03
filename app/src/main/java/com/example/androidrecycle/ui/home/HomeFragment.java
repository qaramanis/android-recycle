package com.example.androidrecycle.ui.home;

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

public class HomeFragment extends Fragment{

    int points = 47;
    int totalPoints = 0;
    int paperPoints = 0, glassPoints = 0, aluminumPoints = 0, otherPoints = 0;
    private FragmentSwitcher fragmentSwitcher;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        totalPoints = paperPoints + glassPoints + aluminumPoints + otherPoints;

        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        TextView progressText = view.findViewById(R.id.progress_text);

        progressText.setText(String.format("%d/100", points));
        progressBar.setProgress(points);


        TextView availableText = view.findViewById(R.id.avaialbleTxt);
        if(points>=100)
            availableText.setText("No rewards available");
        else
            availableText.setText("Rewards are available");

        availableText.setOnClickListener(v -> fragmentSwitcher.switchFragment(R.id.user_map));

        TextView paperText = view.findViewById(R.id.paperPointsTxt);
        paperText.setText(String.format("Paper points: %d", paperPoints));

        TextView glassText = view.findViewById(R.id.glassPointTxt);
        glassText.setText(String.format("Glass points: %d", glassPoints));

        TextView aluminumText = view.findViewById(R.id.aluminumPointsTxt);
        aluminumText.setText(String.format("Aluminum points: %d", aluminumPoints));

        TextView otherText = view.findViewById(R.id.otherPointsTxt);
        otherText.setText(String.format("Other points: %d", otherPoints));

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