package com.abdymalikmulky.masakah.app.widget.baking;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.abdymalikmulky.masakah.R;
import com.abdymalikmulky.masakah.app.data.baking.BakingLocal;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Ingredient;
import com.abdymalikmulky.masakah.util.ConstantsUtil;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 8/26/17.
 */

public class BakingIngredientListProvider implements RemoteViewsFactory {
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();
    private Context context = null;

    private int bakingId;

    private Baking baking;
    private BakingLocal bakingLocal;

    public BakingIngredientListProvider(Context context) {
        this.context = context;

        SharedPreferences prefs = context.getSharedPreferences("pref", MODE_PRIVATE);
        bakingId = prefs.getInt(ConstantsUtil.INTENT_BAKING_ID, 0);


        bakingLocal = new BakingLocal();
        baking = bakingLocal.getBaking(bakingId);

        ingredients = baking.getIngredients();
    }


    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     *Similar to getView of Adapter where instead of View
     *we return RemoteViews
     *
     */
    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.list_item_wizard_ingredient);
        Ingredient ingredient = ingredients.get(position);
        remoteView.setTextViewText(R.id.list_item_wizard_ingredient_quantity, ingredient.getQuantity() + " " + ingredient.getMeasure());
        remoteView.setTextViewText(R.id.list_item_wizard_ingredient_value, ingredient.getIngredient());

        return remoteView;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {
    }

}
