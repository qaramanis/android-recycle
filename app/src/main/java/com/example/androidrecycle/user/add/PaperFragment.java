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


public class PaperFragment extends Fragment {

    private SharedViewModel sharedViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_paper, container, false);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        EditText editPaperAmount = view.findViewById(R.id.editAluminumAmount);

        editPaperAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                sharedViewModel.setPaperAmountString(text);
                try {
                    int value = Integer.parseInt(text);
                    sharedViewModel.setPaperAmountInteger(value);
                } catch (NumberFormatException e) {
                    sharedViewModel.setPaperAmountInteger(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        sharedViewModel.getEditTextValue().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String value) {
                editPaperAmount.setText(value);
            }
        });

        return view;
    }

    public void sendText(){
        EditText text = getView().findViewById(R.id.editAluminumAmount);
        String textToSend = text.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("text_key", textToSend);

        CustomBottomSheetFragment receiver = new CustomBottomSheetFragment();
        receiver.setArguments(bundle);
        if (receiver != null){
            receiver.setArguments(bundle);
        }
    }
}