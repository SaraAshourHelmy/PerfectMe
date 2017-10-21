package com.education.perfectme.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sara on 8/5/2017.
 */

public class SharedPreferenceUtils {

    private static SharedPreferenceUtils utils;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static final String PREFERENCE_NAME = "imageCount";
    public static final String PARAM_COUNT = "count";
    private static Context mContext;

    public static SharedPreferenceUtils getInstance(Context context) {

        if (utils == null) {
            mContext = context;
            utils = new SharedPreferenceUtils();

        }
        return utils;
    }

    private SharedPreferenceUtils() {
        if (preferences == null || editor == null) {
            preferences = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            editor = preferences.edit();
        }
    }

    public void setImageCount() {
        int count = preferences.getInt(PARAM_COUNT, 0);
        count++;
        editor.putInt(PARAM_COUNT, count);
        editor.commit();
    }

    public int getImageCount() {
        return preferences.getInt(PARAM_COUNT, 0);
    }

}
