package com.cuit.zhh.eplife.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.cuit.zhh.eplife.R;

/**
 * 首页
 */
public class HomeFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup rg;//推荐-关注--切换
    private Toolbar tb;
    private View view;
    private Context context;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private TuiJianFragment tuiJianFragment;
    private FocusFragment focusFragment;

    private Fragment[] fragments;
    private int index;
    private int currentTabIndex;

    private static HomeFragment homeFragment;

    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            synchronized (HomeFragment.class) {
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
            }
        }
        return homeFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_home, container, false);
        homeFragment = this;
        context = getActivity();
        init();
        bindEvent();
        return view;
    }

    /**
     * 初始化
     */
    private void init() {
        tb = (Toolbar) view.findViewById(R.id.fg_home_toolbar);
        rg = (RadioGroup) tb.findViewById(R.id.toolbar_hw_notice_rg);
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        tuiJianFragment = TuiJianFragment.getInstance();
        focusFragment = FocusFragment.getInstance();
        fragments = new Fragment[]{tuiJianFragment, focusFragment};
        transaction.add(R.id.fg_home_fl, tuiJianFragment).
                add(R.id.fg_home_fl, focusFragment).
                hide(focusFragment).
                commit();
    }

    /**
     * 绑定监听事件
     */
    private void bindEvent() {
        rg.setOnCheckedChangeListener(this);
    }

    /***
     * 推荐-关注切换
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.toolbar_hw_notice_rb_tuijian:
                index = 0;
//                WinToast.toast(context, "推荐");
                break;
            case R.id.toolbar_hw_notice_rb_focus:
                index = 1;
//                WinToast.toast(context, "关注");
                break;
            default:
                break;
        }

        if (currentTabIndex != index) {
            transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                transaction.add(R.id.activity_main_fl, fragments[index]);
            }
            transaction.show(fragments[index]).commit();
            currentTabIndex = index;
        }
    }
}
