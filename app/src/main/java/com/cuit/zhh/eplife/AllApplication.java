package com.cuit.zhh.eplife;

import android.app.Application;
import android.content.Context;

import com.cuit.zhh.eplife.utils.NetUtil;

import org.xutils.x;

import cn.bmob.v3.Bmob;


public class AllApplication extends Application {
    private static Application mApplication;
    public static int mNetWorkState;
    public static Context context;


    public static synchronized Application getInstance() {
        if (mApplication == null) {
            mApplication = new Application();
        }
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志
        context = getApplicationContext();
        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, "df18ed23c50327eef269013c2b7af0e1");
    }

    /**
     * 获取网络状态
     */
    public void initData() {
        mNetWorkState = NetUtil.getNetworkState(this);
    }

}
