package com.funshow.toolbar_tablayout_viewpage2_navigation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentViewPagerAdapter extends FragmentStateAdapter {
    //初始化
    public FragmentViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new FragmentA();
        }
        else if (position == 1){
            return new FragmentB();
        }
        else {
            return new FragmentC();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
