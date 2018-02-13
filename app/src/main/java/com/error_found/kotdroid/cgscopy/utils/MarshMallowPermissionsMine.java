package com.error_found.kotdroid.cgscopy.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;

/**
 * Created by user12 on 8/2/18.
 */

public class MarshMallowPermissionsMine {

    Activity activity;
    public static final int REQUEST_READ_STORAGE_PERMISSSION=100;
    public static final int REQUEST_WRITE_STORAGE_PERMISSSION=200;
    public static final int REQUEST_CAMERA_PERMISSSION=300;

    public MarshMallowPermissionsMine(Activity activity) {
        this.activity = activity;
    }

    public  boolean isPermissionAllowed(String permission,
                                        int requestCode)
    {
        if (isAboveLollipop())
        {
            if (ActivityCompat.checkSelfPermission(activity,permission)== PackageManager.PERMISSION_GRANTED)
            {
                return true;
            }
        }
        return false;
    }

    private  boolean isAboveLollipop() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    public void requestPermission(final String[] permission, final int requestCode)
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,permission[0]))
        {
            Snackbar.make(activity.findViewById(android.R.id.content),"Permission required to click" +
                    " your image",Snackbar.LENGTH_INDEFINITE).setAction("ENABLE", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.requestPermissions(permission,requestCode);
                }
            }).show();
        }
        else
        {
            activity.requestPermissions(permission,requestCode);
        }
    }

}
