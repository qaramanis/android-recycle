package com.example.androidrecycle.user.add;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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
            else
                return new PaperFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
