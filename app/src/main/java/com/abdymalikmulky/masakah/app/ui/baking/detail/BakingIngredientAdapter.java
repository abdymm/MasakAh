package com.abdymalikmulky.masakah.app.ui.baking.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdymalikmulky.masakah.R;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class BakingIngredientAdapter extends RecyclerView.Adapter<BakingIngredientAdapter.ViewHolder> {

    private List<Ingredient> ingredients;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_baking_ingredients_quantity)
        TextView listBakingIngredientsQuantity;
        @BindView(R.id.list_baking_ingredients_value)
        TextView listBakingIngredientsValue;

        public Ingredient ingredient;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

    }

    public BakingIngredientAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.list_item_ingredient, parent, false);

        return new ViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.ingredient = ingredients.get(position);

        holder.listBakingIngredientsQuantity.setText(holder.ingredient.getQuantity() + " " + holder.ingredient.getMeasure());
        holder.listBakingIngredientsValue.setText(holder.ingredient.getIngredient());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void refresh(List<Ingredient> ingredients) {
        this.ingredients.clear();
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }
}
