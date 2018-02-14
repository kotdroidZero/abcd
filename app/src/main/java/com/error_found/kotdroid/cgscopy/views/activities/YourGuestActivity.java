package com.error_found.kotdroid.cgscopy.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.error_found.kotdroid.cgscopy.R;
import com.error_found.kotdroid.cgscopy.views.adapters.TabAdapter;
import com.error_found.kotdroid.cgscopy.views.fragments.GuestsFragment;
import com.error_found.kotdroid.cgscopy.views.utils.Tab;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class YourGuestActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    TabAdapter mTabdapter;
    @BindView(R.id.fab_add_guest)
    FloatingActionButton fabAddGuest;
    PopupWindow popupWindow;


    @Override
    public void nameError(String err) {

    }

    @Override
    public void contactError(String err) {

    }

    @Override
    public void sessionIdErr() {

    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    protected void init() {
        List<Tab> tabList = new ArrayList<>();

        Bundle bundle = new Bundle();
        bundle.putString("type", "Expected");
        GuestsFragment mExpectedGuestFragment = GuestsFragment.newInstance(
                GuestsFragment.GUEST_TYPE_EXPECTED);
        GuestsFragment mPermanentGuestFragment = GuestsFragment.newInstance(
                GuestsFragment.GUEST_TYPE_PERMANENT);
        GuestsFragment mBlockedGuestFragment = GuestsFragment.newInstance(
                GuestsFragment.GUEST_TYPE_BLOCKED);
        tabList.add(new Tab(mExpectedGuestFragment, "EXPECTED"));
        tabList.add(new Tab(mPermanentGuestFragment, "PERMANENT"));
        tabList.add(new Tab(mBlockedGuestFragment, "BLOCKED"));

        mTabdapter = new TabAdapter(getFragmentManager(), tabList);
        mViewPager.setAdapter(mTabdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_your_guest;
    }

    @OnClick(R.id.fab_add_guest)
    public void addGuest(View view) {
//        openPopupWindow(view);
    }

    private void openPopupWindow(View view) {
        LayoutInflater inflater = (LayoutInflater) YourGuestActivity.this.getSystemService(
                LAYOUT_INFLATER_SERVICE);

        View laout = inflater.inflate(R.layout.popup_add_guest, (ViewGroup)
                findViewById(R.id.root_view_group));

        popupWindow = new PopupWindow(laout, 300, 400, true);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }


}
