package com.education.perfectme.paint.views.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.education.perfectme.R;

/**
 * Created by Sara on 7/28/2017.
 */

public class TemplateAdapter extends RecyclerView.Adapter<TemplateAdapter.Holder> {

    private int[] templates;
    OnItemSelected onItemSelected;

    public TemplateAdapter(int[] templates, OnItemSelected onItemSelected) {
        this.templates = templates;
        this.onItemSelected = onItemSelected;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.adapter_template, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        int path = templates[position];
        holder.imgV_templateItem.setImageResource(path);
    }

    @Override
    public int getItemCount() {
        return templates.length;
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgV_templateItem;

        public Holder(View itemView) {
            super(itemView);
            imgV_templateItem = (ImageView) itemView.findViewById(R.id.imgV_template_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            onItemSelected.onItemSelect(getAdapterPosition());
        }
    }

    public interface OnItemSelected {
        void onItemSelect(int position);
    }
}
