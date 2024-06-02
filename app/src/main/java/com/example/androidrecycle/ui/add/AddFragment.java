package com.example.androidrecycle.ui.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.androidrecycle.R;
import com.example.androidrecycle.MyFragmentStateAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AddFragment extends Fragment{

    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_add, container,false);
//
//        viewPager = requireView().findViewById(R.id.view_pager);
//        tabLayout = requireView().findViewById(R.id.tabs);
//
//        MyFragmentStateAdapter myFragmentStateAdapter = new MyFragmentStateAdapter(this);
//        viewPager.setAdapter(myFragmentStateAdapter);
//
//
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                // Optional: handle tab unselect event
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                // Optional: handle tab reselect event
//            }
//        });
//        return view;
//    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager = getView().findViewById(R.id.view_pager);
        tabLayout = getView().findViewById(R.id.tabs);

        MyFragmentStateAdapter myFragmentStateAdapter = new MyFragmentStateAdapter(this);
        viewPager.setAdapter(myFragmentStateAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Optional: handle tab unselect event
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Optional: handle tab reselect event
            }
        });

    }

}