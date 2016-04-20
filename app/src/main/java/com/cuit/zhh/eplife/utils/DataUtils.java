package com.cuit.zhh.eplife.utils;

import com.cuit.zhh.eplife.bean.CourseShow;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 数据工具类
 */
public class DataUtils {
    public static ArrayList<CourseShow> ParseJsonToCourseShowList(String json) {
        if (!CommonUtils.judgeStrIsEmpty(json)) {
            try {
                JSONObject obj = new JSONObject(json);
                if (1 == CommonUtils.dealJson(json)) {
                    String data = obj.getJSONArray("data").toString();
                    Type type = new TypeToken<ArrayList<CourseShow>>() {
                    }.getType();
                    Gson gson = new Gson();
                    ArrayList<CourseShow> list = gson.fromJson(data, type);
                    return list;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<CourseShow>();
    }
}
