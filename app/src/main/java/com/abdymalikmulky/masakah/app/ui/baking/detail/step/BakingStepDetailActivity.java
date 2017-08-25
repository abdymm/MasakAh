package com.abdymalikmulky.masakah.app.ui.baking.detail.step;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.abdymalikmulky.masakah.R;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Step;
import com.abdymalikmulky.masakah.util.ConstantsUtil;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class BakingStepDetailActivity extends AppCompatActivity implements OnBakingStepWizardListener {

    private String bakingName;

    private List<Step> steps;
    private int order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_step);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {

            bakingName = getIntent().getExtras().getString(ConstantsUtil.INTENT_BAKING_NAME);
            order = getIntent().getExtras().getInt(ConstantsUtil.INTENT_BAKING_STEP_ORDER);
            steps = (ArrayList<Step>) Parcels.unwrap(getIntent().getParcelableExtra(ConstantsUtil.INTENT_BAKING_STEPS));


            setTitle(bakingName);

        } catch (NullPointerException e) {
            Timber.e(e.toString());
        }

        if(savedInstanceState == null) {
            initFragment();
        }
    }

    private void initFragment() {
        setFragment(order);
    }

    private void setFragment(int order) {

        BakingStepDetailFragment bakingStepDetailFragment = BakingStepDetailFragment.newInstance(steps, order, false);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.layout_step_detail, bakingStepDetailFragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public void onNextPrevStep(int order) {
        setFragment(order);
    }
}
