package com.error_found.kotdroid.cgscopy.models.interactors;

import android.support.annotation.NonNull;

import com.error_found.kotdroid.cgscopy.models.networkrequests.API;
import com.error_found.kotdroid.cgscopy.models.networkrequests.NetworkRequestCallbacks;
import com.error_found.kotdroid.cgscopy.models.networkrequests.RestClient;
import com.error_found.kotdroid.cgscopy.models.pojos.PojoLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user12 on 12/2/18.
 */

public class UnitOwnerLoginInteractor {

    public void loginToNetwork(String email, String password, String deviceType,
                               final NetworkRequestCallbacks networkRequestCallbacks)
    {
        API api= RestClient.get();
        Call<PojoLogin> call=api.loginUser(email,password,deviceType);

        call.enqueue(new Callback<PojoLogin>() {
            @Override
            public void onResponse(@NonNull Call<PojoLogin> call, @NonNull Response<PojoLogin> response) {
                if (response.isSuccessful())
                {
                    networkRequestCallbacks.onSuccess(response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PojoLogin> call, Throwable t) {
                    networkRequestCallbacks.onError(t);
            }
        });
    }
}
