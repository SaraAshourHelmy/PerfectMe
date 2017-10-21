package com.education.perfectme.paint.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.education.perfectme.R;
import com.education.perfectme.bases.BaseActivity;
import com.education.perfectme.paint.views.fragment.TemplateCategoryFragment;
import com.education.perfectme.paint.views.fragment.TemplateFragment;
import com.education.perfectme.utilities.Constant;
import com.education.perfectme.utilities.FragmentUtility;

public class TemplateActivity extends BaseActivity implements TemplateFragment.OnSelectItem {

    private TemplateFragment fragment;
    private static final String TAG_TEMPLATE = "templateFragment";
    private static final String TAG_TEMPLATE_CATEGORY = "categoryFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        bindView();
    }

    @Override
    public void bindView() {
        fragment = TemplateFragment.getInstance();
        fragment.setOnSelectItem(this);
        FragmentUtility.insertFragment(this, R.id.container_template,
                fragment, TAG_TEMPLATE);
    }

    @Override
    public void onItemSelected(int selectPosition) {

        setCategory(selectPosition);
    }

    public void setCategory(int selectedPosition) {

        TemplateCategoryFragment templateFragment =
                TemplateCategoryFragment.getInstance(selectedPosition);
        FragmentUtility.replaceFragment(this, R.id.container_template, templateFragment
                , TAG_TEMPLATE_CATEGORY);


    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 0)
            getSupportFragmentManager().popBackStack();
        else {
            Intent intent = new Intent();
            intent.putExtra(Constant.SELECTED_TEMPLATE_IMAGE, 0);
            setResult(Constant.REQUEST_Template_IMAGE, intent);
            finish();
        }
    }
}
