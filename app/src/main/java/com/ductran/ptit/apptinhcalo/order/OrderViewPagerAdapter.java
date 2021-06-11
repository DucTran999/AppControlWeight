package com.ductran.ptit.apptinhcalo.order;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class OrderViewPagerAdapter extends FragmentStatePagerAdapter{
    public OrderViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TabWhey();
            case 1:
                return new TabMass();
            case 2:
                return new TabBcaa();
            default:
                return new TabWhey();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    //xu ly tab
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Whey";
            case 1:
                return "MASS";
            case 2:
                return "BCAA";
            default:
                return "Whey";
        }
    }
}
