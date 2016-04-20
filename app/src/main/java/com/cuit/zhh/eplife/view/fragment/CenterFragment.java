package com.cuit.zhh.eplife.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cuit.zhh.eplife.R;
import com.cuit.zhh.eplife.bean.User;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import org.xutils.x;

import cn.bmob.v3.datatype.BmobFile;

/**
 * A simple {@link Fragment} subclass.
 */
public class CenterFragment extends Fragment {
    private static CenterFragment centerFragment;
    private PorterShapeImageView iv_avatar;
    private TextView tv_name;
    private View view;

    public CenterFragment() {
        // Required empty public constructor
    }

    public static CenterFragment getInstance() {
        if (centerFragment == null) {
            synchronized (EnvironmentalFragment.class) {
                if (centerFragment == null) {
                    centerFragment = new CenterFragment();
                }
            }
        }
        return centerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        centerFragment = this;
        view = inflater.inflate(R.layout.fragment_center, container, false);
        findView();
        bindEvent();
        return view;
    }

    //绑定监听
    private void bindEvent() {
    }

    //查找
    private void findView() {
        iv_avatar = (PorterShapeImageView) view.findViewById(R.id.fragment_center_img_avatar);
        tv_name = (TextView) view.findViewById(R.id.fragment_center_tv_name);
        User user = User.getCurrentUser(getActivity(), User.class);
        tv_name.setText(user.getUsername());
        BmobFile avatar = user.getAvatar();
        String fileUrl = avatar.getFileUrl(getActivity());
        x.image().bind(iv_avatar, fileUrl);
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
