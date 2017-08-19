package com.abdymalikmulky.masakah.app.ui.baking.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.abdymalikmulky.masakah.R;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Baking;
import com.abdymalikmulky.masakah.util.ConstantsUtil;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class BakingDetailActivity extends AppCompatActivity {

    @BindView(R.id.dumm_data)
    TextView dummData;
    private Baking baking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_detail);
        ButterKnife.bind(this);


        try {
            baking = (Baking) Parcels.unwrap(getIntent().getParcelableExtra(ConstantsUtil.INTENT_BAKING));
        } catch (NullPointerException e) {
            Timber.e(e.toString());
        }
        Timber.d("DataBake %s", baking.toString());
        dummData.setText(baking.toString());
    }
}
