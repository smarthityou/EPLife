package com.cuit.zhh.eplife.bean;

/**
 * 教程显示bean
 */
public class CourseShow {

    /**
     * subject : 小清新M型口金包
     * hand_id : 307543
     * user_id : 17017618
     * collect : 189
     * view : 13214
     * host_pic : http://img3.shougongke.com/public/data/hand/201604/14/host/146061775373996_593.jpg
     * host_pic_color : #99A0AC
     * user_name : 柠檬树🌿
     * bg_color : #99A0AC
     * last_id : 1
     */

    private String subject;//名字
    private String hand_id;
    private String user_id;//教程的制作者id
    private String collect;//收藏
    private String view;//人气
    private String host_pic;//图片地址
    private String host_pic_color;//图片底部显示颜色
    private String user_name;//作者名字
    private String bg_color;////图片底部显示颜色
    private String last_id;//图片第几张

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHand_id() {
        return hand_id;
    }

    public void setHand_id(String hand_id) {
        this.hand_id = hand_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getHost_pic() {
        return host_pic;
    }

    public void setHost_pic(String host_pic) {
        this.host_pic = host_pic;
    }

    public String getHost_pic_color() {
        return host_pic_color;
    }

    public void setHost_pic_color(String host_pic_color) {
        this.host_pic_color = host_pic_color;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getBg_color() {
        return bg_color;
    }

    public void setBg_color(String bg_color) {
        this.bg_color = bg_color;
    }

    public String getLast_id() {
        return last_id;
    }

    public void setLast_id(String last_id) {
        this.last_id = last_id;
    }
}
