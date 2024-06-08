package com.example.androidrecycle.admin.requests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidrecycle.OkHttpHandler;
import com.example.androidrecycle.R;
import com.example.androidrecycle.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompletedRequestsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_completed_requests, container, false);
        ImageView back = view.findViewById(R.id.backArrowIcon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.AdminFragmentContainerView, AdminRequestsFragment.class, null)
                        .setReorderingAllowed(true).addToBackStack(null).commit();
            }
        });
        List<Map<String, String>> data = new ArrayList<>();
        JSONObject userResponse = null;
        User currUser = User.getInstance();
        ArrayList<Integer> ids = new ArrayList<>();

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            userResponse = okHttpHandler.getAllRequests();
            if (userResponse.getBoolean("success")) {
                JSONArray requestsArray = userResponse.getJSONArray("requests");
                for (int i = 0; i < requestsArray.length(); i++) {
                    Map<String, String> reward = new HashMap<>();
                    JSONObject requestItem = requestsArray.getJSONObject(i);
                    int rewardId = requestItem.getInt("reward_id");
                    int userId = requestItem.getInt("user_id");
                    int id = requestItem.getInt("id");
                    if(!requestItem.isNull("approved")){
                        int approved = requestItem.getInt("approved");
                        reward.put("line 1", "Reward ID: " + id);
                        if(approved==0){
                            reward.put("line 2", "Rejected");
                        }else{
                            reward.put("line 2", "Accepted");
                        }
                        ids.add(id);
                        data.add(reward);
                    }
                }
            } else {
                System.out.println("No requests found in the response.");
            }
            System.out.println("Response: " + userResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        CustomSimpleAdapter adapter = new CustomSimpleAdapter(getContext(), data,
                android.R.layout.simple_list_item_2,
                new String[] {"line 1", "line 2"},
                new int[] {android.R.id.text1, android.R.id.text2});


        ListView listView = view.findViewById(R.id.rewardsList);
        listView.setAdapter(adapter);
        return view;
    }
}
