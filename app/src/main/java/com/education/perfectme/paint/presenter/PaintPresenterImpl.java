package com.education.perfectme.paint.presenter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.education.perfectme.paint.views.fragment.PaintView;
import com.education.perfectme.utilities.PickImageUtils;

/**
 * Created by Sara on 7/26/2017.
 */

public class PaintPresenterImpl implements PaintPresenter {

    private PaintView paintView;
    private Context context;
    private Fragment fragment;


    public PaintPresenterImpl(PaintView paintView, Context context) {
        this.paintView = paintView;
        this.context = context;
        this.fragment = (Fragment) paintView;
    }

    @Override
    public void getImageCamera() {

        PickImageUtils.takeCameraImage(fragment);
        Toast.makeText(context, "Camera Granted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void getImageGallery() {
        PickImageUtils.takeGalleryImage(fragment);
        Toast.makeText(context, "Read Storage Granted", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean saveImageToSDCard(Bitmap bitmap, String name) {

        boolean result = PickImageUtils.saveImageOnGallery(context, bitmap, name);
        return result;
    }

}
