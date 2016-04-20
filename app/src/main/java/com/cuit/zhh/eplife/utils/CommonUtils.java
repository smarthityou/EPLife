package com.cuit.zhh.eplife.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cuit.zhh.eplife.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.image.ImageOptions;

/**
 * 工具类
 */
public class CommonUtils {
    /**
     *
     * @param context
     * @param info
     */
    public static void toastInfo(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    //处理服务器返回json的数据
    public static int dealJson(String jsonString) {
        if (!TextUtils.isEmpty(jsonString)) {
            int status = 0;
            try {
                JSONObject obj = new JSONObject(jsonString);
                status = obj.optInt("status");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return status;
        } else {
            //不会为空
        }
        return 0;
    }

    /**
     * ImageOptions
     *
     * @return
     */
    public static ImageOptions getOptions() {
        ImageOptions options = new ImageOptions.Builder()
                .setIgnoreGif(true)//忽略gif
                .setAnimation(null)//设置动画效果
                .setCircular(false)
                .setSquare(true)
                .setUseMemCache(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_loading_defalut)//设置加载中图片
                .setFailureDrawableId(R.mipmap.ic_loading_defalut)//设置失败的图片
                .build();
        return options;
    }

    /**
     * 是否隐藏键盘
     *
     * @param v
     * @param event
     * @return
     */
    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断输入框是否为空
     *
     * @param et 输入框
     * @return
     */
    public static boolean judgeEtIsNoEmpty(EditText et) {
        return et.getText().toString().length() > 0 ? true : false;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean judgeStrIsEmpty(String str) {
        if (!TextUtils.isEmpty(str) && !"".equals(str)) {//��Ϊ��
            return false;
        } else//Ϊ��
            return true;
    }

    /**
     * 顶部toolbar展示
     *
     * @param activity
     * @param tb
     * @param btn_back_Is_Gone        返回键是否隐藏
     * @param btn_back_text           返回键位置的文字
     * @param toolbar_center_title    toolbar中心标题
     * @param btn_right_is_Gone       toolbar右边按钮是否隐藏
     * @param btn_right_title_is_Gone toolbar右边tv是否隐藏
     * @param right_title             如果null则不显示
     */
    public static void toolbarShow(final Activity activity, Toolbar tb, boolean btn_back_Is_Gone,
                                   String btn_back_text, String toolbar_center_title,
                                   boolean btn_right_is_Gone, boolean btn_right_title_is_Gone,
                                   String right_title) {
        Toolbar toolbar;//�toolbar
        Button toolbar_back = null;//toolbar返回键
        TextView toolbar_title = null;//toolbar中心title
        Button toolbar_right_btn = null;//右边btn
        TextView toolbar_right_tv = null;//右边tv

        //查找控件
        toolbar = tb;
        toolbar_back = (Button) toolbar.findViewById(R.id.toolbar_back);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_right_btn = (Button) toolbar.findViewById(R.id.toolbar_right_btn);
        toolbar_right_tv = (TextView) toolbar.findViewById(R.id.toolbar_right_tv);
        //返回键
        if (btn_back_Is_Gone) {
            toolbar_back.setVisibility(View.GONE);

        } else {
            toolbar_back.setVisibility(View.VISIBLE);
            toolbar_back.setText(btn_back_text);
            toolbar_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                }
            });
        }
        //toolbar中心
        toolbar_title.setText(toolbar_center_title);
        //隐藏
        if (btn_right_is_Gone) {
            toolbar_right_btn.setVisibility(View.GONE);
        } else {
            toolbar_right_btn.setVisibility(View.VISIBLE);
        }
        //右边tv
        if (btn_right_title_is_Gone) {
            toolbar_right_tv.setVisibility(View.GONE);
        } else {
            toolbar_right_tv.setVisibility(View.VISIBLE);
            toolbar_right_tv.setText(right_title);
        }
    }
}
