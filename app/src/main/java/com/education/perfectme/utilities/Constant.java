package com.education.perfectme.utilities;

import android.Manifest;

/**
 * Created by Sara on 7/26/2017.
 */

public class Constant {

    // Camera request
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final int REQUEST_CAMERA = 100;
    public static final int REQUEST_CAPTURE_IMAGE = 101;

    // Storage
    public static final String PERMISSION_READ_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final int REQUEST_READ_STORAGE = 200;
    public static final int REQUEST_WRITE_STORAGE = 400;
    public static final int REQUEST_PICK_IMAGE = 201;

    // Select Template Category
    public static final int CATEGORY_FRUIT = 1;
    public static final int CATEGORY_LETTERS = 2;
    public static final int CATEGORY_NUMBERS = 3;
    public static final int CATEGORY_ANIMALS = 4;
    public static final int CATEGORY_SHAPES = 5;

    public static final String SELECTED_CATEGORY = "category";
    public static final String SELECTED_TEMPLATE_IMAGE = "templateImage";
    public static final int REQUEST_Template_IMAGE = 300;

    // Image Selection

    public enum ImageSelection {
        SELECT_BLANK,
        SELECT_CAMERA,
        SELECT_GALLERY,
        SELECT_TEMPLATE
    }


}
