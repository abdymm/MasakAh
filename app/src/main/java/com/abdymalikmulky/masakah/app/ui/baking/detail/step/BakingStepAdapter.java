package com.abdymalikmulky.masakah.app.ui.baking.detail.step;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdymalikmulky.masakah.R;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class BakingStepAdapter extends RecyclerView.Adapter<BakingStepAdapter.ViewHolder> {

    private List<Step> steps;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_baking_step_order)
        TextView itemBakingStepOrder;
        @BindView(R.id.item_baking_step_short_desc)
        TextView itemBakingStepShortDesc;
        @BindView(R.id.item_baking_step_desc)
        TextView itemBakingStepDesc;

        public Step step;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

    }

    public BakingStepAdapter(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.list_item_step, parent, false);

        return new ViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.step = steps.get(position);

        holder.itemBakingStepOrder.setText(String.valueOf(position+1));
        holder.itemBakingStepDesc.setText(holder.step.getDescription());
        holder.itemBakingStepShortDesc.setText(holder.step.getShortDescription());

    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public void refresh(List<Step> steps) {
        this.steps.clear();
        this.steps = steps;
        notifyDataSetChanged();
    }
}
