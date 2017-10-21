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
    }

    @Override
    public void bindView() {

        circleLayout = (CircleLayout) findViewById(R.id.circleLayout);
        paletteImageView = (ImageView) findViewById(R.id.imgV_palette);
        videosImageView = (ImageView) findViewById(R.id.imgV_videos);
        ABCImageView = (ImageView) findViewById(R.id.imgV_abc);
        giftsImageView = (ImageView) findViewById(R.id.imgV_gifts);
        todoListImageView = (ImageView) findViewById(R.id.imgV_list_todo);
        circleLayout.setOnRotationFinishedListener(new CircleLayout.OnRotationFinishedListener() {
            @Override
            public void onRotationFinished(View view) {
                navigateToActivity(selectedActivityName);
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

        selectedActivityName = "";
        switch (view.getId()) {

            case R.id.imgV_abc:
                //// TODO: 10/21/2017  navigate abc
                selectedActivityName = ACTIVITY_CLASS_PALETTE;
                break;
            case R.id.imgV_palette:
                //// TODO: 10/21/2017  navigate palette
                selectedActivityName = ACTIVITY_CLASS_PALETTE;
                break;
            case R.id.imgV_gifts:
                //// TODO: 10/21/2017  navigate gifts
                selectedActivityName = ACTIVITY_CLASS_PALETTE;
                break;
            case R.id.imgV_videos:
                //// TODO: 10/21/2017 navigate videos
                selectedActivityName = ACTIVITY_CLASS_PALETTE;
                break;
            case R.id.imgV_list_todo:
                //// TODO: 10/21/2017 navigate todo list
                selectedActivityName = ACTIVITY_CLASS_PALETTE;
                break;
            default:
                break;
        }

    }

    private void navigateToActivity(String activityName) {
        Intent intent = new Intent(this, PaintActivity.class);
        startActivity(intent);
    }

}
