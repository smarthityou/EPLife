package com.cuit.zhh.eplife.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.cuit.zhh.eplife.BroadcastReceiver.NetBroadcastReceiver;
import com.cuit.zhh.eplife.R;
import com.cuit.zhh.eplife.api.Constant;
import com.cuit.zhh.eplife.base.BaseActivity;
import com.cuit.zhh.eplife.bean.User;
import com.cuit.zhh.eplife.utils.CommonUtils;
import com.cuit.zhh.eplife.utils.NetUtil;
import com.cuit.zhh.eplife.view.weight.LoadingDialog;
import com.cuit.zhh.eplife.view.weight.WinToast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * 注册页面
 */
public class LoginActivity extends BaseActivity{
    private Button regbtn;//注册按钮
    private Button see_pwd;//看见密码图标按钮
    private boolean isHidden = true;
    private EditText inputPwd_et;//密码输入框
    private EditText et_phone;//电话号码输入框
    private Button login_btn;//登陆。
    private LoadingDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        init();
        NetBroadcastReceiver.mListeners.add(this);
        mDialog = new LoadingDialog(this);
    }

    /**
     * 初始化界面
     */
    public void init() {
        regbtn = (Button) findViewById(R.id.activity_login_register);
        see_pwd = (Button) findViewById(R.id.see_pwd);
        login_btn = (Button) findViewById(R.id.activity_login_loginBtn);
        et_phone = (EditText) findViewById(R.id.activity_login_et_phoneNum);
        inputPwd_et = (EditText) findViewById(R.id.activity_login_pwd);
        see_pwd.setOnClickListener(this);
        regbtn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
    }

    //处理点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_register://注册
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.see_pwd: {//点击可视化密码
                if (isHidden) {//如果可见密码  就把可见密码图片改变为不可见，同时把密码设为不可见
                    see_pwd.setBackgroundResource(R.mipmap.nosee);
                    inputPwd_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    see_pwd.setBackgroundResource(R.mipmap.see);
                    inputPwd_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                isHidden = !isHidden;
                inputPwd_et.postInvalidate();
                Editable etext = inputPwd_et.getText();
                Selection.setSelection(etext, etext.length());
            }
            break;
            case R.id.activity_login_loginBtn: {//登陆按钮
                String user_phone = et_phone.getText().toString();
                String user_pwd = inputPwd_et.getText().toString();
                deal_login(user_phone, user_pwd);
            }
            break;
            default:
                break;
        }
    }

    //网络请求，处理登陆。
    private void deal_login(String user_phone, String user_pwd) {
        Log.i("info", "账号:" + user_phone + "密码:" + user_pwd);
        if (TextUtils.isEmpty(user_phone)) {
            CommonUtils.toastInfo(this, "账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(user_pwd)) {
            CommonUtils.toastInfo(this, "密码不能为空");
            return;
        }
        mDialog.show();
        if (NetUtil.getNetworkState(this) != NetUtil.NETWORN_NONE) {
            User user = new User();
            user.setUsername(user_phone);
            user.setPassword(user_pwd);
            user.login(this, new SaveListener() {
                @Override
                public void onSuccess() {
                    mDialog.dismiss();
                    WinToast.toast(LoginActivity.this, "登陆成功");
                    BmobUser currentUser = User.getCurrentUser(LoginActivity.this);
                    Log.i("info", "当前用户信息:" + currentUser.getUsername());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    mDialog.dismiss();
                    WinToast.toast(LoginActivity.this, "账号或者密码错误");
                    Log.i("info", "登陆失败:状态码:" + i + "&&错误原因:" + s);
                }
            });
        } else {
            WinToast.toast(this, "网络不可用");
        }
    }

    @Override
    public void onNetChange() {
        if (NetUtil.getNetworkState(this) == NetUtil.NETWORN_NONE) {
            CommonUtils.toastInfo(this, "网络不可用");
            Constant.NET = false;
        } else {
            CommonUtils.toastInfo(this, "网络可用");
            Constant.NET = true;
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (CommonUtils.isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
}

