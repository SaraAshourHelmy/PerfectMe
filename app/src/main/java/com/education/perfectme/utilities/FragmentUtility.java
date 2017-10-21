package com.education.perfectme.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Sara on 7/15/2017.
 */

public class FragmentUtility {

    public static void insertFragment(AppCompatActivity activity,
                                      int container, Fragment fragment, String tag) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(container, fragment, tag);
        //transaction.addToBackStack(tag);
        transaction.commit();
    }

    public static void replaceFragment(AppCompatActivity activity,
                                       int container, Fragment fragment, String tag) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(container, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();

    }
}
