package com.chan.jx3_market.util;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import com.chan.jx3_market.base.BaseEntity;

/**
 * Created by qianlei on 2016-04-06.10:38
 * class description:
 */
public class JsonUtil {

//    public static <T>T parseEntity(Class<T> clazz,String json){
//        String ret = "{ \"list\": " + json + "}";
//        return JSON.parseObject(ret, clazz);
//    }

    public static <T>List<T> parseArray(Class<T> clazz, String json){
//        String ret = "{" + json + "}";
        ArrayList<T> infos = (ArrayList<T>) JSON.parseArray(json,clazz);
        if(infos != null && infos.size() > 0){
            BaseEntity<T> entity = new BaseEntity<T>();
            entity.setList(infos);
            entity.setTotal(infos.size());
        }
        return JSON.parseArray(json,clazz);
    }

    public static <T>BaseEntity<T> parseEntity(Class<T> clazz, String json){
//        String ret = "{" + json + "}";
        ArrayList<T> infos = (ArrayList<T>) JSON.parseArray(json,clazz);
        if(infos != null && infos.size() > 0){
            BaseEntity<T> entity = new BaseEntity<T>();
            entity.setList(infos);
            entity.setTotal(infos.size());
            return entity;
        }else
            return null;
    }
}
