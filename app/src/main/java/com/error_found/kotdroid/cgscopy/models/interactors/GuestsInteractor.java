package com.error_found.kotdroid.cgscopy.models.interactors;

import com.error_found.kotdroid.cgscopy.models.networkrequests.API;
import com.error_found.kotdroid.cgscopy.models.networkrequests.NetworkRequestCallbacks;
import com.error_found.kotdroid.cgscopy.models.networkrequests.RestClient;
import com.error_found.kotdroid.cgscopy.models.pojos.PojoLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user12 on 14/2/18.
 */

public class GuestsInteractor {

    public void getGuests(String sessionId, String status, final NetworkRequestCallbacks
            requestCallbacks) {
        API api = RestClient.get();

        Call<List<PojoLogin>> call = api.getGuests(sessionId, status);
        call.enqueue(new Callback<List<PojoLogin>>() {
            @Override
            public void onResponse(Call<List<PojoLogin>> call, Response<List<PojoLogin>> response) {
                if (response.isSuccessful()) {
                    requestCallbacks.onSuccess(response);
                }
            }

            @Override
            public void onFailure(Call<List<PojoLogin>> call, Throwable t) {
                requestCallbacks.onError(t);
            }
        });

    }

    public void moveGuest(String sessionId, int guestId,
                          int finalStatus, final NetworkRequestCallbacks requestCallbacks) {
        API api = RestClient.get();
        Call<PojoLogin> call = api.changeGuestStatus(sessionId,guestId,finalStatus);
        call.enqueue(new Callback<PojoLogin>() {
            @Override
            public void onResponse(Call<PojoLogin> call, Response<PojoLogin> response) {
                if (response.isSuccessful())
                {
                    requestCallbacks.onSuccess(response);
                }
            }

            @Override
            public void onFailure(Call<PojoLogin> call, Throwable t) {
                requestCallbacks.onError(t);
            }
        });
    }
}
