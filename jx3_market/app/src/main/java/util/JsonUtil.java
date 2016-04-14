package util;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.util.List;

import base.BaseEntity;

/**
 * Created by qianlei on 2016-04-06.10:38
 * class description:
 */
public class JsonUtil {

    public static <T>T parseEntity(Class<T> clazz,String json){
        String ret = "{ \"list\": " + json + "}";
        return JSON.parseObject(ret, clazz);
    }

    public static <T>List<T> parseArray(Class<T> clazz, String json){
//        String ret = "{" + json + "}";
        return JSON.parseArray(json,clazz);
    }
}
