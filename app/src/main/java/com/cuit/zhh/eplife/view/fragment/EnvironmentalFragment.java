package com.cuit.zhh.eplife.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuit.zhh.eplife.R;

/**
 * 环保活动fragments
 */
public class EnvironmentalFragment extends Fragment {
    private static EnvironmentalFragment environmentalFragment;

    public EnvironmentalFragment() {
        // Required empty public constructor
    }
    //单例
    public static EnvironmentalFragment getInstance() {
        if (environmentalFragment == null) {
            synchronized (EnvironmentalFragment.class) {
                if (environmentalFragment == null) {
                    environmentalFragment = new EnvironmentalFragment();
                }
            }
        }
        return environmentalFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        environmentalFragment = this;
        return inflater.inflate(R.layout.fg_environmental, container, false);
    }
}
