package com.abdymalikmulky.masakah.app.data.baking;

import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;

import java.util.List;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 8/17/17.
 */

public interface BakingDataSource {

    interface FetchBakingCallback {
        void onFetched(List<Baking> bakings);
        void onFailed(String errorMessage);
    }

    void load(FetchBakingCallback callback);

}
