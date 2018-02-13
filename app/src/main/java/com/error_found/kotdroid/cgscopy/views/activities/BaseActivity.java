package com.error_found.kotdroid.cgscopy.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.error_found.kotdroid.cgscopy.views.interfaces.BaseView;
import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by user12 on 7/2/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {


    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        Fresco.initialize(this);

        unbinder = ButterKnife.bind(this);
        init();
    }

    protected abstract void init();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

    public abstract int getLayoutId();

    @Override
    public void showMessage(int resId, String string, boolean isShowSnackbarMessage) {
        if (!isShowSnackbarMessage) {
            Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        }
    }
}
