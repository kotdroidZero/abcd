package com.error_found.kotdroid.cgscopy.views.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.error_found.kotdroid.cgscopy.views.interfaces.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by user12 on 7/2/18.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public abstract int getLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public Context getActivityContext() {

        return getActivity();
    }


    @Override
    public void showMessage(int resId, String string, boolean isShowSnackbarMessage) {
        if (!isShowSnackbarMessage) {
            Toast.makeText(getActivityContext(), string, Toast.LENGTH_SHORT).show();
        }
    }

    public abstract void init();
}
