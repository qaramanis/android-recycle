package com.example.androidrecycle.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidrecycle.FragmentSwitcher;
import com.example.androidrecycle.R;
import com.example.androidrecycle.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.Inflater;

public class HomeFragment extends Fragment {

    private ProgressBar progressBar;
    private TextView progressText;
    private TextView availableText, totalText, paperText, glassText, aluminumText, otherText;
    int points = 47;
    int totalPoints;
    int paperPoints, glassPoints, aluminumPoints, otherPoints;
    private FragmentSwitcher fragmentSwitcher;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        totalPoints = paperPoints + glassPoints + aluminumPoints + otherPoints;

        progressBar = getView().findViewById(R.id.progressBar);
        progressText = getView().findViewById(R.id.progress_text);

        progressText.setText(points + "/100");
        progressBar.setProgress(points);


        availableText = getView().findViewById(R.id.avaialbleTxt);
        if(points>=100)
            availableText.setVisibility(View.VISIBLE);
        else
            availableText.setVisibility(View.GONE);

        availableText.setOnClickListener(v -> fragmentSwitcher.switchFragment(R.id.nav_rewards));

        paperText = getView().findViewById(R.id.paperPointsTxt);
        paperText.setText("Paper points: " + paperPoints);

        glassText = getView().findViewById(R.id.glassPointTxt);
        glassText.setText("Glass points: " + glassPoints);

        aluminumText = getView().findViewById(R.id.aluminumPointsTxt);
        aluminumText.setText("Aluminum points: " + aluminumPoints);

        otherText = getView().findViewById(R.id.otherPointsTxt);
        otherText.setText("Other points: " + otherPoints);

        totalText = getView().findViewById(R.id.totalText);
        totalText.setText("Total points: " + totalPoints);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if (context instanceof FragmentSwitcher){
            fragmentSwitcher = (FragmentSwitcher) context;
        }else {
            throw new RuntimeException(context.toString()+ "must implement Fragment Switcher");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentSwitcher = null;
    }

}