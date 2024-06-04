package com.example.androidrecycle.user.add;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.androidrecycle.user.add.AluminumFragment;
import com.example.androidrecycle.user.add.GlassFragment;
import com.example.androidrecycle.user.add.OtherFragment;
import com.example.androidrecycle.user.add.PaperFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
            if(position == 0)
                return new PaperFragment();
            else if (position == 1)
                return new GlassFragment();
            else if (position == 2)
                return new AluminumFragment();
            else if (position == 3)
                return new OtherFragment();
            else
                return new PaperFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
