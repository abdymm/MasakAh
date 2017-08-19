package com.abdymalikmulky.masakah.app.ui.baking.list;

import com.abdymalikmulky.masakah.app.data.baking.BakingDataSource;
import com.abdymalikmulky.masakah.app.data.baking.BakingRepo;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 8/19/17.
 */

public class BakingListPresenter implements BakingListContract.Presenter {

    private BakingRepo bakingRepo;
    private BakingListContract.View bakingListView;

    public BakingListPresenter(BakingRepo bakingRepo, BakingListContract.View bakingListView) {
        this.bakingRepo = bakingRepo;
        this.bakingListView = bakingListView;

        this.bakingListView.setPresenter(this);
    }

    @Override
    public void start() {
        loadBakings();
    }

    @Override
    public void stop() {

    }

    @Override
    public void loadBakings() {
        bakingRepo.load(new BakingDataSource.FetchBakingCallback() {
            @Override
            public void onFetched(List<Baking> bakings) {
                bakingListView.showBakings(bakings);
            }

            @Override
            public void onFailed(String errorMessage) {
                bakingListView.showError(errorMessage);
            }
        });
    }
}
