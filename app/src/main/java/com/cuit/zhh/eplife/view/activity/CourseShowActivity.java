package com.cuit.zhh.eplife.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cuit.zhh.eplife.R;
import com.cuit.zhh.eplife.bean.CourseShow;
import com.cuit.zhh.eplife.utils.CommonUtils;
import com.cuit.zhh.eplife.utils.DataUtils;
import com.cuit.zhh.eplife.view.weight.WinToast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

public class CourseShowActivity extends AppCompatActivity {
    private Toolbar tb;
    private GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_show);
        ini();
    }

    private void ini() {
        tb = (Toolbar) findViewById(R.id.activity_course_show_toolba);
        CommonUtils.toolbarShow(this, tb, false, "返回", "教程", true, true, null);
        gv = (GridView) findViewById(R.id.activity_course_show_gv);
        RequestParams params = new RequestParams("http://m.shougongke.com/index.php?c=Course&a=newCourseList&gcate=cate&cate_id=1&pub_time=week&order=hot&vid=16&channel=shougongke_004");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ArrayList<CourseShow> courseShows = DataUtils.ParseJsonToCourseShowList(result);
                Log.i("info", "size" + courseShows.size());
                gv.setAdapter(new MAdapter(courseShows));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("info", "ex:" + ex.toString());
                WinToast.toast(CourseShowActivity.this, "获取数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //显示详情的适配器
    class MAdapter extends BaseAdapter {
        ArrayList<CourseShow> courseShows;

        public MAdapter(ArrayList<CourseShow> courseShows) {
            this.courseShows = courseShows;
        }

        @Override
        public int getCount() {
            return courseShows.size();
        }

        @Override
        public CourseShow getItem(int position) {
            return courseShows.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CourseShow courseShow = getItem(position);
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_course_show, null);
                new MViewHolder(convertView);
            }

            MViewHolder holder = (MViewHolder) convertView.getTag();
//            holder.ll_all;
            x.image().bind(holder.iv, courseShow.getHost_pic(), CommonUtils.getOptions());
            holder.name.setText(courseShow.getSubject());
            holder.author_name.setText(courseShow.getUser_name());
            holder.collect_view.setText(courseShow.getView() + "人气/" + courseShow.getCollect() + "收藏");
            holder.ll_bg.setBackgroundColor(Color.parseColor(courseShow.getBg_color()));
            return convertView;
        }

        class MViewHolder {
            LinearLayout ll_all;
            LinearLayout ll_bg;
            ImageView iv;
            TextView name;
            TextView author_name;
            TextView collect_view;

            public MViewHolder(View view) {
                ll_all = (LinearLayout) view.findViewById(R.id.item_course_show_ll);
                ll_bg = (LinearLayout) view.findViewById(R.id.item_course_show_ll_bg);
                iv = (ImageView) view.findViewById(R.id.item_course_show_iv_course);
                name = (TextView) view.findViewById(R.id.item_course_show_tv_name);
                author_name = (TextView) view.findViewById(R.id.item_course_show_tv_author_name);
                collect_view = (TextView) view.findViewById(R.id.item_course_show_tv_collect_view);
                view.setTag(this);
            }
        }
    }
}
