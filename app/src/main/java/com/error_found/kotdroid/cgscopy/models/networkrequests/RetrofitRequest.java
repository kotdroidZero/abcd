package com.error_found.kotdroid.cgscopy.models.networkrequests;


/**
 * Created by Mukesh on 12/12/2016.
 */

public class RetrofitRequest {

   /* public static PojoNetworkResponse checkForResponseCode(int code) {
        switch (code) {
            case 200:
                return new PojoNetworkResponse(true, false);
            case 403:
                return new PojoNetworkResponse(false, true);
            default:
                return new PojoNetworkResponse(false, false);
        }
    }

    public static String getErrorMessage(ResponseBody responseBody) {
        String errorMessage = "";
        try {
            Converter<ResponseBody, Error> errorConverter = RestClient.getRetrofitInstance()
                    .responseBodyConverter(Error.class, new Annotation[0]);
            return errorConverter.convert(responseBody).getError_description();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorMessage;
    }

    public static int getRetrofitError(Throwable t) {
        int errorMessageResId;
        if (t instanceof IOException) {
            errorMessageResId = R.string.no_internet;
        } else {
            errorMessageResId = R.string.retrofit_failure;
        }
        return errorMessageResId;
    }*/

}
