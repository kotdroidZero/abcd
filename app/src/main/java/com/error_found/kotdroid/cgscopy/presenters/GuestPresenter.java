package com.error_found.kotdroid.cgscopy.presenters;

import com.error_found.kotdroid.cgscopy.models.interactors.GuestsInteractor;
import com.error_found.kotdroid.cgscopy.models.networkrequests.NetworkRequestCallbacks;
import com.error_found.kotdroid.cgscopy.models.pojos.PojoLogin;
import com.error_found.kotdroid.cgscopy.views.interfaces.GuestsView;

import java.util.List;

import retrofit2.Response;

/**
 * Created by user12 on 14/2/18.
 */

public class GuestPresenter {
    GuestsView mGuestsView;
    GuestsInteractor mGuestsInteractor;

    public GuestPresenter(GuestsView mGuestsView) {
        this.mGuestsView = mGuestsView;
        mGuestsInteractor = new GuestsInteractor();
    }

    public void getGuests(String sessionId, String status) {
        if (sessionId.isEmpty()) {
            mGuestsView.sessionIdErr();
        } else {
            mGuestsInteractor.getGuestsByRxJava(sessionId, status, new NetworkRequestCallbacks() {
                @Override
                public void onSuccess(List<PojoLogin> loginList) {

                }

                @Override
                public void onSuccess(Response<?> response) {
                    mGuestsView.success(response);
                }

                @Override
                public void onError(Throwable t) {
                    mGuestsView.error(t);
                }
            });
        }
    }

    public void moveGuest(String sessionId, int guestId, final int adapterPosition, final int guestType) {
        mGuestsInteractor.moveGuest(sessionId, guestId, guestType, new NetworkRequestCallbacks() {
            @Override
            public void onSuccess(List<PojoLogin> loginList) {

            }

            @Override
            public void onSuccess(Response<?> response) {
                mGuestsView.guestTypeChanged(response);
                mGuestsView.reloadList(adapterPosition, guestType);
            }

            @Override
            public void onError(Throwable t) {
                mGuestsView.error(t);
            }
        });
    }


    public void fetchGuestList(String sessionId, int guestType) {
        mGuestsInteractor.getGuestsByRxJava(sessionId, guestType + "", new NetworkRequestCallbacks() {
            @Override
            public void onSuccess(List<PojoLogin> loginList) {

            }

            @Override
            public void onSuccess(Response<?> response) {
                mGuestsView.success(response);
            }

            @Override
            public void onError(Throwable t) {
                mGuestsView.error(t);
            }
        });


    }
}
