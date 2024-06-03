package com.example.androidrecycle.ui.home;

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

public class HomeFragment extends Fragment {

    int points = 47;
    int totalPoints;
    int paperPoints, glassPoints, aluminumPoints, otherPoints;
    private FragmentSwitcher fragmentSwitcher;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        totalPoints = paperPoints + glassPoints + aluminumPoints + otherPoints;

        ProgressBar progressBar = getView().findViewById(R.id.progressBar);
        TextView progressText = getView().findViewById(R.id.progress_text);

        progressText.setText(points + "/100");
        progressBar.setProgress(points);


        TextView availableText = getView().findViewById(R.id.avaialbleTxt);
        if(points>=100)
            availableText.setVisibility(View.VISIBLE);
        else
            availableText.setVisibility(View.GONE);

        availableText.setOnClickListener(v -> fragmentSwitcher.switchFragment(R.id.nav_rewards));

        TextView paperText = getView().findViewById(R.id.paperPointsTxt);
        paperText.setText("Paper points: " + paperPoints);

        TextView glassText = getView().findViewById(R.id.glassPointTxt);
        glassText.setText("Glass points: " + glassPoints);

        TextView aluminumText = getView().findViewById(R.id.aluminumPointsTxt);
        aluminumText.setText("Aluminum points: " + aluminumPoints);

        TextView otherText = getView().findViewById(R.id.otherPointsTxt);
        otherText.setText("Other points: " + otherPoints);

        TextView totalText = getView().findViewById(R.id.totalText);
        totalText.setText("Total points: " + totalPoints);

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