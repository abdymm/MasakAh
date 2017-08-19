package com.abdymalikmulky.masakah.app.ui.baking.list;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.abdymalikmulky.masakah.R;
import com.abdymalikmulky.masakah.app.data.baking.BakingLocal;
import com.abdymalikmulky.masakah.app.data.baking.BakingRemote;
import com.abdymalikmulky.masakah.app.data.baking.BakingRepo;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;
import com.abdymalikmulky.masakah.app.ui.baking.detail.BakingDetailActivity;
import com.abdymalikmulky.masakah.util.ConstantsUtil;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BakingListActivity extends AppCompatActivity implements BakingListContract.View {

    @BindView(R.id.list_baking)
    RecyclerView listBaking;

    private BakingLocal bakingLocal;
    private BakingRemote bakingRemote;
    private BakingRepo bakingRepo;

    private BakingListContract.Presenter bakingListPresenter;

    private List<Baking> bakings;
    private BakingListAdapter bakingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_list);
        ButterKnife.bind(this);

        initPresenterAndRepo();

        initListLayout();
    }

    private void initPresenterAndRepo() {
        bakingLocal = new BakingLocal();
        bakingRemote = new BakingRemote();
        bakingRepo = new BakingRepo(bakingLocal, bakingRemote);

        bakingListPresenter = new BakingListPresenter(bakingRepo, this);
    }

    private void initListLayout() {
        bakings = new ArrayList<>();
        listBaking.setHasFixedSize(true);

        int columns = initListColumn();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, columns);

        listBaking.setLayoutManager(layoutManager);
        bakingListAdapter = new BakingListAdapter(bakings, this);
        listBaking.setAdapter(bakingListAdapter);
    }

    private int initListColumn() {
        int columns = 0;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            columns = 2;
        } else
        {
            columns = 1;
        }
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        if(isTablet) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            {
                columns = 3;
            } else
            {
                columns = 2;
            }
        }
        return columns;
    }

    @Override
    protected void onStart() {
        super.onStart();

        bakingListPresenter.loadBakings();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setPresenter(BakingListContract.Presenter presenter) {
        this.bakingListPresenter = presenter;
    }

    @Override
    public void showBakings(List<Baking> bakings) {
        this.bakings = bakings;
        bakingListAdapter.refresh(bakings);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBakingClicked(Baking baking) {
        Intent detailIntent = new Intent(this, BakingDetailActivity.class);
        detailIntent.putExtra(ConstantsUtil.INTENT_BAKING, Parcels.wrap(baking));
        startActivity(detailIntent);
    }
}
