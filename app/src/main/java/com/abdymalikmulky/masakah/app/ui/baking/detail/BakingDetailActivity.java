package com.abdymalikmulky.masakah.app.ui.baking.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
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
import com.abdymalikmulky.masakah.app.ui.baking.detail.step.BakingStepDetailFragment;
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
    @BindView(R.id.list_baking_ingredi_step)
    RecyclerView listBakingIngrediSteps;
    @BindView(R.id.baking_step_detail_layout)
    NestedScrollView bakingStepDetailLayout;

    private Baking baking;

    private BakingDetailContract.Presenter bakingDetailPresenter;

    private List<Ingredient> ingredients;
    private List<Step> steps;

    private BakingIngredientAdapter bakingIngredientAdapter;
    private BakingStepAdapter bakingStepAdapter;


    private boolean isTwoPanel = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initPresenterAndRepo();

        if (findViewById(R.id.layout_step_detail) != null) {
            isTwoPanel = true;

            initListIngreSteps();

        } else {
            isTwoPanel = false;

            initListIngredients();

            initListSteps();
        }


        try {

            baking = (Baking) Parcels.unwrap(getIntent().getParcelableExtra(ConstantsUtil.INTENT_BAKING));

            Timber.d("DataBaking %s", baking.toString());
            Timber.d("DataBaking-ingredients %s", baking.toString());
            Timber.d("DataBaking-steps %s", baking.getIngredients().toString());
            Timber.d("DataBaking-steps %s", baking.getSteps().toString());
            setTitle(baking.getName());

            showBaking(baking);

            showIngredients(baking.getIngredients());

            showSteps(baking.getSteps());

            if(isTwoPanel) {
                setFragment(0);
            }

        } catch (NullPointerException e) {
            Timber.e(e.toString());
        }
    }

    private void initPresenterAndRepo() {
        bakingDetailPresenter = new BakingDetailPresenter(this);
    }

    private void initListIngreSteps() {
        steps = new ArrayList<>();
        ingredients = new ArrayList<>();

        listBakingIngrediSteps.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManagerStep = new LinearLayoutManager(this);
        listBakingIngrediSteps.setLayoutManager(mLayoutManagerStep);
        bakingStepAdapter = new BakingStepAdapter(steps, this);
        listBakingIngrediSteps.setAdapter(bakingStepAdapter);

        ingredients = new ArrayList<>();
        bakingIngredientAdapter = new BakingIngredientAdapter(ingredients);
        listBakingIngredients.setAdapter(bakingIngredientAdapter);
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
        this.ingredients = ingredients;
        bakingIngredientAdapter.refresh(ingredients);
    }

    @Override
    public void showSteps(List<Step> steps) {
        this.steps = steps;
        bakingStepAdapter.refresh(steps);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onBakingStepClicked(Step step, int order) {
        if (isTwoPanel) {
            setFragment(order);
        } else {
            Intent detailIntent = new Intent(this, BakingStepDetailActivity.class);
            detailIntent.putExtra(ConstantsUtil.INTENT_BAKING_NAME, baking.getName());
            detailIntent.putExtra(ConstantsUtil.INTENT_BAKING_STEP_ORDER, order);
            detailIntent.putExtra(ConstantsUtil.INTENT_BAKING_STEPS, Parcels.wrap(steps));
            startActivity(detailIntent);
        }
    }

    private void setFragment(int order) {

        BakingStepDetailFragment bakingStepDetailFragment = BakingStepDetailFragment.newInstance(steps, order, true);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.layout_step_detail, bakingStepDetailFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("ARTICLE_SCROLL_POSITION",
                new int[]{ bakingStepDetailLayout.getScrollX(), bakingStepDetailLayout.getScrollY()});
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final int[] position = savedInstanceState.getIntArray("ARTICLE_SCROLL_POSITION");
        if(position != null)
            bakingStepDetailLayout.post(new Runnable() {
                public void run() {
                    bakingStepDetailLayout.scrollTo(position[0], position[1]);
                }
            });
    }

}
