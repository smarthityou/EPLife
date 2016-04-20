package com.cuit.zhh.eplife.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cuit.zhh.eplife.R;
import com.cuit.zhh.eplife.base.BaseActivity;
import com.cuit.zhh.eplife.bean.User;
import com.cuit.zhh.eplife.utils.AppManager;
import com.cuit.zhh.eplife.utils.CommonUtils;
import com.cuit.zhh.eplife.utils.FileUtils;
import com.cuit.zhh.eplife.view.popup.SlideFromBottomPopup;
import com.cuit.zhh.eplife.view.weight.LoadingDialog;
import com.cuit.zhh.eplife.view.weight.WinToast;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.sw926.imagefileselector.ImageCropper;
import com.sw926.imagefileselector.ImageFileSelector;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseActivity {
    private EditText et_account;//注册账号
    private EditText et_settingPwd;//设置密码
    private Button btn_regFinish;//注册完成
    private ImageFileSelector mImageFileSelector = new ImageFileSelector(this);//选取图片
    private ImageCropper mImageCropper = new ImageCropper(this);//截取图片
    private PorterShapeImageView iv_avatar;//班级icon
    private SlideFromBottomPopup slideFromBottomPopup;//底部popup
    private String icon_url = "";//班级图片地址
    private LoadingDialog loadingDialog;
    private File srcFile1;
    private File outFile1;
    private Toolbar toolbar;//自定义标题


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AppManager.getAppManager().addActivity(this);
        init();
    }

    private void init() {
        loadingDialog = new LoadingDialog(this);
        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        CommonUtils.toolbarShow(this, toolbar, false, "登陆", "注册", true, true, null);
        et_account = (EditText) findViewById(R.id.activity_reg_et_account);//手机号码
        et_settingPwd = (EditText) findViewById(R.id.activity_reg_et_settingPwd);//设置密码
        btn_regFinish = (Button) findViewById(R.id.activity_reg_btn_finish);//完成注册
        iv_avatar = (PorterShapeImageView) findViewById(R.id.activity_reg_img_avatar);

        btn_regFinish.setOnClickListener(this);
        iv_avatar.setOnClickListener(this);


        //初始化底部popupwindow
        slideFromBottomPopup = new SlideFromBottomPopup(this, mImageFileSelector);

        // 设置输出文件的尺寸
        mImageFileSelector.setOutPutImageSize(800, 600);
        // 设置保存图片的质量 0到100
        mImageFileSelector.setQuality(80);

        // 设置输出文件的宽高比
        mImageCropper.setOutPutAspect(1, 1);
        // 设置输出文件的尺寸
        mImageCropper.setOutPut(200, 200);
        // 设置是否缩放到指定的尺寸
        mImageCropper.setScale(true);

        mImageFileSelector.setCallback(new ImageFileSelector.Callback() {
            @Override
            public void onSuccess(final String file) {
                // 选取图片成功
//                Log.i("info", "图片地址:" + file);
                mImageCropper.cropImage(new File(file));
                mImageCropper.setCallback(new ImageCropper.ImageCropperCallback() {
                    @Override
                    public void onCropperCallback(ImageCropper.CropperResult result, File srcFile, File outFile) {
                        if (result == ImageCropper.CropperResult.success) {
                            iv_avatar.setImageBitmap(BitmapFactory.decodeFile(outFile.toString()));
                            //删除图片地址
                            srcFile1 = srcFile;
                            outFile1 = outFile;
                            icon_url = outFile.toString();
                            Log.i("info", "图片地址" + icon_url);
                            // 成功
                            slideFromBottomPopup.dismiss();
                        } else if (result == ImageCropper.CropperResult.error_illegal_input_file) {
                            // 输入的文件失败
                            WinToast.toast(RegisterActivity.this, "error_illegal_input_file");
                        } else if (result == ImageCropper.CropperResult.error_illegal_out_file) {
                            // 输出文件失败
                            WinToast.toast(RegisterActivity.this, "error_illegal_out_file");
                        }
                    }
                });
            }

            @Override
            public void onError() {
                // 选取图片失败
                WinToast.toast(RegisterActivity.this, "选取图片失败");
                slideFromBottomPopup.dismiss();
            }
        });


    }

    //处理页面点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_reg_btn_finish://注册完成
                //完成注册事件
                finshReg();
                break;
            case R.id.activity_reg_img_avatar:
                // 拍照选取
                slideFromBottomPopup.showPopupWindow();
                break;
            default:
                break;
        }
    }

    private void finshReg() {
        //两个输入框都不能为空才能完成注册验证
        if (CommonUtils.judgeEtIsNoEmpty(et_account) && CommonUtils.judgeEtIsNoEmpty(et_settingPwd)) {
            loadingDialog.show();

            BmobFile bmobFile = new BmobFile(new File(icon_url));
            bmobFile.upload(this, new UploadFileListener() {
                @Override
                public void onSuccess() {
                    WinToast.toast(RegisterActivity.this, "图片上传成功");
                    User user = new User();
                    user.setUsername(et_account.getText().toString());
                    user.setPassword(et_settingPwd.getText().toString());
                    user.setAvatar(new BmobFile(new File(icon_url)));
                    //调用第三方的注册方法注册
                    user.signUp(RegisterActivity.this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            FileUtils.deleteFile(outFile1);
                            FileUtils.deleteFile(srcFile1);
                            WinToast.toast(RegisterActivity.this, "注册成功，自动跳转到登陆页面");
                            //跳转登陆页面
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            //结束当前页面
                            AppManager.getAppManager().finishActivity(RegisterActivity.class);

                        }

                        @Override
                        public void onFailure(int code, String msg) {
                            loadingDialog.dismiss();
                            Log.e("info", "注册失败+code:" + code + "--msg:" + msg);
                        }
                    });
                }

                @Override
                public void onFailure(int code, String msg) {
                    Log.e("info", "上传图片失败+code:" + code + "--msg:" + msg);
                }
            });
        } else {
            WinToast.toast(RegisterActivity.this, "亲，您还有未填项哦");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mImageFileSelector.onActivityResult(requestCode, resultCode, data);
        mImageCropper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mImageFileSelector.onSaveInstanceState(outState);
        mImageCropper.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mImageFileSelector.onRestoreInstanceState(savedInstanceState);
        mImageCropper.onRestoreInstanceState(savedInstanceState);
    }

    // Android 6.0的动态权限
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mImageFileSelector.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
