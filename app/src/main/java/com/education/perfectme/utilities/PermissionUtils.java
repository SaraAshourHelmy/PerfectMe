package com.education.perfectme.utilities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.security.Permission;

/**
 * Created by Sara on 7/26/2017.
 */

public class PermissionUtils {

    public static boolean getPermission(Activity activity, String permission,
                                        int requestCode) {
        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);

            return false;
        } else
            return true;
    }
}
