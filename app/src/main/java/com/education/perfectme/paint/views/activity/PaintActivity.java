package com.education.perfectme.paint.views.activity;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.education.perfectme.R;
import com.education.perfectme.bases.BaseActivity;
import com.education.perfectme.paint.views.fragment.PaintFragment;
import com.education.perfectme.utilities.ActivityUtils;
import com.education.perfectme.utilities.Constant;
import com.education.perfectme.utilities.FragmentUtility;


public class PaintActivity extends BaseActivity {


    PaintFragment paintFragment;

    static final String TAG_PAINT = "paintFragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        setActivityOrientation();
        bindView();

    }


    public void setActivityOrientation() {
        if (ActivityUtils.isTablet(this))
            ActivityUtils.setOrientation(this);
    }

    @Override
    public void bindView() {

        paintFragment = PaintFragment.getInstance();
        FragmentUtility.insertFragment(this, R.id.container_paint,
                paintFragment, TAG_PAINT);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.REQUEST_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    paintFragment.openCamera();
                break;
            case Constant.REQUEST_READ_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    paintFragment.openGallery();
                break;

            case Constant.REQUEST_WRITE_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    paintFragment.saveImage();
            default:
                break;
        }
    }


}
