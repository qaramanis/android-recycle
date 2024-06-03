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

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PaperFragment();
            case 1:
                return new GlassFragment();
            case 2:
                return new AluminumFragment();
            case 3:
                return new OtherFragment();
            default:
                return new PaperFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public String getTabTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
