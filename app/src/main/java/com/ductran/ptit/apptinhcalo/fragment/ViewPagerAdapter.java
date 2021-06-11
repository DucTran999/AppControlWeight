package com.ductran.ptit.apptinhcalo.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InfoFragment();
            case 1:
                return new NewsFragment();
            case 2:
                return new AlarmFragment();
            case 3:
                return new ShoppingFragment();
            default:
                return new InfoFragment();
        }
    }
    @Override
    public int getCount() {
        return 4;
    }
}
