package com.example.androidrecycle.user.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidrecycle.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class CustomBottomSheetFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        TextView paperText = view.findViewById(R.id.paperCheck);
        paperText.setText("Kgs of paper: -");
        TextView glassText = view.findViewById(R.id.glassCheck);
        glassText.setText("Glass bottles: -");
        TextView aluminumText = view.findViewById(R.id.aluminumCheck);
        aluminumText.setText("Number of cans: - ");

        Button confirm = view.findViewById(R.id.confirmButton);
        confirm.setOnClickListener(v -> {
            //TODO calculate points as well
           dismiss();
        });

        return view;
    }
}
