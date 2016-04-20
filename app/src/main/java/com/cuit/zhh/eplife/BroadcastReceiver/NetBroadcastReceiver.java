package com.cuit.zhh.eplife.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cuit.zhh.eplife.AllApplication;
import com.cuit.zhh.eplife.utils.NetUtil;

import java.util.ArrayList;

/**
 * 广播
 */
public class NetBroadcastReceiver extends BroadcastReceiver {
    public static ArrayList<netEventHandler> mListeners = new ArrayList<netEventHandler>();
    private static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(NET_CHANGE_ACTION)) {
            Log.i("info", "network_change");
            AllApplication.mNetWorkState = NetUtil.getNetworkState(context);
            if (mListeners.size() > 0)// 通知接口完成加载
            {
                Log.i("info", "network_change:" + mListeners.size());
                for (netEventHandler handler : mListeners) {
                    handler.onNetChange();
                }
            } else {
                Log.i("info", "network_change+size:0");
            }
        }
    }

    public static abstract interface netEventHandler {

        public abstract void onNetChange();
    }
}
