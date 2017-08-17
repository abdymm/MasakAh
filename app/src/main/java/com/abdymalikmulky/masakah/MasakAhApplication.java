package com.abdymalikmulky.masakah;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

import timber.log.Timber;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 8/17/17.
 */

public class MasakAhApplication extends Application {
    private static MasakAhApplication instance;

    public static MasakAhApplication getInstance() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(this);
        Timber.plant(new Timber.DebugTree());
    }
}
