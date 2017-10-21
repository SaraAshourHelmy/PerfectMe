package com.education.perfectme.paint.views.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.education.perfectme.R;
import com.education.perfectme.paint.model.Image;
import com.education.perfectme.paint.presenter.PaintPresenter;
import com.education.perfectme.paint.presenter.PaintPresenterImpl;
import com.education.perfectme.paint.views.activity.TemplateActivity;
import com.education.perfectme.paint.views.adapter.PaletteAdapter;
import com.education.perfectme.utilities.Constant;
import com.education.perfectme.utilities.PermissionUtils;
import com.education.perfectme.utilities.PickImageUtils;
import com.education.perfectme.utilities.SharedPreferenceUtils;
import com.education.perfectme.utilities.ToolbarUtils;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaintFragment extends Fragment implements PaintView,
        View.OnClickListener, PaletteAdapter.OnItemSelected
        , View.OnTouchListener {
    private Toolbar toolbar;
    private Button btn_newBlank, btn_camera, btn_gallery, btn_template;
    private TextView tvSave, tvAdd;
    private View lnr_menu;
    Bitmap bitmap = null, bitmapPaint;
    private Canvas canvas;
    private Paint paint;
    private int[] colors;
    private float startX = 0, startY = 0, stopX = 0, stopY = 0, fontSize = 4f;
    private int color;
    private RecyclerView recycler_palette;
    private PaintPresenter paintPresenter;
    private ImageView imgV_paint, imgV_brush1, imgV_brush2, imgV_brush3, imgV_brush4;
    private FrameLayout framePaint;
    private boolean isErase = false, isFirst = true;
    private String selectImage = Constant.ImageSelection.SELECT_BLANK.name();
    int image = 0;

    public static PaintFragment getInstance() {
        return new PaintFragment();
    }

    public PaintFragment() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paint, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_paint);
        tvAdd = (TextView) toolbar.findViewById(R.id.tv_add);
        tvSave = (TextView) toolbar.findViewById(R.id.tv_save);
        framePaint = (FrameLayout) view.findViewById(R.id.frame_paint);
        tvAdd.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        ToolbarUtils.setToolbar((AppCompatActivity) getActivity(), toolbar);
        color = ActivityCompat.getColor(getContext(), R.color.palette_black);

        // define palette and brush size
        recycler_palette = (RecyclerView) view.findViewById(R.id.recycler_palette);
        imgV_paint = (ImageView) view.findViewById(R.id.imgV_paint);
        imgV_brush1 = (ImageView) view.findViewById(R.id.imgV_brush1);
        imgV_brush2 = (ImageView) view.findViewById(R.id.imgV_brush2);
        imgV_brush3 = (ImageView) view.findViewById(R.id.imgV_brush3);
        imgV_brush4 = (ImageView) view.findViewById(R.id.imgV_brush4);
        imgV_brush1.setOnClickListener(this);
        imgV_brush2.setOnClickListener(this);
        imgV_brush3.setOnClickListener(this);
        imgV_brush4.setOnClickListener(this);

        // define menu item
        lnr_menu = view.findViewById(R.id.lnr_menu);
        btn_newBlank = (Button) lnr_menu.findViewById(R.id.btn_menu_blank);
        btn_camera = (Button) lnr_menu.findViewById(R.id.btn_menu_camera);
        btn_gallery = (Button) lnr_menu.findViewById(R.id.btn_menu_gallery);
        btn_template = (Button) lnr_menu.findViewById(R.id.btn_menu_template);
        btn_newBlank.setOnClickListener(this);
        btn_camera.setOnClickListener(this);
        btn_gallery.setOnClickListener(this);
        btn_template.setOnClickListener(this);

        // presenter
        paintPresenter = new PaintPresenterImpl(this, getContext());
        setRecycler();

    }

    public void openBlank() {
        bitmap = null;
        paintImage();
    }

    public void showMenu() {
        lnr_menu.setVisibility(View.VISIBLE);
        tvAdd.setVisibility(View.GONE);
    }

    public void closeMenu() {
        if (lnr_menu.getVisibility() == View.VISIBLE) {
            lnr_menu.setVisibility(View.GONE);
            tvAdd.setVisibility(View.VISIBLE);
        }
    }

    public void openTemplate() {
        Intent intent = new Intent(getContext(), TemplateActivity.class);
        startActivityForResult(intent, Constant.REQUEST_Template_IMAGE);
    }

    public void openGallery() {

        boolean flag = PermissionUtils.getPermission(getActivity(), Constant.PERMISSION_READ_STORAGE,
                Constant.REQUEST_READ_STORAGE);
        if (flag) {
            paintPresenter.getImageGallery();
        }
    }

    public void saveImage() {
        boolean flag = PermissionUtils.getPermission(getActivity(), Constant.PERMISSION_WRITE_STORAGE,
                Constant.REQUEST_WRITE_STORAGE);

        if (flag) {
            Bitmap savedImage = PickImageUtils.getBitmapView(framePaint);
            SharedPreferenceUtils.getInstance(getContext()).setImageCount();
            String name = "Paint" + SharedPreferenceUtils.getInstance(getContext()).getImageCount();
            boolean result = paintPresenter.saveImageToSDCard(savedImage, name);
            if (result)
                Toast.makeText(getContext(), getString(R.string.image_success)
                        , Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(getContext(), getString(R.string.image_fail)
                        , Toast.LENGTH_LONG).show();
            }
        }
    }

    public void openCamera() {

        boolean flag = PermissionUtils.getPermission(getActivity(), Constant.PERMISSION_CAMERA,
                Constant.REQUEST_CAMERA);
        if (flag)
            paintPresenter.getImageCamera();
    }

    private void setRecycler() {

        colors = getResources().getIntArray(R.array.paint_colors);
        PaletteAdapter adapter = new PaletteAdapter(colors, this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),
                2, LinearLayoutManager.HORIZONTAL, false);

        recycler_palette.setLayoutManager(layoutManager);
        recycler_palette.setAdapter(adapter);
        recycler_palette.setHasFixedSize(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            // for capture image from camera
            case Constant.REQUEST_CAPTURE_IMAGE:
                if (resultCode == RESULT_OK)
                    setCameraImage(data);
                break;

            // for pick image from gallery
            case Constant.REQUEST_PICK_IMAGE:
                if (resultCode == RESULT_OK)
                    setGalleryImage(data);
                break;

            // for pick image from template
            case Constant.REQUEST_Template_IMAGE:

                setTemplateImage(data);
                break;
            default:
                break;
        }
        if (!(selectImage.equals(Constant.ImageSelection.SELECT_TEMPLATE.name())
                && image == 0))
            paintImage();

    }

    private void setTemplateImage(Intent data) {

        if (data.hasExtra(Constant.SELECTED_TEMPLATE_IMAGE)) {
            image = data.getIntExtra(Constant.SELECTED_TEMPLATE_IMAGE, 0);
            if (image != 0) {
                bitmap = BitmapFactory.decodeResource(getResources(), image);
            }

        }
    }

    private void setGalleryImage(Intent data) {

        bitmap = PickImageUtils.getGalleryImage(data, getContext());
        //if (image != null)
        //  imgV_paint.setImageBitmap(image);
    }

    private void setCameraImage(Intent data) {
        bitmap = PickImageUtils.getCameraImage(data);
        //if (image != null) {
        //paintImage(image);
        //imgV_paint.setImageBitmap(image);
        //}
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            // for menu items
            case R.id.tv_add:
                showMenu();
                break;
            case R.id.tv_save:
                saveImage();
                break;

            case R.id.btn_menu_blank:
                isFirst = false;
                selectImage = Constant.ImageSelection.SELECT_BLANK.name();
                openBlank();
                break;
            case R.id.btn_menu_camera:
                isFirst = false;
                selectImage = Constant.ImageSelection.SELECT_CAMERA.name();
                openCamera();
                break;
            case R.id.btn_menu_gallery:
                isFirst = false;
                selectImage = Constant.ImageSelection.SELECT_GALLERY.name();
                openGallery();
                break;
            case R.id.btn_menu_template:
                isFirst = false;
                selectImage = Constant.ImageSelection.SELECT_TEMPLATE.name();
                openTemplate();
                break;

            // for brush sizes
            case R.id.imgV_brush1:

                fontSize = getFontSize(R.dimen.font_xSmall);
                break;

            case R.id.imgV_brush2:

                fontSize = getFontSize(R.dimen.font_small);
                break;

            case R.id.imgV_brush3:

                fontSize = getFontSize(R.dimen.font_medium);
                break;

            case R.id.imgV_brush4:

                fontSize = getFontSize(R.dimen.font_large);
                break;

            default:
                break;

        }
        if (v != tvAdd)
            closeMenu();
    }

    public void paintImage() {

        bitmapPaint = Bitmap.createBitmap(framePaint.getWidth(), framePaint.getHeight(),
                Bitmap.Config.ARGB_8888);
        try {
            if (!selectImage.equals(Constant.ImageSelection.SELECT_BLANK.name())) {
                Image image = new Image();
                image.setOriginWidth(bitmap.getWidth());
                image.setOriginHeight(bitmap.getHeight());
                image.setReqWidth(framePaint.getWidth());
                image.setReqHeight(framePaint.getHeight());
                image.setBitmap(bitmap);
                bitmap = PickImageUtils.resizeImage(image);
                //bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                framePaint.setBackground(PickImageUtils.convertBitmapToDrawable
                        (getContext(), bitmap));
            } else {
                framePaint.setBackgroundColor(ActivityCompat.getColor(getContext(),
                        R.color.palette_white));
            }

            canvas = new Canvas(bitmapPaint);
            paint = new Paint();
            setPaintOption();
            imgV_paint.setImageBitmap(bitmapPaint);
            imgV_paint.setOnTouchListener(this);
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
    }

    private void setPaintOption() {
        if (isErase) {
            paint.setAlpha(0xFF);
            paint.setAntiAlias(true);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            paint.setStrokeWidth(30f);
        } else {
            paint.setXfermode(null);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(color);
            paint.setStrokeWidth(fontSize);
        }
    }

    @Override
    public void onItemSelect(int position) {
        // check menu visibility
        closeMenu();
        if (isFirst) {
            isFirst = false;
            paintImage();
        }

        if (position != 0) {
            isErase = false;
            color = colors[position - 1];
        } else {
            isErase = true;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        closeMenu();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                stopX = event.getX();
                stopY = event.getY();
                setPaintOption();
                canvas.drawLine(startX, startY, stopX, stopY, paint);

                // update Start point
                startX = event.getX();
                startY = event.getY();
                imgV_paint.setImageBitmap(bitmapPaint);
                break;
            case MotionEvent.ACTION_UP:
                break;

            default:
                break;
        }
        return true;
    }

    private float getFontSize(int fontSize) {
        TypedValue outValue = new TypedValue();
        getResources().getValue(fontSize, outValue, true);
        float value = outValue.getFloat();
        return value;
    }
}
