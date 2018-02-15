package com.error_found.kotdroid.cgscopy.presenters;

import com.error_found.kotdroid.cgscopy.models.interactors.UnitOwnerLoginInteractor;
import com.error_found.kotdroid.cgscopy.models.networkrequests.NetworkRequestCallbacks;
import com.error_found.kotdroid.cgscopy.models.pojos.PojoLogin;
import com.error_found.kotdroid.cgscopy.views.interfaces.UnitOwnerLoginView;


import java.util.List;

import retrofit2.Response;

/**
 * Created by user12 on 12/2/18.
 */

public class UnitOwnerLoginPresenter {
    private UnitOwnerLoginView unitOwnerLoginView;
    private UnitOwnerLoginInteractor unitOwnerLoginInteractor;

    public UnitOwnerLoginPresenter(UnitOwnerLoginView unitOwnerLoginView) {
        this.unitOwnerLoginView = unitOwnerLoginView;
        unitOwnerLoginInteractor = new UnitOwnerLoginInteractor();
    }

    public void login(String email, String password, String deviceType) {
        if (email.isEmpty()) {
            unitOwnerLoginView.emailErr();
        } else if (password.isEmpty()) {
            unitOwnerLoginView.passwordErr();
        } else if (!deviceType.equalsIgnoreCase("android") ||
                deviceType.equalsIgnoreCase("iphone")) {
            unitOwnerLoginView.deviceTypeErr();
        } else {
            unitOwnerLoginInteractor.loginToNetwork(email, password, deviceType,
                    new NetworkRequestCallbacks() {
                        @Override
                        public void onSuccess(List<PojoLogin> loginList) {

                        }

                        @Override
                        public void onSuccess(Response<?> response) {
                            PojoLogin userLogin = (PojoLogin) response.body();

                            unitOwnerLoginView.loginSuccess(userLogin);

                        }

                        @Override
                        public void onError(Throwable t) {
                            unitOwnerLoginView.showMessage(0,t.getMessage(),
                                    false);
                        }
                    });
        }
    }
}
