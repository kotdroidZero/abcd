package com.error_found.kotdroid.cgscopy.views.fragments;


import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;

import com.error_found.kotdroid.cgscopy.BuildConfig;
import com.error_found.kotdroid.cgscopy.R;
import com.error_found.kotdroid.cgscopy.utils.GeneralFunctions;
import com.error_found.kotdroid.cgscopy.utils.GetSampledImage;
import com.error_found.kotdroid.cgscopy.utils.MarshMallowPermissions;

import java.io.File;
import java.util.List;

/**
 * Created by Mukesh on 26/10/2016.
 */

public abstract class BasePictureOptionsFragment extends BaseFragment implements
        GetSampledImage.SampledImageAsyncResp {

    public static final int GALLERY_REQUEST = 234;
    public static final int CAMERA_REQUEST = 23;
    private MarshMallowPermissions mMarshMallowPermissions;
    private String picturePath, imagesDirectory;
    private boolean isCameraOptionSelected;

    @Override
    public void init() {
        setData();
    }

    public void showPictureOptionsDialog(final String imagesDirectory) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setItems(getResources().getStringArray(R.array.media_options),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        switch (position) {
                            case 0:
                                checkForPermissions(false, imagesDirectory);
                                break;
                            case 1:
                                checkForPermissions(true, imagesDirectory);
                                break;
                            default:
                                break;
                        }
                    }
                });
        alertDialogBuilder.show();
    }

    private void checkForPermissions(boolean isCameraOptionSelected, String imagesDirectory) {
        this.isCameraOptionSelected = isCameraOptionSelected;
        this.imagesDirectory = imagesDirectory;

        if (null == mMarshMallowPermissions) {
            mMarshMallowPermissions =
                    new MarshMallowPermissions(BasePictureOptionsFragment.this);
        }

        if (mMarshMallowPermissions
                .isPermissionGrantedForReadExtStorage()) {
            if (isCameraOptionSelected) {
                startCameraIntent();
            } else {
                openGallery();
            }
        } else {
            mMarshMallowPermissions.requestPermissionForReadExtStorage();
        }
    }

    private void openGallery() {
        startActivityForResult(new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST);
    }

    private void startCameraIntent() {
        Intent takePictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File file = null;
        try {
            file = GeneralFunctions.setUpImageFile(imagesDirectory);
            picturePath = file.getAbsolutePath();

            Uri outputUri = FileProvider
                    .getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider", file);
//            Uri outputUri=Uri.fromFile(file);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ClipData clip = ClipData.newUri(getActivity().getContentResolver(),
                        "A photo", outputUri);

                takePictureIntent.setClipData(clip);
                takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                List<ResolveInfo> resInfoList = getActivity().getPackageManager()
                        .queryIntentActivities(takePictureIntent,
                                PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    getActivity().grantUriPermission(packageName, outputUri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            picturePath = null;
        }
        startActivityForResult(takePictureIntent, CAMERA_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case MarshMallowPermissions.READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (isCameraOptionSelected) {
                        startCameraIntent();
                    } else {
                        openGallery();
                    }
                } else {
                    showMessage(R.string.enable_storage_permission, null, false);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && (requestCode == GALLERY_REQUEST ||
                requestCode == CAMERA_REQUEST)) {
            boolean isGalleryImage = false;
            if (requestCode == GALLERY_REQUEST) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                cursor.close();
                isGalleryImage = true;
            }

            new GetSampledImage(this).execute(picturePath, imagesDirectory,
                    String.valueOf(isGalleryImage),
                    String.valueOf((int) getResources()
                            .getDimension(R.dimen.image_downsample_size)));
        }
    }

    @Override
    public void onSampledImageAsyncPostExecute(File file) {
        onGettingImageFile(file);
    }


    public abstract void setData();

    public abstract void onGettingImageFile(File file);
}
