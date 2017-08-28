package com.abdymalikmulky.masakah.app.ui.baking.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

    private BakingDetailContract.View bakingDetailView;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_baking_step_order)
        TextView itemBakingStepOrder;
        @BindView(R.id.item_baking_step_short_desc)
        TextView itemBakingStepShortDesc;
        @BindView(R.id.item_baking_step_desc)
        TextView itemBakingStepDesc;
        @BindView(R.id.item_baking_step_line)
        View itemBakingStepLine;
        @BindView(R.id.item_baking_step_layout_watchvideo)
        LinearLayout itemBakingStepLayoutWatchvideo;

        public Step step;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

    }

    public BakingStepAdapter(List<Step> steps, BakingDetailContract.View bakingDetailView) {
        this.steps = steps;
        this.bakingDetailView = bakingDetailView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.list_item_step, parent, false);

        return new ViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.step = steps.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   bakingDetailView.onBakingStepClicked(holder.step, position);
                                               }
                                           });


        holder.itemBakingStepOrder.setText(String.valueOf(position + 1));
        holder.itemBakingStepDesc.setText(holder.step.getDescription());
        holder.itemBakingStepShortDesc.setText(holder.step.getShortDescription());

        showHideWatchVideo(holder, true);
        if(holder.step.getVideoURL().equals("")) {
            showHideWatchVideo(holder, false);
        }

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

    private void showHideWatchVideo(ViewHolder holder, boolean show) {
        holder.itemBakingStepLayoutWatchvideo.setVisibility(View.GONE);
        holder.itemBakingStepLine.setVisibility(View.GONE);
        if(show) {
            holder.itemBakingStepLayoutWatchvideo.setVisibility(View.VISIBLE);
            holder.itemBakingStepLine.setVisibility(View.VISIBLE);
        }
    }
}
