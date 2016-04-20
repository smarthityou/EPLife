package com.cuit.zhh.eplife.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * User实体类（用于登陆、注册）
 */
public class User extends BmobUser {
    private BmobFile avatar;//头像  第三方独有的文件类型（头像先上传到服务器）

    public User() {
    }


    public BmobFile getAvatar() {
        return avatar;
    }

    public void setAvatar(BmobFile avatar) {
        this.avatar = avatar;
    }
}
