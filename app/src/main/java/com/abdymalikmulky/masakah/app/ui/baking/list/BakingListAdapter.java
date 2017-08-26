package com.abdymalikmulky.masakah.app.ui.baking.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abdymalikmulky.masakah.R;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;
import com.abdymalikmulky.masakah.app.widget.baking.OnClickWidgetListConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class BakingListAdapter extends RecyclerView.Adapter<BakingListAdapter.ViewHolder> {

    private List<Baking> bakings;
    private Context context;
    private BakingListContract.View bakingListView;
    private OnClickWidgetListConfig onListBakingWidgetClick;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_baking_name)
        TextView itemBakingName;
        @BindView(R.id.item_baking_desc)
        TextView itemBakingDesc;
        @BindView(R.id.item_layout_header)
        RelativeLayout itemLayoutHeader;
        @BindView(R.id.item_baking_step)
        TextView itemBakingStep;

        public Baking baking;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            if(bakingListView != null) {
                bakingListView.onBakingClicked(baking);
            } else{
                onListBakingWidgetClick.onClick(baking);
            }
        }
    }

    public BakingListAdapter(List<Baking> bakings, BakingListContract.View bakingListView) {
        this.bakings = bakings;
        this.bakingListView = bakingListView;
    }

    public BakingListAdapter(List<Baking> bakings, OnClickWidgetListConfig onListBakingWidgetClick) {
        this.bakings = bakings;
        this.onListBakingWidgetClick = onListBakingWidgetClick;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.grid_item_baking, parent, false);

        return new ViewHolder(rootView);
    }


    @SuppressLint("StringFormatMatches")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);

        holder.baking = bakings.get(position);

        holder.itemBakingName.setText(holder.baking.getName());
        holder.itemBakingDesc.setText(String.format(context.getResources().getString(R.string.list_baking_desc),
                String.valueOf(holder.baking.getServings()),
                String.valueOf(holder.baking.getSteps().size()),
                String.valueOf(holder.baking.getIngredients().size())));
        holder.itemBakingStep.setText(Html.fromHtml(context.getResources().getString(R.string.list_baking_by, "abdymalikmulky")));
        if (holder.baking.getImage().equals("")) {
            holder.baking.setImage("http://test.com");
        }
        holder.itemLayoutHeader.setBackgroundResource(context.getResources().getIdentifier(holder.baking.getImageDrawable(), "drawable", context.getPackageName()));

        /* thisis for a valid image url
        Picasso.with(context).load(holder.baking.getImage()).into(new Target(){
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.itemLayoutHeader.setBackground(new BitmapDrawable(context.getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                holder.itemLayoutHeader.setBackground(errorDrawable);
            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {
                holder.itemLayoutHeader.setBackground(placeHolderDrawable);
            }
        });
        */

    }

    @Override
    public int getItemCount() {
        return bakings.size();
    }

    public void refresh(List<Baking> bakings) {
        this.bakings.clear();
        this.bakings = bakings;
        notifyDataSetChanged();
    }
}
