package com.error_found.kotdroid.cgscopy.models.networkrequest;

import retrofit2.Response;

/**
 * Created by Mukesh on 12/12/2016.
 */

public interface NetworkRequestCallbacks {

    void onSuccess(Response<?> response);
    void onError(Throwable t);
}
