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

import com.example.androidrecycle.LoginActivity;
import com.example.androidrecycle.OkHttpHandler;
import com.example.androidrecycle.R;
import com.example.androidrecycle.SharedViewModel;
import com.example.androidrecycle.User;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONObject;


public class CustomBottomSheetFragment extends BottomSheetDialogFragment {

    private SharedViewModel sharedViewModel;
    TextView paperText;
    TextView glassText;
    TextView aluminumText;

    Integer paperAmount;
    Integer glassAmount;
    Integer aluminumAmount;

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



            int paperPoints = 0;
            if (paperAmount != null) {
                paperPoints = paperAmount *  10;
                sharedViewModel.updatePoints(0,paperPoints);
            }
            int glassPoints = 0;
            if(glassAmount != null){
                glassPoints = (glassAmount * 10);
                sharedViewModel.updatePoints(1,glassPoints);
            }

            int aluminumPoints = 0;
            if(aluminumAmount != null){
                aluminumPoints = aluminumAmount / 2;
                sharedViewModel.updatePoints(2,aluminumPoints);
            }
            int totalPoints = (paperPoints + glassPoints + aluminumPoints);
            User.getInstance().setPoints(totalPoints);
            sharedViewModel.updatePoints(3,totalPoints);
            Toast.makeText(getContext(), "Total points: " + totalPoints, Toast.LENGTH_SHORT).show();

            paperText.setText("Kgs of paper: -");
            glassText.setText("Glass bottles: -");
            aluminumText.setText("Number of cans: - ");

            sharedViewModel.setEditTextValue(null);

            JSONObject userResponse = null;

            try {
                OkHttpHandler okHttpHandler = new OkHttpHandler();
                userResponse = okHttpHandler.addPoints(User.getInstance().getId(), 1, 2, 3);
                System.out.println("Response: " + userResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }

            dismiss();
        });

        return view;
    }
}
