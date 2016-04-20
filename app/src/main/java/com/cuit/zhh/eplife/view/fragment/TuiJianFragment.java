package com.cuit.zhh.eplife.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cuit.zhh.eplife.R;
import com.cuit.zhh.eplife.base.BaseFragment;
import com.cuit.zhh.eplife.view.activity.CourseShowActivity;
import com.cuit.zhh.eplife.view.weight.WinToast;

/**
 * 推荐fragment.
 */
public class TuiJianFragment extends BaseFragment {
    private static TuiJianFragment tuiJianFragment;

    private Button btn_piyi;
    private Button btn_buyi;
    private View view;

    public static TuiJianFragment getInstance() {
        if (tuiJianFragment == null) {
            synchronized (FocusFragment.class) {
                if (tuiJianFragment == null) {
                    tuiJianFragment = new TuiJianFragment();
                }
            }
        }
        return tuiJianFragment;
    }

    public TuiJianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tui_jian, container, false);
        init();
        return view;
    }

    private void init() {
        btn_buyi = (Button) view.findViewById(R.id.fg_tuijian_btn_buyi);
        btn_piyi = (Button) view.findViewById(R.id.fg_tuijian_btn_piyi);
        bindEvent();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.fg_tuijian_btn_buyi:
                startActivity(new Intent(getContext(), CourseShowActivity.class));
                break;
            case R.id.fg_tuijian_btn_piyi:
                WinToast.toast(getContext(), "暂时未写，工程师正在开发中");
                break;
            default:
                break;
        }
    }

    /**
     * 绑定监听事件
     */
    private void bindEvent() {
        btn_buyi.setOnClickListener(this);
        btn_piyi.setOnClickListener(this);
    }

}
