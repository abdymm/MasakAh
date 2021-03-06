/*
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.abdymalikmulky.masakah.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Bismillahirrahmanirrahim
 * abdymalikmulky
 * ------
 * Constant values reused in this sample.
 */
public class ConstantsUtil {

    //INTENT KEY
    public static final String INTENT_MASAK = "masak";
    public static final String INTENT_BAKING = "baking";
    public static final String INTENT_BAKING_ID = "baking_id";
    public static final String INTENT_BAKING_NAME = "baking_name";
    public static final String INTENT_BAKING_STEP = "baking_step";
    public static final String INTENT_BAKING_STEP_ORDER = "baking_step_order";
    public static final String INTENT_BAKING_STEPS = "baking_steps";
    public static final String INTENT_BAKING_STEP_TWO_PANEL = "two_panel";
    public static final String INTENT_BAKING_CURRENT_STEP = "current_step";

    /**
     LAYOUT VIEW
     **/
    public static void showHideLoadingList(ProgressBar pbLoading, RecyclerView rvList, TextView tvMsg,
                                           boolean showLoading, boolean showError) {
        if(showError) {
            pbLoading.setVisibility(View.GONE);
            rvList.setVisibility(View.GONE);
            tvMsg.setVisibility(View.VISIBLE);
        } else {
            tvMsg.setVisibility(View.GONE);
            if (showLoading) {
                pbLoading.setVisibility(View.VISIBLE);
                rvList.setVisibility(View.GONE);
            } else {
                pbLoading.setVisibility(View.GONE);
                rvList.setVisibility(View.VISIBLE);
            }
        }
    }
    public static void showErrorInLoadingList(TextView tvMsg, boolean show) {

    }
}
