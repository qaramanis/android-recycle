package com.example.androidrecycle.admin.requests;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.androidrecycle.CustomListAdapter;
import com.example.androidrecycle.OkHttpHandler;
import com.example.androidrecycle.PopupHandler;
import com.example.androidrecycle.R;
import com.example.androidrecycle.User;
import com.example.androidrecycle.user.account.RewardsHistoryFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AdminRequestsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_requests, container, false);


        ListView listView = view.findViewById(R.id.requestList);
        List<String> itemList = Arrays.asList("Pending", "Completed");
        CustomListAdapter adapter = new CustomListAdapter(requireContext(), itemList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, v, position, id) -> {
            String selectedItem = itemList.get(position);
            switch (selectedItem){
                case "Pending":
                    getParentFragmentManager().beginTransaction().replace(R.id.AdminFragmentContainerView, PendingRequestsFragment.class, null)
                            .setReorderingAllowed(true).addToBackStack(null).commit();
                    break;
                case "Completed":
                    getParentFragmentManager().beginTransaction().replace(R.id.AdminFragmentContainerView, CompletedRequestsFragment.class, null)
                            .setReorderingAllowed(true).addToBackStack(null).commit();
                    break;
            }
        });
        return view;
    }

}