package com.example.androidrecycle.user.rewards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidrecycle.R;
import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.Arrays;
import java.util.List;

public class UserRewardsFragment extends Fragment implements com.example.androidrecycle.ui.rewards.CarouselAdapter.OnItemClickListener{

    private List<Integer> imageList;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_rewards, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        imageList = Arrays.asList(
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image,
                R.drawable.ic_image
                );

        com.example.androidrecycle.ui.rewards.CarouselAdapter adapter = new com.example.androidrecycle.ui.rewards.CarouselAdapter(requireContext(), imageList);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new CarouselLayoutManager());
        return view;
    }


    @Override
    public void onItemClick(int position) {
        int clickedImageRes = imageList.get(position);
        //TODO add popup to confirm request for reward
        Toast.makeText(requireContext(), "Clicked item at position: " + position, Toast.LENGTH_SHORT).show();
    }
}