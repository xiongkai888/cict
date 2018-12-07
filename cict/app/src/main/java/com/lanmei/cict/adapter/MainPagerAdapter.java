package com.lanmei.cict.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lanmei.cict.ui.about.AboutFragment;
import com.lanmei.cict.ui.home.HomeFragment;
import com.lanmei.cict.ui.manual.ManualFragment;
import com.lanmei.cict.ui.me.MeFragment;


public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();//Home
            case 1:
                return new ManualFragment();//Manual
            case 2:
                return new AboutFragment();//About
            case 3:
                return new MeFragment();//Me
        }
        return new HomeFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

}
