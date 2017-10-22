package com.education.perfectme.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.education.perfectme.R;
import com.education.perfectme.bases.BaseActivity;
import com.education.perfectme.paint.views.activity.PaintActivity;
import com.szugyi.circlemenu.view.CircleLayout;

public class HomeActivity extends BaseActivity {

    private CircleLayout circleLayout;
    private ImageView paletteImageView;
    private ImageView videosImageView;
    private ImageView ABCImageView;
    private ImageView giftsImageView;
    private ImageView todoListImageView;
    private final static String ACTIVITY_CLASS_PALETTE = "PaintActivity";
    private String selectedActivityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bindView();
    }

    @Override
    public void bindView() {

        circleLayout = (CircleLayout) findViewById(R.id.circleLayout);
        paletteImageView = (ImageView) findViewById(R.id.imgV_palette);
        videosImageView = (ImageView) findViewById(R.id.imgV_videos);
        ABCImageView = (ImageView) findViewById(R.id.imgV_abc);
        giftsImageView = (ImageView) findViewById(R.id.imgV_gifts);
        todoListImageView = (ImageView) findViewById(R.id.imgV_list_todo);

        circleLayout.setOnItemSelectedListener(new CircleLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view) {

                navigation(view);
            }
        });

        circleLayout.setOnItemClickListener(new CircleLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                navigation(view);
            }
        });

    }

    private void navigation(View view) {

        Class selectedActivity;
        switch (view.getId()) {

            case R.id.imgV_abc:
                //// TODO: 10/21/2017  navigate abc
                selectedActivity = PaintActivity.class;
                break;
            case R.id.imgV_palette:
                //// TODO: 10/21/2017  navigate palette
                selectedActivity = PaintActivity.class;
                break;
            case R.id.imgV_gifts:
                //// TODO: 10/21/2017  navigate gifts
                selectedActivity = PaintActivity.class;
                break;
            case R.id.imgV_videos:
                //// TODO: 10/21/2017 navigate videos
                selectedActivity = PaintActivity.class;
                break;
            case R.id.imgV_list_todo:
                //// TODO: 10/21/2017 navigate todo list
                selectedActivity = PaintActivity.class;
                break;
            default:
                selectedActivity = null;
                break;
        }
        navigateToActivity(selectedActivity);
    }

    private void navigateToActivity(Class activityName) {
        Intent intent = new Intent(this, activityName);
        startActivity(intent);
    }

}
