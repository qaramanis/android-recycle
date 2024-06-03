package com.example.androidrecycle.ui.rewards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidrecycle.ImageAdapter;
import com.example.androidrecycle.R;
import com.example.androidrecycle.databinding.FragmentRewardsBinding;
import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RewardsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewards, container, false);
        RecyclerView recyclerView = getView().findViewById(R.id.recycler);
        List<Integer> imageList = Arrays.asList(
                R.drawable.ic_account,
                R.drawable.ic_rewards
        );

        ImageAdapter adapter = new ImageAdapter(RewardsFragment.this, imageList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new CarouselLayoutManager());
        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onClick(ImageView imageView, String path) {

            }
        });
        return view;
    }

}