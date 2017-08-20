package com.abdymalikmulky.masakah.app.ui.baking.detail;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 8/20/17.
 */

public class BakingDetailPresenter implements BakingDetailContract.Presenter {

    private BakingDetailContract.View bakingDetailView;

    public BakingDetailPresenter(BakingDetailContract.View bakingDetailView) {
        this.bakingDetailView = bakingDetailView;
        this.bakingDetailView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loadBaking() {

    }
}
