package com.cuit.zhh.eplife.view.popup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

import com.cuit.zhh.eplife.R;
import com.cuit.zhh.eplife.base.BasePopupWindow;
import com.cuit.zhh.eplife.gm_interface.IntCallBack;
import com.sw926.imagefileselector.ImageFileSelector;

/**
 * 注册页面点击头像底部弹出popupwindow
 */
public class SlideFromBottomPopup extends BasePopupWindow implements View.OnClickListener {
    private View popupView;
    private ImageFileSelector mImageFileSelector;
    private IntCallBack callBack;

    public SlideFromBottomPopup(Activity context) {
        super(context);
        bindEvent();
    }

    public SlideFromBottomPopup(Activity context, IntCallBack callBack) {
        super(context);
        this.callBack = callBack;
        bindEvent();
    }

    public SlideFromBottomPopup(Activity context, ImageFileSelector mImageFileSelector) {
        super(context);
        this.mImageFileSelector = mImageFileSelector;
        bindEvent();
    }

    @Override
    protected Animation getShowAnimation() {
        return getTranslateAnimation(250 * 2, 0, 300);
    }

    //点击消失
    @Override
    protected View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    //获取布局
    @Override
    public View getPopupView() {
        popupView = LayoutInflater.from(mContext).inflate(R.layout.creat_class_bottom_popupwindow, null);
        return popupView;
    }

    @Override
    public View getAnimaView() {
        return popupView.findViewById(R.id.creat_class_bottom_popupwindow_ll);
    }

    //绑定点击事件
    private void bindEvent() {
        if (popupView != null) {
            popupView.findViewById(R.id.creat_class_bottom_popupwindow_btn_takePhoto).setOnClickListener(this);
            popupView.findViewById(R.id.creat_class_bottom_popupwindow_btn_photo_album).setOnClickListener(this);
            popupView.findViewById(R.id.bottom_popupwindow_btn_cancle).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_popupwindow_btn_cancle:
                dismiss();
                break;
            case R.id.creat_class_bottom_popupwindow_btn_takePhoto://拍照
                mImageFileSelector.takePhoto(mContext);
                break;
            case R.id.creat_class_bottom_popupwindow_btn_photo_album://相册选取
                mImageFileSelector.selectImage(mContext);
                break;
            default:
                break;
        }
    }
}
