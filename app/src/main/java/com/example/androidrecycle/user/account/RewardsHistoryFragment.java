package com.example.androidrecycle.user.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidrecycle.CustomListAdapter;
import com.example.androidrecycle.OkHttpHandler;
import com.example.androidrecycle.R;
import com.example.androidrecycle.User;
import com.example.androidrecycle.user.home.UserHomeFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RewardsHistoryFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_rewards_history, container, false);

        ImageView back = view.findViewById(R.id.backArrowIcon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.UserFragmentContainerView, UserAccountFragment.class, null)
                        .setReorderingAllowed(true).addToBackStack(null).commit();
            }
        });

        List<Map<String, String>> data = new ArrayList<>();

        JSONObject userResponse = null;
        User currUser = User.getInstance();
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            userResponse = okHttpHandler.getMyRequests(currUser.getId());
            if (userResponse.getBoolean("success")) {
                JSONArray requestsArray = userResponse.getJSONArray("requests");
                for (int i = 0; i < requestsArray.length(); i++) {
                    Map<String, String> reward = new HashMap<>();
                    JSONObject requestItem = requestsArray.getJSONObject(i);
                    int rewardId = requestItem.getInt("reward_id");
                    System.out.println(Integer.toString(rewardId));
                    reward.put("line 1", Integer.toString(rewardId));
                    int pending = requestItem.getInt("pending");
                    if(requestItem.isNull("approved")){
                        reward.put("line 2", "pending");
                    }else {
                       int status = requestItem.getInt("approved");
                       if (status == 1) {
                           reward.put("line 2", "approved");
                       } else if (status == 0) {
                           reward.put("line 2", "rejected");
                       }
                    }
                    data.add(reward);
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

        ListView listView = view.findViewById(R.id.rewardsList);
        listView.setAdapter(adapter);

        return view;
    }

}
