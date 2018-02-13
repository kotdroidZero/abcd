package com.error_found.kotdroid.cgscopy.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v13.app.FragmentCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.error_found.kotdroid.cgscopy.R;


/**
 * Created by user12 on 9/2/18.
 */

public class MarshMallowPermissions {

    public static final int READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 1;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 2;
    public static final int REQUEST_CODE_READ_CONTACTS_PERMISSION = 874;

    private Fragment mFragment;
    private Activity mActivity;

    public MarshMallowPermissions(Fragment fragment) {
        mFragment = fragment;
        mActivity = mFragment.getActivity();
    }

    public boolean isPermissionGrantedForReadExtStorage() {
        return ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissionForReadExtStorage() {
        if (FragmentCompat.shouldShowRequestPermissionRationale(mFragment,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            showAlertDialog(mActivity.getString(R.string.storage_permission_needed),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FragmentCompat.requestPermissions(mFragment,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
                        }
                    }, null);
        } else {
            FragmentCompat.requestPermissions(mFragment,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
        }
    }

    public boolean isPermissionGrantedForCamera() {
        return ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissionForCamera() {
        if (FragmentCompat.shouldShowRequestPermissionRationale(mFragment,
                Manifest.permission.CAMERA)) {
            showAlertDialog(mActivity.getString(R.string.camera_permission_needed),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FragmentCompat.requestPermissions(mFragment,
                                    new String[]{Manifest.permission.CAMERA},
                                    CAMERA_PERMISSION_REQUEST_CODE);
                        }
                    }, null);
        } else {
            FragmentCompat.requestPermissions(mFragment,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    public MarshMallowPermissions(Activity activity) {
        mActivity = activity;
    }

    public boolean isPermissionGrantedForReadContacts() {
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.READ_CONTACTS);
    }

    public void requestPermissionForReadContacts() {
        if (FragmentCompat.shouldShowRequestPermissionRationale(mFragment,
                Manifest.permission.READ_CONTACTS)) {
            showAlertDialog(mActivity.getString(R.string.reading_contacts_permission_required),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FragmentCompat.requestPermissions(mFragment,
                                    new String[]{Manifest.permission.READ_CONTACTS},
                                    REQUEST_CODE_READ_CONTACTS_PERMISSION);
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
        } else {
            FragmentCompat.requestPermissions(mFragment,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_CODE_READ_CONTACTS_PERMISSION);
        }
    }

    private void showAlertDialog(String message,
                                 DialogInterface.OnClickListener okListener,
                                 DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(mActivity)
                .setMessage(message)
                .setPositiveButton(mActivity.getString(R.string.ok), okListener)
                .setNegativeButton(mActivity.getString(R.string.cancel), cancelListener)
                .create()
                .show();
    }

}
