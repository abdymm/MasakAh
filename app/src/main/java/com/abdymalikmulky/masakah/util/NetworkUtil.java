package com.abdymalikmulky.masakah.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by abdymalikmulky on 1/28/17.
 */

public class NetworkUtil {


    public static final boolean isNetworkAvailable(Context context){
        ConnectivityManager ConnectionManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
