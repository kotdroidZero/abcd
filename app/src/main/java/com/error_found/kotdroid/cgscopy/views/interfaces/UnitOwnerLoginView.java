package com.error_found.kotdroid.cgscopy.views.interfaces;

import com.error_found.kotdroid.cgscopy.models.pojos.PojoLogin;

/**
 * Created by user12 on 12/2/18.
 */

public interface UnitOwnerLoginView extends BaseView {
    void emailErr();

    void deviceTypeErr();

    void passwordErr();

    void loginSuccess(PojoLogin pojoUserLogin);


}
