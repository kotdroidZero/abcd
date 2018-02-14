package com.error_found.kotdroid.cgscopy.views.interfaces;

import retrofit2.Response;

/**
 * Created by user12 on 14/2/18.
 */

public interface GuestsView extends BaseView {
    void error(Throwable t);
    void reloadList(int adapterPosition, int guestType);
    void success(Response<?> response);
    void guestTypeChanged(Response<?> response);

}
