package com.example.androidrecycle.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidrecycle.R;

import java.util.Arrays;
import java.util.List;

public class AccountFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        ListView listView = getView().findViewById(R.id.account_list);
        List<String> itemList = Arrays.asList("Item 1", "Item 2", "Item 3");

        CustomListAdapter adapter = new CustomListAdapter(requireContext(), itemList);
        listView.setAdapter(adapter);

        return view;
    }
}