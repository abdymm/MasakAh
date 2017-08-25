package com.abdymalikmulky.masakah.app.ui.baking.detail.step;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.abdymalikmulky.masakah.R;
import com.abdymalikmulky.masakah.app.data.baking.pojo.Step;
import com.abdymalikmulky.masakah.util.ConstantsUtil;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

import static com.abdymalikmulky.masakah.util.ConstantsUtil.INTENT_BAKING_STEP_ORDER;
import static com.abdymalikmulky.masakah.util.ConstantsUtil.INTENT_BAKING_STEP_TWO_PANEL;

/**
 * A simple {@link Fragment} subclass.
 */
public class BakingStepDetailFragment extends Fragment {

    private OnBakingStepWizardListener onBakingStepWizardListener;

    @BindView(R.id.baking_step_detail_video)
    SimpleExoPlayerView bakingStepDetailVideo;
    @BindView(R.id.baking_step_detail_order)
    TextView bakingStepDetailOrder;
    @BindView(R.id.baking_step_detail_short_desc)
    TextView bakingStepDetailShortDesc;
    @BindView(R.id.baking_step_detail_desc)
    TextView bakingStepDetailDesc;
    @BindView(R.id.baking_step_detail_prev)
    Button bakingStepDetailPrev;
    @BindView(R.id.baking_step_detail_next)
    Button bakingStepDetailNext;

    Unbinder unbinder;

    private List<Step> steps;
    private Step step;
    private int currentStep;
    private int totalStep;
    private boolean isTwoPanel;

    private DataSource.Factory mediaDataSourceFactory;
    private SimpleExoPlayer player;
    private DefaultTrackSelector trackSelector;
    private boolean shouldAutoPlay;
    private BandwidthMeter bandwidthMeter;

    public BakingStepDetailFragment() {

    }

    public static BakingStepDetailFragment newInstance(List<Step> steps, int order, boolean isTwoPanel) {
        BakingStepDetailFragment fragment = new BakingStepDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(INTENT_BAKING_STEP_ORDER, order);
        bundle.putBoolean(INTENT_BAKING_STEP_TWO_PANEL, isTwoPanel);
        bundle.putSerializable(ConstantsUtil.INTENT_BAKING_STEPS, (Serializable) steps);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_baking_step_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shouldAutoPlay = true;
        bandwidthMeter = new DefaultBandwidthMeter();
        mediaDataSourceFactory = new DefaultDataSourceFactory(getActivity(), Util.getUserAgent(getActivity(), "mediaPlayerSample"), (TransferListener<? super DataSource>) bandwidthMeter);

        steps = (List<Step>) getArguments().getSerializable(ConstantsUtil.INTENT_BAKING_STEPS);

        if(savedInstanceState != null) {
            currentStep = savedInstanceState.getInt(INTENT_BAKING_STEP_ORDER);
        } else {
            currentStep = getArguments().getInt(INTENT_BAKING_STEP_ORDER);
        }

        totalStep = steps.size();
        isTwoPanel = getArguments().getBoolean(INTENT_BAKING_STEP_TWO_PANEL, false);

        step = steps.get(currentStep);


        setData(step);
        toggleNextPrevButton();


        if(!isTwoPanel) {
            try {
                onBakingStepWizardListener = (OnBakingStepWizardListener) getActivity();
            } catch (ClassCastException e) {
                Timber.e("Error-class %s", e.toString());
            }
        }
    }

    private void toggleNextPrevButton() {
        if(isTwoPanel) {
            bakingStepDetailPrev.setVisibility(View.GONE);
            bakingStepDetailNext.setVisibility(View.GONE);
        } else {
            if(currentStep == 0) {
                bakingStepDetailPrev.setVisibility(View.GONE);
            } else if(currentStep == steps.size()-1){
                bakingStepDetailNext.setVisibility(View.GONE);
            }
        }
    }

    @OnClick(R.id.baking_step_detail_prev)
    public void prev(View view) {
        currentStep--;
        onBakingStepWizardListener.onNextPrevStep(currentStep);
    }

    @OnClick(R.id.baking_step_detail_next)
    public void next(View view) {
        currentStep++;
        onBakingStepWizardListener.onNextPrevStep(currentStep);
    }

    private void initializePlayer(String url) {


        bakingStepDetailVideo.requestFocus();

        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        player = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
        bakingStepDetailVideo.setPlayer(player);
        player.setPlayWhenReady(shouldAutoPlay);

        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(url),
                mediaDataSourceFactory, extractorsFactory, null, null);
        player.prepare(mediaSource);
    }

    private void releasePlayer() {
        if (player != null) {
            shouldAutoPlay = player.getPlayWhenReady();

            player.stop();
            player.release();
            player = null;
            trackSelector = null;
        }
    }

    private void setData(Step step) {
        bakingStepDetailOrder.setText(String.valueOf(step.getId() + 1));
        bakingStepDetailShortDesc.setText(step.getShortDescription());
        bakingStepDetailDesc.setText(step.getDescription());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (player == null) {
            if(!step.getVideoURL().equals("")) {
                initializePlayer(step.getVideoURL());
            } else {
                bakingStepDetailVideo.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();

    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ConstantsUtil.INTENT_BAKING_STEP_ORDER, currentStep);
    }
}
