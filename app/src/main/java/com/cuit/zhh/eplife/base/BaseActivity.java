package com.cuit.zhh.eplife.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.cuit.zhh.eplife.BroadcastReceiver.NetBroadcastReceiver;
import com.cuit.zhh.eplife.utils.AppManager;
import com.cuit.zhh.eplife.utils.NetUtil;


/**
 * 基础activity
 */
public class BaseActivity extends AppCompatActivity implements NetBroadcastReceiver.netEventHandler, OnClickListener {
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        AppManager.getAppManager().addActivity(this);
        NetBroadcastReceiver.mListeners.add(this);
    }

    @Override
    public void onNetChange() {
        if (NetUtil.getNetworkState(this) == NetUtil.NETWORN_NONE) {
//            WinToast.toast(mContext, "网络失去连接!!");
        } else {
//            WinToast.toast(mContext, "网络恢复正常!!");
        }
    }

    @Override
    public void onClick(View v) {

    }
}
