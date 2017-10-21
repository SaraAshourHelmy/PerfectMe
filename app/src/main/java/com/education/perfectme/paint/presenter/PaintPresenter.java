package com.education.perfectme.paint.presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;

/**
 * Created by Sara on 7/26/2017.
 */

public interface PaintPresenter {

    void getImageCamera();

    void getImageGallery();

    boolean saveImageToSDCard(Bitmap bitmap, String name);
}
