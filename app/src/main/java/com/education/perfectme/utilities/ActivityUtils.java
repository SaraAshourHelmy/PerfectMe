package com.education.perfectme.utilities;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.education.perfectme.R;

/**
 * Created by Sara on 8/5/2017.
 */

public class ActivityUtils {

    public static void setOrientation(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public static boolean isTablet(Activity activity) {
        boolean isTab = activity.getResources().getBoolean(R.bool.isTablet);
        return isTab;
    }
}
