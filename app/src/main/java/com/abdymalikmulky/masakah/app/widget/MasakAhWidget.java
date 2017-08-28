package com.abdymalikmulky.masakah.app.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.abdymalikmulky.masakah.R;
import com.abdymalikmulky.masakah.app.data.baking.BakingLocal;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;
import com.abdymalikmulky.masakah.app.widget.baking.UpdateWidgetService;
import com.abdymalikmulky.masakah.util.ConstantsUtil;

import static android.content.Context.MODE_PRIVATE;

/**
 * Implementation of App Widget functionality.
 */
public class MasakAhWidget extends AppWidgetProvider {

    private int bakingId;

    private Baking baking;
    private BakingLocal bakingLocal;


    RemoteViews remoteViews;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            RemoteViews remoteViews = updateWidgetListView(context);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    private RemoteViews updateWidgetListView(Context context) {
        //which layout to show on widget
        remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.masak_ah_widget);

        SharedPreferences prefs = context.getSharedPreferences("pref", MODE_PRIVATE);
        bakingId = prefs.getInt(ConstantsUtil.INTENT_BAKING_ID, 0);


        bakingLocal = new BakingLocal();
        baking = bakingLocal.getBaking(bakingId);
        remoteViews.setTextViewText(R.id.titleWidget, baking.getName());

        //RemoteViews Service needed to provide adapter for ListView
        Intent svcIntent = new Intent(context, UpdateWidgetService.class);
        remoteViews.setRemoteAdapter(R.id.listViewWidget, svcIntent);
        //setting an empty view in case of no data
        remoteViews.setEmptyView(R.id.listViewWidget, R.id.empty_view);
        return remoteViews;
    }

    @Override
    public void onEnabled(Context context) {




    }

    @Override
    public void onDisabled(Context context) {
        Toast.makeText(context, "Good Bye Fellas", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

    }

}

