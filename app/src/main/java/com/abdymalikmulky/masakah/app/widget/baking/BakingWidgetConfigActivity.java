package com.abdymalikmulky.masakah.app.widget.baking;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.abdymalikmulky.masakah.R;
import com.abdymalikmulky.masakah.app.data.baking.BakingDataSource;
import com.abdymalikmulky.masakah.app.data.baking.BakingLocal;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;
import com.abdymalikmulky.masakah.app.ui.baking.list.BakingListAdapter;
import com.abdymalikmulky.masakah.util.ConstantsUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID;
import static android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID;

public class BakingWidgetConfigActivity extends AppCompatActivity implements OnClickWidgetListConfig {

    int mAppWidgetId;

    @BindView(R.id.widget_list_baking)
    RecyclerView widgetListBaking;

    //TODO: need to do MVP implementation
    private BakingLocal bakingLocal;

    private List<Baking> bakings;
    private BakingListAdapter bakingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_widget_config);
        ButterKnife.bind(this);

        setResult(RESULT_CANCELED);

        initListLayout();

        bakingLocal = new BakingLocal();
    }

    @Override
    protected void onStart() {
        super.onStart();

        bakingLocal.load(new BakingDataSource.FetchBakingCallback() {
            @Override
            public void onFetched(List<Baking> bakings) {
                bakingListAdapter.refresh(bakings);
            }

            @Override
            public void onFailed(String errorMessage) {
                Toast.makeText(BakingWidgetConfigActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListLayout() {
        widgetListBaking.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManagerIngredient = new LinearLayoutManager(this);
        widgetListBaking.setLayoutManager(mLayoutManagerIngredient);

        bakings = new ArrayList<>();
        bakingListAdapter = new BakingListAdapter(bakings, this);
        widgetListBaking.setAdapter(bakingListAdapter);
    }

    @Override
    public void onClick(Baking baking) {
        showAppWidget(baking);
    }

    private void showAppWidget(Baking baking) {
        mAppWidgetId = INVALID_APPWIDGET_ID;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(EXTRA_APPWIDGET_ID,
                    INVALID_APPWIDGET_ID);

            AppWidgetProviderInfo providerInfo = AppWidgetManager.getInstance(
                    getBaseContext()).getAppWidgetInfo(mAppWidgetId);

            setBakingWizard(baking.getId());

            Intent startService = new Intent(this, UpdateWidgetService.class);

            startService.putExtra(EXTRA_APPWIDGET_ID, mAppWidgetId);

            startService.setAction("FROM CONFIGURATION ACTIVITY");

            setResult(RESULT_OK, startService);
            startService(startService);

            finish();
        }
        if (mAppWidgetId == INVALID_APPWIDGET_ID) {
            Timber.d("serviceIntent-BakingId %s", "I am invalid");
            finish();
        }

    }

    private void setBakingWizard(int bakingId) {
        SharedPreferences.Editor editor = getSharedPreferences("pref", MODE_PRIVATE).edit();
        editor.putInt(ConstantsUtil.INTENT_BAKING_ID, bakingId);
        editor.apply();
    }

}
