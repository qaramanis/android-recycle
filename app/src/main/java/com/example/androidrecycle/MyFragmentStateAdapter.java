package com.example.androidrecycle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.androidrecycle.ui.add.AddFragment;
import com.example.androidrecycle.ui.add.AluminumFragment;
import com.example.androidrecycle.ui.add.GlassFragment;
import com.example.androidrecycle.ui.add.OtherFragment;
import com.example.androidrecycle.ui.add.PaperFragment;

public class MyFragmentStateAdapter extends FragmentStateAdapter {

    public MyFragmentStateAdapter(@NonNull AddFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       Fragment fragment = null;
       switch(position) {
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

}
