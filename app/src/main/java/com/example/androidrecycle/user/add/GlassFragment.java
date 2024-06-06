package com.example.androidrecycle.user.add;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.androidrecycle.R;


public class GlassFragment extends Fragment {
    private SharedViewModel sharedViewModel;
    EditText editGlassAmount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_glass, container, false);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        editGlassAmount = view.findViewById(R.id.editGlassAmount);
        editGlassAmount.getText();

        editGlassAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                sharedViewModel.setGlassAmountString(text);
                try {
                    int value = Integer.parseInt(text);
                    sharedViewModel.setGlassAmountInteger(value);
                } catch (NumberFormatException e) {
                    sharedViewModel.setGlassAmountInteger(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        sharedViewModel.getEditTextValue().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String value) {
                editGlassAmount.setText(value);
            }
        });

       return view;
    }
}