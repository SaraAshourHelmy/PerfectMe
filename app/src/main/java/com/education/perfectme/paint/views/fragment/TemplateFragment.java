package com.education.perfectme.paint.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.education.perfectme.R;
import com.education.perfectme.utilities.Constant;

public class TemplateFragment extends Fragment implements View.OnClickListener {

    private OnSelectItem onSelectItem;
    private ImageView imgV_fruit, imgV_letters, imgV_numbers, imgV_animals, imgV_shapes;

    public TemplateFragment() {
        // Required empty public constructor
    }


    public static TemplateFragment getInstance() {
        TemplateFragment fragment = new TemplateFragment();
        return fragment;
    }

    public void setOnSelectItem(OnSelectItem onSelectItem) {

        this.onSelectItem = onSelectItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_template, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgV_fruit = (ImageView) view.findViewById(R.id.imgV_template_fruit);
        imgV_letters = (ImageView) view.findViewById(R.id.imgV_template_letters);
        imgV_numbers = (ImageView) view.findViewById(R.id.imgV_template_numbers);
        imgV_animals = (ImageView) view.findViewById(R.id.imgV_template_animals);
        imgV_shapes = (ImageView) view.findViewById(R.id.imgV_template_shapes);

        // onclick
        imgV_shapes.setOnClickListener(this);
        imgV_animals.setOnClickListener(this);
        imgV_numbers.setOnClickListener(this);
        imgV_letters.setOnClickListener(this);
        imgV_fruit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imgV_template_fruit:

                onSelectItem.onItemSelected(Constant.CATEGORY_FRUIT);
                break;
            case R.id.imgV_template_letters:

                onSelectItem.onItemSelected(Constant.CATEGORY_LETTERS);
                break;

            case R.id.imgV_template_numbers:

                onSelectItem.onItemSelected(Constant.CATEGORY_NUMBERS);
                break;
            case R.id.imgV_template_animals:

                onSelectItem.onItemSelected(Constant.CATEGORY_ANIMALS);
                break;
            case R.id.imgV_template_shapes:

                onSelectItem.onItemSelected(Constant.CATEGORY_SHAPES);
                break;

            default:
                break;

        }
    }

    public interface OnSelectItem {
        void onItemSelected(int selectPosition);
    }
}
