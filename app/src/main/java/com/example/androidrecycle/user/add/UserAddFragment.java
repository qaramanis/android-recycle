package com.example.androidrecycle.user.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.androidrecycle.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class UserAddFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_add, container,false);

        TabLayout tabLayout = view.findViewById(R.id.tabs);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);


        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2,
            (tab, position) -> {
                switch (position){
                    case 1:
                        tab.setText("Glass");
                        break;
                    case 2:
                        tab.setText("Aluminum");
                        break;
                    case 3:
                        tab.setText("Other");
                        break;
                    default:
                        tab.setText("Paper");
                        break;
                }
            }
        ).attach();
        return  view;
    }

}