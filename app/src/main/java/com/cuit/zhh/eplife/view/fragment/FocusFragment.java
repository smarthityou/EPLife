package com.cuit.zhh.eplife.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuit.zhh.eplife.R;

/**
 * 关注的页面（fragment）
 */
public class FocusFragment extends Fragment {

    private static FocusFragment focusFragment;

    public static FocusFragment getInstance() {
        if (focusFragment == null) {
            synchronized (FocusFragment.class) {
                if (focusFragment == null) {
                    focusFragment = new FocusFragment();
                }
            }
        }
        return focusFragment;
    }


    public FocusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_focus, container, false);
    }

}
