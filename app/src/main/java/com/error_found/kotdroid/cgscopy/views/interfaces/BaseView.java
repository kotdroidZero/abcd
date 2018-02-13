package com.error_found.kotdroid.cgscopy.views.interfaces;

import android.content.Context;

/**
 * Created by user12 on 7/2/18.
 */

public interface BaseView {
    public void nameError(String err);

    public void contactError(String err);

    void sessionIdErr();
    public Context getActivityContext();

    void showMessage(int resId, String string, boolean isShowSnackbarMessage);

}
