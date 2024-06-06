package com.example.androidrecycle.user.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidrecycle.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class CustomBottomSheetFragment extends BottomSheetDialogFragment {

    private SharedViewModel sharedViewModel;
    TextView paperText;
    TextView glassText;
    TextView aluminumText;

    Integer paperAmount;
    Integer glassAmount;
    Integer aluminumAmount;

    EditText paperEdit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        paperText = view.findViewById(R.id.paperCheck);
        paperText.setText("Kgs of paper: -");

        sharedViewModel.getPaperAmountInteger().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer value1) {
                paperAmount = sharedViewModel.getPaperAmountInteger().getValue();
            }
        });

        sharedViewModel.getPaperAmountString().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                paperText.setText(String.format("Kgs of paper: %s", s));
            }
        });




        glassText = view.findViewById(R.id.glassCheck);
        glassText.setText("Glass bottles: -");

        sharedViewModel.getGlassAmountInteger().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer value1) {
                glassAmount = sharedViewModel.getGlassAmountInteger().getValue();
            }
        });
        sharedViewModel.getGlassAmountString().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                glassText.setText(String.format("Glass bottles: %s", s));
            }
        });

        aluminumText = view.findViewById(R.id.aluminumCheck);
        aluminumText.setText("Number of cans: - ");

        sharedViewModel.getAluminumAmountInteger().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer value1) {
                aluminumAmount = sharedViewModel.getGlassAmountInteger().getValue();
            }
        });

        sharedViewModel.getAluminumAmountString().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                aluminumText.setText(String.format("Number of cans: %s", s));
            }
        });

        Button confirm = view.findViewById(R.id.confirmButton);
        confirm.setOnClickListener(v -> {
            float paperPoints = 0;
            if (paperAmount != null) {
                paperPoints = (float) (double) (paperAmount * 10);
                sharedViewModel.updatePoints(0, (int) paperPoints);
            }
            float glassPoints = 0;
            if(glassAmount != null){
                glassPoints = (float) (double) (glassAmount * 10);
                sharedViewModel.updatePoints(1, (int) glassPoints);
            }

            float aluminumPoints = 0;
            if(aluminumAmount != null){
                aluminumPoints = (float) (double) (aluminumAmount / 2);
                sharedViewModel.updatePoints(2, (int) aluminumPoints);
            }
            float totalPoints = paperPoints + glassPoints + aluminumPoints ;
            sharedViewModel.updatePoints(3, (int) totalPoints);
            Toast.makeText(getContext(), "Total points: " + totalPoints, Toast.LENGTH_SHORT).show();

            paperText.setText("Kgs of paper: -");
            glassText.setText("Glass bottles: -");
            aluminumText.setText("Number of cans: - ");

            sharedViewModel.setEditTextValue(null);
            //TODO show points in home screen
            dismiss();
        });

        return view;
    }
}
