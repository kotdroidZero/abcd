package com.error_found.kotdroid.cgscopy.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by Mukesh on 05-05-2016.
 */
public class GeneralFunctions {

    private static final String alphaNumChars =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static final String JPEG_FILE_PREFIX = "IMG_";
    public static final String JPEG_FILE_SUFFIX = ".jpg";

    public static void replaceFragment(FragmentManager fragmentManager, int enterAnim, int exitAnim,
                                       int popEnterAnim, int popExitAnim, Fragment fragment,
                                       String tag, int containerId) {
        fragmentManager.beginTransaction().setCustomAnimations(enterAnim, exitAnim, popEnterAnim,
                popExitAnim).replace(containerId, fragment, tag).commit();
    }

    public static void replaceFragmentWithBackStack(FragmentManager fragmentManager,
                                                    int enterAnim, int exitAnim,
                                                    int popEnterAnim, int popExitAnim,
                                                    Fragment fragment, String tag,
                                                    int containerId) {
        fragmentManager.beginTransaction().setCustomAnimations(enterAnim, exitAnim, popEnterAnim,
                popExitAnim).replace(containerId, fragment, null != tag ? tag : "tag")
                .addToBackStack(null).commit();
    }

    public static void addFragment(FragmentManager fragmentManager, int enterAnim, int exitAnim,
                                   int popEnterAnim, int popExitAnim, Fragment fragment,
                                   String tag, int containerId) {
        fragmentManager.beginTransaction().setCustomAnimations(enterAnim, exitAnim, popEnterAnim,
                popExitAnim).add(containerId, fragment, null != tag ? tag : "tag")
                .addToBackStack(null).commit();
    }

    public static File setUpImageFile(String directory) throws IOException {
        File imageFile = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            File storageDir = new File(directory);
            if (storageDir != null) {
                if (!storageDir.mkdirs()) {
                    if (!storageDir.exists()) {
                        Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }
            imageFile = File.createTempFile(JPEG_FILE_PREFIX
                            + System.currentTimeMillis() + "_",
                    JPEG_FILE_SUFFIX, storageDir);
        }
        return imageFile;
    }

    public static void hideKeyboard(Context context) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                .toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public static void showKeyboard(Context context) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                .toggleSoftInput(InputMethodManager.SHOW_FORCED,
                        InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                .matches();
    }

    public static String getResizedUserImage(String userImage, int imageWidth, int imageHeight) {
        return userImage + "&w=" + imageWidth + "&h=" + imageHeight;
    }

    public static String changeDateFormat(String dob, String currentFormat,
                                          String requiredFormat) {
        try {
            return new SimpleDateFormat(
                    requiredFormat).format(new SimpleDateFormat(currentFormat).parse(dob));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Uri getUserProfileImageUri(String profileImage, int imageWidth,
                                             int imageHeight) {
        String profilePicUrl = "";
        if (profileImage.contains("timthumb.php?src=")) {
            profilePicUrl = profileImage + "&w=" + imageWidth + "&h=" + imageHeight;
        } else if (profileImage.contains("graph.facebook.com")) {
            profilePicUrl = profileImage + "?width=" + imageWidth + "&height=" + imageHeight;
        } else {
            profilePicUrl = profileImage + "sz=" + imageWidth;
        }
        return Uri.parse(profilePicUrl);
    }

    public static Uri getResizedImageUri(String imageUrl, int imageWidth, int imageHeight) {
        return Uri.parse(imageUrl + "&w=" + (0 != imageWidth ? imageWidth : "") + "&h=" +
                (0 != imageHeight ? imageHeight : ""));
    }

    public static Uri getLocalImageUri(File file) {
        return Uri.parse("file://" + file);
    }

    public static String generateRandomString(int randomStringLength) {
        StringBuffer buffer = new StringBuffer();
        int charactersLength = alphaNumChars.length();
        for (int i = 0; i < randomStringLength; i++) {
            double index = Math.random() * charactersLength;
            buffer.append(alphaNumChars.charAt((int) index));
        }
        return buffer.toString();
    }

    public static boolean isAboveLollipopDevice() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static String getTimeZone() {
        return TimeZone.getDefault().getID();
    }

}

