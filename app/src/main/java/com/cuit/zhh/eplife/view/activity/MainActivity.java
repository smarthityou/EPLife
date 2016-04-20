package com.cuit.zhh.eplife.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cuit.zhh.eplife.R;
import com.cuit.zhh.eplife.base.BaseActivity;
import com.cuit.zhh.eplife.utils.AppManager;
import com.cuit.zhh.eplife.view.fragment.CenterFragment;
import com.cuit.zhh.eplife.view.fragment.EnvironmentalFragment;
import com.cuit.zhh.eplife.view.fragment.HomeFragment;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private long waitTime = 2000;
    private long touchTime = 0;
    private RadioGroup radioGroup;//底部 “首页” 和 “活动” 和“我”切换的radioGroup
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;//fragment管理
    private FragmentTransaction transaction;

    private HomeFragment homeFragment;
    private EnvironmentalFragment environmentalFragment;
    private CenterFragment centerFragment;
    private Fragment[] fagments;
    private int index;
    private int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppManager.getAppManager().addActivity(this);
        init();
    }

    private void init() {
        radioGroup = (RadioGroup) findViewById(R.id.main_activity_botton_rg);
        frameLayout = (FrameLayout) findViewById(R.id.activity_main_fl);
        radioGroup.setOnCheckedChangeListener(this);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        homeFragment = HomeFragment.getInstance();
        environmentalFragment = EnvironmentalFragment.getInstance();
        centerFragment = CenterFragment.getInstance();
        fagments = new Fragment[]{homeFragment, environmentalFragment, centerFragment};
        transaction.add(R.id.activity_main_fl, homeFragment).
                add(R.id.activity_main_fl, environmentalFragment).
                add(R.id.activity_main_fl, centerFragment).
                hide(centerFragment).
                hide(environmentalFragment).
                commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTIme = System.currentTimeMillis();
            if ((currentTIme - touchTime) >= waitTime) {
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                touchTime = currentTIme;
            } else {
                AppManager.getAppManager().AppExit(this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                index = 0;
                break;
            case R.id.rb_activity:
                index = 1;
                break;
            case R.id.rb_center:
                index = 2;
                break;
            default:
                break;
        }
        if (currentTabIndex != index) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(fagments[currentTabIndex]);
            if (!fagments[index].isAdded()) {
                transaction.add(R.id.activity_main_fl, fagments[index]);
            }
            transaction.show(fagments[index]).commit();
            currentTabIndex = index;
        }
    }
}
