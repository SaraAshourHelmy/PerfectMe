package com.education.perfectme.paint.views.fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.education.perfectme.R;
import com.education.perfectme.paint.views.adapter.TemplateAdapter;
import com.education.perfectme.utilities.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class TemplateCategoryFragment extends Fragment implements TemplateAdapter.OnItemSelected {

    private int selectedPosition;
    private int[] templateItems;
    private RecyclerView recycleTemplate;

    public TemplateCategoryFragment() {
        // Required empty public constructor

    }

    public static TemplateCategoryFragment getInstance(int selectedPosition) {
        TemplateCategoryFragment fragment = new TemplateCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.SELECTED_CATEGORY, selectedPosition);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle.containsKey(Constant.SELECTED_CATEGORY)) {
            selectedPosition = bundle.getInt(Constant.SELECTED_CATEGORY);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_template_category, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTemplateItems();
        recycleTemplate = (RecyclerView) view.findViewById(R.id.recycler_template);
        setRecycleTemplate();
    }

    private void setTemplateItems() {
        switch (selectedPosition) {
            case Constant.CATEGORY_FRUIT:
                getResourceArray(R.array.template_fruit);
                break;
            case Constant.CATEGORY_ANIMALS:
                getResourceArray(R.array.template_animals);
                break;
            case Constant.CATEGORY_LETTERS:
                getResourceArray(R.array.template_letters);
                break;
            case Constant.CATEGORY_NUMBERS:
                getResourceArray(R.array.template_numbers);
                break;
            case Constant.CATEGORY_SHAPES:
                getResourceArray(R.array.template_shapes);
                break;
            default:
                break;
        }
    }

    private void getResourceArray(int arrayID) {
        TypedArray tArray = getResources().obtainTypedArray(arrayID);
        int count = tArray.length();
        templateItems = new int[count];
        for (int i = 0; i < templateItems.length; i++) {
            templateItems[i] = tArray.getResourceId(i, 0);
        }

    }

    private void setRecycleTemplate() {
        TemplateAdapter adapter = new TemplateAdapter(templateItems, this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recycleTemplate.setLayoutManager(layoutManager);
        recycleTemplate.setAdapter(adapter);
    }

    @Override
    public void onItemSelect(int position) {

        Intent intent = new Intent();
        intent.putExtra(Constant.SELECTED_TEMPLATE_IMAGE, templateItems[position]);
        getActivity().setResult(Constant.REQUEST_Template_IMAGE, intent);
        getActivity().finish();


    }


}
