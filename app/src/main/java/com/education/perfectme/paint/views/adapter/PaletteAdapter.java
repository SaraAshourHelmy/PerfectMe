package com.education.perfectme.paint.views.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.education.perfectme.R;


/**
 * Created by Sara on 7/15/2017.
 */

public class PaletteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int[] mLstColors;
    static final int TYPE_COLOR = 1, TYPE_ERASE = 2;
    private OnItemSelected onItemSelected;


    public PaletteAdapter(int[] lstColors, OnItemSelected onItemSelected) {
        mLstColors = lstColors;
        this.onItemSelected = onItemSelected;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_COLOR) {
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.adapter_palette, parent, false);
            return new ColorHolder(view);
        } else if (viewType == TYPE_ERASE) {
            view = LayoutInflater.from(parent.getContext()).inflate
                    (R.layout.adapter_erase, parent, false);
            return new EraseHolder(view);
        }


        return null;
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if (position == 0)
            type = TYPE_ERASE;
        else
            type = TYPE_COLOR;
        return type;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position > 0) {
            ColorHolder colorHolder = (ColorHolder) holder;
            colorHolder.tv_paint.setBackgroundColor(mLstColors[position - 1]);
        } else {
            EraseHolder eraseHolder = (EraseHolder) holder;
            eraseHolder.imgBtn_eraser.setBackgroundResource(R.drawable.ic_eraser);
        }
    }

    @Override
    public int getItemCount() {
        return mLstColors.length + 1;
    }

    class ColorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView tv_paint;

        public ColorHolder(View itemView) {
            super(itemView);

            tv_paint = (TextView) itemView.findViewById(R.id.textV_paint);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemSelected.onItemSelect(getAdapterPosition());
        }
    }

    class EraseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton imgBtn_eraser;

        public EraseHolder(View itemView) {
            super(itemView);
            imgBtn_eraser = (ImageButton) itemView.findViewById(R.id.imgBtn_eraser);
            imgBtn_eraser.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemSelected.onItemSelect(0);
        }
    }

    public interface OnItemSelected {
        void onItemSelect(int position);
    }
}
