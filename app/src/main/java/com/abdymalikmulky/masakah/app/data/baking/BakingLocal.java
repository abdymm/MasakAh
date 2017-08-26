package com.abdymalikmulky.masakah.app.data.baking;

import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking_Table;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Ingredient;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Ingredient_Table;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Step;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 8/17/17.
 */

public class BakingLocal implements BakingDataSource {

    @Override
    public void load(FetchBakingCallback callback) {
        List<Baking> bakings = SQLite.select()
                .from(Baking.class).queryList();

        if(bakings.size() > 0) {
            callback.onFetched(bakings);
        } else {
            callback.onFailed("No Data");
        }
    }

    public Baking getBaking(int bakingId) {
        Baking baking = SQLite.select()
                .from(Baking.class)
                .where(Baking_Table.id.eq(bakingId))
                .querySingle();

        List<Ingredient> ingredients = SQLite.select()
                .from(Ingredient.class)
                .where(Ingredient_Table.bakingId.eq(bakingId))
                .queryList();

        baking.setIngredients(ingredients);

        return baking;
    }

    public boolean save(Baking baking) {
        Baking newBaking = setBakingIdFor(baking);
        return (baking.insert() > 0) ? true : false;
    }

    private Baking setBakingIdFor(Baking baking) {
        List<Ingredient> ingredients = baking.getIngredients();
        List<Step> steps = baking.getSteps();
        for (int i=0; i < ingredients.size(); i++) {
            ingredients.get(i).setBakingId(baking.getId());
        }
        for (int i=0; i < steps.size(); i++) {
            steps.get(i).setBakingId(baking.getId());
        }
        baking.setIngredients(ingredients);
        baking.setSteps(steps);
        return baking;
    }

    public boolean isExist(int bakingId){
        long rowCount = SQLite.select()
                .from(Baking.class)
                .where(Baking_Table.id.eq(bakingId))
                .count();
        return (rowCount > 0) ? true : false;
    }

}
