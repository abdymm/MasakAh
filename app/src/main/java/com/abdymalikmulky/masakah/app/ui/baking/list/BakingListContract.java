package com.abdymalikmulky.masakah.app.ui.baking.list;

import com.abdymalikmulky.masakah.app.BasePresenter;
import com.abdymalikmulky.masakah.app.BaseView;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 7/7/17.
 */

public class BakingListContract {

    interface View extends BaseView<Presenter> {
        void showBakings(List<Baking> baking);
        void showError(String msg);

        void onBakingClicked(Baking baking);
    }

    interface Presenter extends BasePresenter {
        void loadBakings();
    }

}
