package com.example.androidrecycle.admin.requests;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import static androidx.core.content.ContextCompat.getSystemService;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.androidrecycle.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AdminRequestsFragment extends Fragment {

    ExpandableListView expandableListView;
    CustomExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    Map<String, List<String>> expandableListDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_requests, container, false);

        expandableListView = view.findViewById(R.id.pendingExpandable);
        expandableListDetail = getData();
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(requireContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        return view;
    }

    private Map<String, List<String>> getData() {
        Map<String, List<String>> expandableListDetail = new LinkedHashMap<>();

        List<String> completed = new ArrayList<>();
        completed.add("completed request");
        completed.add("completed request");

        List<String> pending = new ArrayList<>();
        pending.add("pending request");
        pending.add("pending request");

        expandableListDetail.put("Pending", pending);
        expandableListDetail.put("Completed", completed);

        return expandableListDetail;
    }

}