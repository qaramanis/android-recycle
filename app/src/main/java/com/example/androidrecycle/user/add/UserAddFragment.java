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

//        ViewPagerAdapter adapter = new ViewPagerAdapter(requireActivity());
//        adapter.addFragment(new PaperFragment(), "Paper");
//        adapter.addFragment(new GlassFragment(), "Glass");
//        adapter.addFragment(new AluminumFragment(), "Aluminum");
//        adapter.addFragment(new OtherFragment(), "Other");


        viewPager2.setAdapter(new com.example.androidrecycle.user.add.ViewPagerAdapter(requireActivity()));
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText("Item " + (position + 1))
        ).attach();
        return  view;
    }

}