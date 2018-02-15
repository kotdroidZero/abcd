package com.error_found.kotdroid.cgscopy.views.interfaces;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by user12 on 15/2/18.
 */

public interface BaseRecyclerView extends BaseView{
    void showSwipeRefreshLoader();

    void hideSwipeRefreshLoader();

    void showNoDataText(int resId, String message);

    void hideNoDataText();

    RecyclerView.Adapter getRecyclerViewAdapter();

    void updateRecyclerViewData(List<?> dataList);
}
