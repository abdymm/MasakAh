package com.abdymalikmulky.masakah.app.data.baking;

import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 8/17/17.
 */

public class BakingRepo implements BakingDataSource {

    private BakingLocal bakingLocal;
    private BakingRemote bakingRemote;

    public BakingRepo(BakingLocal bakingLocal, BakingRemote bakingRemote) {
        this.bakingLocal = bakingLocal;
        this.bakingRemote = bakingRemote;
    }

    @Override
    public void load(final FetchBakingCallback callback) {
        bakingRemote.load(new FetchBakingCallback() {
            @Override
            public void onFetched(List<Baking> bakings) {

                saveOnLocal(bakings);

                callback.onFetched(bakings);

            }

            @Override
            public void onFailed(String errorMessage) {

                callback.onFailed(errorMessage);

            }
        });
    }

    private void saveOnLocal(List<Baking> bakings) {
        for (Baking baking : bakings) {
            //if exist on db local, dont save
            if(!bakingLocal.isExist(baking.getId())) {

                bakingLocal.save(baking);

            }
        }
    }
}
