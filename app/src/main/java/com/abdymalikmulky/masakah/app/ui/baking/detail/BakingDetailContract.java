package com.abdymalikmulky.masakah.app.ui.baking.detail;

import com.abdymalikmulky.masakah.app.BasePresenter;
import com.abdymalikmulky.masakah.app.BaseView;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Ingredient;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Step;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class BakingDetailContract {

    interface View extends BaseView<Presenter> {
        void showBaking(Baking baking);

        void showIngredients(List<Ingredient> ingredients);

        void showSteps(List<Step> steps);

        void showError(String msg);
    }

    interface Presenter extends BasePresenter {
        void loadBaking();
    }

}
