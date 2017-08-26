package com.abdymalikmulky.masakah.app.widget.baking;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Bismillahirrahmanirrahim
 * Created by abdymalikmulky on 8/26/17.
 */

public class UpdateWidgetService extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new BakingIngredientListProvider(this.getApplicationContext()));
    }

}

