package util;

import android.util.Log;

import com.alibaba.fastjson.JSON;

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
}
