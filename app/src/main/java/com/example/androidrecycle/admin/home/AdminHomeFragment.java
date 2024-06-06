package com.example.androidrecycle.admin.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.androidrecycle.R;

import java.util.ArrayList;
import java.util.List;

public class AdminHomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);
        ListView listView = view.findViewById(R.id.topRecList);
        List<String> itemList = new ArrayList<>();
        //TODO populate list with top contributor descending according to points
        return view;
    }
}