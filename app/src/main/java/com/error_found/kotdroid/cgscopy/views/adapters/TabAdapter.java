package com.error_found.kotdroid.cgscopy.views.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.error_found.kotdroid.cgscopy.views.utils.Tab;

import java.util.List;

/**
 * Created by user12 on 14/2/18.
 */

public class TabAdapter extends FragmentPagerAdapter {

    List<Tab> tabList;
    public TabAdapter(FragmentManager fm,List<Tab> tabList) {
        super(fm);
        this.tabList=tabList;
    }

    @Override
    public Fragment getItem(int position) {
        return tabList.get(position).fragment;
    }

    @Override
    public int getCount() {
        return tabList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position).title;
    }
}
