package com.education.perfectme.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.education.perfectme.paint.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * Created by Sara on 7/26/2017.
 */

public class PickImageUtils {

    public static void takeCameraImage(Fragment fragment) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Activity activity = fragment.getActivity();
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            fragment.startActivityForResult(takePictureIntent,
                    Constant.REQUEST_CAPTURE_IMAGE);
        }
    }

    public static void takeGalleryImage(Fragment fragment) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        Activity activity = fragment.getActivity();
        if (photoPickerIntent.resolveActivity(activity.getPackageManager()) != null) {
            fragment.startActivityForResult(photoPickerIntent, Constant.REQUEST_PICK_IMAGE);
        }
    }

    @Nullable
    public static Bitmap getGalleryImage(Intent data, Context context) {
        Uri imageUri = data.getData();
        // return selectedImage;
        Bitmap selectedImage = null;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            selectedImage = MediaStore.Images.Media
                    .getBitmap(context.getContentResolver(), imageUri);
            return selectedImage;
        } catch (IOException e) {
            Log.e("image_error", e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public static Bitmap getCameraImage(Intent data) {
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        Bitmap result = (imageBitmap != null) ? imageBitmap : null;
        return result;
    }

    public static Bitmap resizeImage(Image image) {
        if (image.getOriginWidth() > image.getReqWidth()) {

            // picture is wider than we want it, we calculate its target height
            int destHeight = image.getOriginHeight() /
                    (image.getOriginWidth() / image.getReqWidth());
            // we create an scaled bitmap so it reduces the image, not just trim it
            Bitmap b2 = Bitmap.createScaledBitmap(image.getBitmap(),
                    image.getReqWidth(), image.getReqHeight(), false);

            //image.getReqHeight()
            return b2;
        }
        return image.getBitmap();
    }

    public static Drawable convertBitmapToDrawable(Context context, Bitmap bitmap) {
        Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        return drawable;
    }

    public static Bitmap getBitmapView(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
        //view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public static boolean saveImageOnGallery(Context context, Bitmap bitmap, String name) {
        try {

            String savedImageURL = MediaStore.Images.Media.insertImage
                    (context.getContentResolver(), bitmap, name, "Paint");
            Log.e("image_path", savedImageURL);
            if (savedImageURL.length() > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
}
