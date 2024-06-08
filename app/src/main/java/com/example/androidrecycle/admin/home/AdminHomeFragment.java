package com.example.androidrecycle.admin.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.androidrecycle.CustomListAdapter;
import com.example.androidrecycle.OkHttpHandler;
import com.example.androidrecycle.R;
import com.example.androidrecycle.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminHomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        List<Map<String, String>> data = new ArrayList<>();
        JSONObject userResponse = null;
        User currUser = User.getInstance();

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            userResponse = okHttpHandler.getTopRecyclers();
            if (userResponse.getBoolean("success")) {
                JSONArray topRecyclers = userResponse.getJSONArray("topRecyclers");
                for (int i = 0; i < 3; i++) {
                    Map<String, String> rec = new HashMap<>();
                    JSONObject recycler = topRecyclers.getJSONObject(i);
                    int totalPoints = recycler.getInt("totalPoints");
                    String username = recycler.getString("username");
                    rec.put("line 1", username);
                    rec.put("line 2", "Total points: " + totalPoints);
                    data.add(rec);
                }
            } else {
                System.out.println("No requests found in the response.");
            }
            System.out.println("Response: " + userResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SimpleAdapter adapter = new SimpleAdapter(getContext(), data,
                android.R.layout.simple_list_item_2,
                new String[] {"line 1", "line 2"},
                new int[] {android.R.id.text1, android.R.id.text2});

        ListView listView = view.findViewById(R.id.topRecList);
        listView.setAdapter(adapter);

        return view;
    }
}