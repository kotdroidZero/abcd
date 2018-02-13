package com.error_found.kotdroid.cgscopy.views.activities;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.error_found.kotdroid.cgscopy.R;

import butterknife.BindView;
import butterknife.BindViews;

public class YourGuestActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


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

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_your_guest;
    }
}
