package com.abdymalikmulky.masakah.app.data.baking;

import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;
import com.abdymalikmulky.masakah.util.EndpointUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 8/17/17.
 */

public interface BakingApi {

    @GET(EndpointUtil.GET_BAKING_URL)
    public Call<List<Baking>> getAll();

}
