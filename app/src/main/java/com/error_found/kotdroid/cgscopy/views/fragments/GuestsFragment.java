package com.error_found.kotdroid.cgscopy.views.fragments;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.error_found.kotdroid.cgscopy.R;
import com.error_found.kotdroid.cgscopy.models.pojos.PojoLogin;
import com.error_found.kotdroid.cgscopy.presenters.GuestPresenter;
import com.error_found.kotdroid.cgscopy.views.adapters.GuestAdapter;
import com.error_found.kotdroid.cgscopy.views.interfaces.GuestsView;
import com.error_found.kotdroid.cgscopy.views.utils.Constants;

import java.util.List;

import butterknife.BindView;
import retrofit2.Response;

/**
 * Created by user12 on 14/2/18.
 */

public class GuestsFragment extends BaseFragment implements GuestsView, GuestAdapter.GuestActionCallBack {

    public static final int GUEST_TYPE_EXPECTED = 1;
    public static final int GUEST_TYPE_PERMANENT = 2;
    public static final int GUEST_TYPE_BLOCKED = -1;
    public static final int GUEST_TYPE_DELETE = 0;
    public static final String LOCAL_INTEND_GUEST_LIST_RELOAD = "reloadGustList";
    public static final String BUNDLE_EXTRA_GUEST_TYPE = "BUNDLE_EXTRA_GUEST_TYPE";
    int guestType;
    GuestPresenter mGuestPresenter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    GuestAdapter mGuestAdapter;
    private ProgressDialog progressDialog;

    public static GuestsFragment newInstance(int gusetType) {
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_EXTRA_GUEST_TYPE, gusetType);
        GuestsFragment fragment = new GuestsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

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
    public int getLayoutId() {
        return R.layout.fragment_guests;
    }

    @Override
    public void init() {
        progressDialog = ProgressDialog.show(getActivityContext(), null,
                "loading guests", false);
        mGuestPresenter = new GuestPresenter(this);
        guestType = getArguments().getInt(BUNDLE_EXTRA_GUEST_TYPE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mGuestPresenter.getGuests(Constants.SESSION_ID, String.valueOf(guestType));
            }
        },4000);
        mGuestAdapter = new GuestAdapter(this, guestType);
        LinearLayoutManager llm = new LinearLayoutManager(getActivityContext(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mGuestAdapter);




        //registering broadcast receivers
        LocalBroadcastManager.getInstance(getActivityContext()).registerReceiver(message,
                new IntentFilter(LOCAL_INTEND_GUEST_LIST_RELOAD));
    }

    @Override
    public void error(Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(getActivityContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void reloadList(int adapterPosition, int guestType) {
        mGuestAdapter.removeGuest(adapterPosition);

        sendBroadcast(guestType);
    }


    @Override
    public void success(Response<?> response) {

        List<PojoLogin> loginList = (List<PojoLogin>) response.body();
        for (PojoLogin login : loginList) {
            mGuestAdapter.updateList(login);
        }
        progressDialog.dismiss();
    }

    @Override
    public void success(List<PojoLogin> list) {
        for (PojoLogin login : list) {
            mGuestAdapter.updateList(login);
        }
        progressDialog.dismiss();
    }

    @Override
    public void guestTypeChanged(Response<?> response) {
        progressDialog.dismiss();
        Toast.makeText(getActivityContext(), ((PojoLogin) response.body()).message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void changeGuestType(int guestId, int adapterPosition, int guestType) {
        progressDialog = ProgressDialog.show(getActivityContext(), null,
                "loading guests", false);
        mGuestPresenter.moveGuest(Constants.SESSION_ID, guestId, adapterPosition, guestType);

    }


    private BroadcastReceiver message = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (null != context && null != intent) {
                if (intent.getIntExtra(BUNDLE_EXTRA_GUEST_TYPE, 6) == guestType) {
                    mGuestPresenter.fetchGuestList(Constants.SESSION_ID, guestType);
                }
            }
            progressDialog.dismiss();
        }
    };

    private void sendBroadcast(int guestType) {
        Intent intent = new Intent(LOCAL_INTEND_GUEST_LIST_RELOAD);
        intent.putExtra(BUNDLE_EXTRA_GUEST_TYPE, guestType);
        LocalBroadcastManager.getInstance(getActivityContext()).sendBroadcast(intent);

    }

}
