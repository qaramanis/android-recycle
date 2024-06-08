package com.example.androidrecycle.admin.requests;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import static androidx.core.app.ActivityCompat.recreate;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidrecycle.OkHttpHandler;
import com.example.androidrecycle.PopupHandler;
import com.example.androidrecycle.R;
import com.example.androidrecycle.User;
import com.example.androidrecycle.user.account.UserAccountFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendingRequestsFragment extends Fragment {
    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<Integer> userIds = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_pending_requests, container, false);

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
                    if(requestItem.isNull("approved")){
                        reward.put("line 1", "Item ID: " + rewardId);
                        reward.put("line 2", "Requested by user " + userId);
                        ids.add(id);
                        userIds.add(userId);
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

        SimpleAdapter adapter = new SimpleAdapter(getContext(), data,
                android.R.layout.simple_list_item_2,
                new String[] {"line 1", "line 2"},
                new int[] {android.R.id.text1, android.R.id.text2});

        ListView listView = view.findViewById(R.id.rewardsList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showRequestPopup(position);
            }
        });
        return view;
    }


    public void showRequestPopup(int position){
        LayoutInflater inflater = (LayoutInflater) requireContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View  popupView = inflater.inflate(R.layout.popup_request_info, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setElevation(30);

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        PopupHandler.dimBehind(popupWindow);


        OkHttpHandler okHttpHandler = new OkHttpHandler();
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });

        Button acceptBtn = popupView.findViewById(R.id.acceptBtn);
        acceptBtn.setOnClickListener(v -> {
            JSONObject response;
            try {
                response = okHttpHandler.approveRejectRequest(ids.get(position), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Response: " + response);
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            try {
                fragmentTransaction.replace(R.id.AdminFragmentContainerView, PendingRequestsFragment.class.newInstance());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (java.lang.InstantiationException e) {
                throw new RuntimeException(e);
            }
            fragmentTransaction.commit();
            popupWindow.dismiss();
        });

        Button rejectBtn = popupView.findViewById(R.id.rejectBtn);
        rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                JSONObject response;
                try {
                    response = okHttpHandler.approveRejectRequest(ids.get(position), false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Response: " + response);
                try {
                    response = okHttpHandler.subtractPoints(userIds.get(position), -100);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Response: " + response);

                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                try {
                    fragmentTransaction.replace(R.id.AdminFragmentContainerView, PendingRequestsFragment.class.newInstance());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (java.lang.InstantiationException e) {
                    throw new RuntimeException(e);
                }
                fragmentTransaction.commit();
                popupWindow.dismiss();
            }
        });
    }
}
