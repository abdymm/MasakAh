package com.abdymalikmulky.masakah.app.ui.baking.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.abdymalikmulky.masakah.R;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Ingredient;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Step;
import com.abdymalikmulky.masakah.app.ui.baking.detail.step.BakingStepDetailActivity;
import com.abdymalikmulky.masakah.util.ConstantsUtil;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class BakingDetailActivity extends AppCompatActivity implements BakingDetailContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.baking_detail_title_ingredient)
    TextView bakingDetailTitleIngredient;
    @BindView(R.id.list_baking_ingredients)
    RecyclerView listBakingIngredients;
    @BindView(R.id.baking_detail_title_step)
    TextView bakingDetailTitleStep;
    @BindView(R.id.list_baking_steps)
    RecyclerView listBakingSteps;

    private Baking baking;

    private BakingDetailContract.Presenter bakingDetailPresenter;

    private List<Ingredient> ingredients;
    private List<Step> steps;

    private BakingIngredientAdapter bakingIngredientAdapter;
    private BakingStepAdapter bakingStepAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initPresenterAndRepo();


        initListIngredients();

        initListSteps();

        try {

            baking = (Baking) Parcels.unwrap(getIntent().getParcelableExtra(ConstantsUtil.INTENT_BAKING));

            setTitle(baking.getName());

            showBaking(baking);

            showIngredients(baking.getIngredients());

            showSteps(baking.getSteps());

        } catch (NullPointerException e) {
            Timber.e(e.toString());
        }
    }

    private void initPresenterAndRepo() {
        bakingDetailPresenter = new BakingDetailPresenter(this);
    }

    private void initListIngredients() {
        listBakingIngredients.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManagerIngredient = new LinearLayoutManager(this);
        listBakingIngredients.setLayoutManager(mLayoutManagerIngredient);

        ingredients = new ArrayList<>();
        bakingIngredientAdapter = new BakingIngredientAdapter(ingredients);
        listBakingIngredients.setAdapter(bakingIngredientAdapter);

    }

    private void initListSteps() {
        listBakingSteps.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManagerStep = new LinearLayoutManager(this);
        listBakingSteps.setLayoutManager(mLayoutManagerStep);

        steps = new ArrayList<>();
        bakingStepAdapter = new BakingStepAdapter(steps, this);
        listBakingSteps.setAdapter(bakingStepAdapter);

    }


    @Override
    public void setPresenter(BakingDetailContract.Presenter presenter) {

    }

    @Override
    public void showBaking(Baking baking) {
        setTitle(baking.getName());
        toolbarLayout.setBackgroundResource(getResources().getIdentifier(baking.getImageDrawable(), "drawable", getPackageName()));
    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {
        bakingIngredientAdapter.refresh(ingredients);
    }

    @Override
    public void showSteps(List<Step> steps) {
        bakingStepAdapter.refresh(steps);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onBakingStepClicked(Step step) {
        Intent detailIntent = new Intent(this, BakingStepDetailActivity.class);
        detailIntent.putExtra(ConstantsUtil.INTENT_BAKING_NAME, baking.getName());
        detailIntent.putExtra(ConstantsUtil.INTENT_BAKING_STEP, Parcels.wrap(step));
        startActivity(detailIntent);
    }
}
