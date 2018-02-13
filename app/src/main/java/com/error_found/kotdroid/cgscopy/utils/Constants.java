package com.error_found.kotdroid.cgscopy.utils;

import android.os.Environment;

/**
 * Created by Mukesh on 05-05-2016.
 */
public class Constants {

    private static final String APP_NAME = "Phone Book";

    // Date Time Format Constants
    public static final String DATE_FORMAT_DISPLAY = "dd MMM, yyyy";
    public static final String DATE_FORMAT_SERVER = "yyyy-MM-d";

    // Media Constants
    public static final String LOCAL_STORAGE_BASE_PATH_FOR_MEDIA = Environment
            .getExternalStorageDirectory() + "/" + APP_NAME;
    public static final String LOCAL_STORAGE_BASE_PATH_FOR_POSTED_IMAGES =
            LOCAL_STORAGE_BASE_PATH_FOR_MEDIA
                    + "/Post/Images/";
    public static final String LOCAL_STORAGE_BASE_PATH_FOR_USER_PHOTOS =
            LOCAL_STORAGE_BASE_PATH_FOR_MEDIA + "/User/Photos/";
}
