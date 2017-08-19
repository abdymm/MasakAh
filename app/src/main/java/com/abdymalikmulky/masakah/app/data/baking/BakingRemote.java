package com.abdymalikmulky.masakah.app.data.baking;


import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;
import com.abdymalikmulky.masakah.helper.ApiHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 8/17/17.
 */

public class BakingRemote implements BakingDataSource {

    BakingApi api;

    public BakingRemote() {
        this.api = ApiHelper.client().create(BakingApi.class);
    }


    @Override
    public void load(final FetchBakingCallback callback) {
        Call<List<Baking>> call = api.getAll();
        call.enqueue(new Callback<List<Baking>>() {
            @Override
            public void onResponse(Call<List<Baking>> call, Response<List<Baking>> response) {
                if(response.isSuccessful()) {
                    List<Baking> bakings = response.body();
                    Timber.d(bakings.toString());
                    callback.onFetched(bakings);
                } else {
                    Timber.e(response.errorBody().toString());
                    callback.onFailed(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Baking>> call, Throwable t) {
                Timber.e(t.getLocalizedMessage());
                callback.onFailed(t.getLocalizedMessage());
            }
        });

    }
}
