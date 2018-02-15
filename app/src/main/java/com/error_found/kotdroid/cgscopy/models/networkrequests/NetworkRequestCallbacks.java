package com.error_found.kotdroid.cgscopy.models.networkrequests;

import com.error_found.kotdroid.cgscopy.models.pojos.PojoLogin;

import java.util.List;

import retrofit2.Response;

/**
 * Created by Mukesh on 12/12/2016.
 */

public interface NetworkRequestCallbacks {

    void onSuccess(List<PojoLogin> loginList);
    void onSuccess(Response<?> response);
    void onError(Throwable t);
}
