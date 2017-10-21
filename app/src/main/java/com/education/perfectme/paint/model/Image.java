package com.education.perfectme.paint.model;

import android.graphics.Bitmap;

/**
 * Created by Sara on 7/29/2017.
 */

public class Image {

    private int originWidth;
    private int originHeight;
    private int reqWidth;
    private int reqHeight;
    private Bitmap bitmap;

    public int getOriginWidth() {
        return originWidth;
    }

    public int getOriginHeight() {
        return originHeight;
    }

    public int getReqWidth() {
        return reqWidth;
    }

    public int getReqHeight() {
        return reqHeight;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setOriginWidth(int originWidth) {
        this.originWidth = originWidth;
    }

    public void setOriginHeight(int originHeight) {
        this.originHeight = originHeight;
    }

    public void setReqWidth(int reqWidth) {
        this.reqWidth = reqWidth;
    }

    public void setReqHeight(int reqHeight) {
        this.reqHeight = reqHeight;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
