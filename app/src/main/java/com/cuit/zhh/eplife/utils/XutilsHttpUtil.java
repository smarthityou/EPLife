package com.cuit.zhh.eplife.utils;


import android.util.Log;

import com.cuit.zhh.eplife.gm_interface.GetResult;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Xutils进一步封装(xutils第三方网络请求包)
 */
public class XutilsHttpUtil {
    static RequestParams params;

    public static void post(RequestParams params, final GetResult getResult) {
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("info", "返回数据为" + result);
                getResult.getJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
