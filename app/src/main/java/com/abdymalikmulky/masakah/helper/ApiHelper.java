package com.abdymalikmulky.masakah.helper;

import com.abdymalikmulky.masakah.BuildConfig;
import com.abdymalikmulky.masakah.util.DateTimeUtil;
import com.abdymalikmulky.masakah.util.EndpointUtil;
import com.abdymalikmulky.masakah.util.InterceptorUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abdymalikmulky on 1/27/17.
 */

public class ApiHelper {
    public static final String BASE_URL = EndpointUtil.BASE_URL;
    private static Retrofit retrofit = null;

    private static OkHttpClient client;

    public static Retrofit client() {

        Gson gson = new GsonBuilder()
                .setDateFormat(DateTimeUtil.DATE_FORMAT)
                .setLenient()
                .create();



        //add cache to the client
        client = new OkHttpClient.Builder()
                .build();
        if(BuildConfig.DEBUG) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(InterceptorUtil.getLoggingInterceptor())
                    .build();
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }



}
