package com.ductran.ptit.apptinhcalo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ductran.ptit.apptinhcalo.R;
import com.ductran.ptit.apptinhcalo.order.OrderViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class ShoppingFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    View mView;

    public ShoppingFragment() {

    }
    //ham lay du lieu.

//xu ly tab
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_shopping, container, false);

        //anh xa
        tabLayout = mView.findViewById(R.id.tab_layout);
        viewPager = mView.findViewById(R.id.order_viewpager);

        //Tao adapter: get child
        OrderViewPagerAdapter adapter = new OrderViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        //truyen viewpager vao
        tabLayout.setupWithViewPager(viewPager);

        return mView;

    }
}