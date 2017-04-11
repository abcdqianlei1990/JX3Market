package com.chan.jx3_market.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.chan.jx3_market.base.BaseEntity;
import com.chan.jx3_market.bean.ProfessionInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public static String getJsonFromAssets(Context context, String fileName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toString();
    }

    public static ArrayList<ProfessionInfo> getProfession(Context context ){
        String json = getJsonFromAssets(context, "profession.json");
        ArrayList<ProfessionInfo> list = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0;i < jsonArray.length();i++){
                ProfessionInfo info = new ProfessionInfo();
                JSONObject o = (JSONObject) jsonArray.get(i);
                String profession = (String) o.get("profession");
                info.setProfession(profession);
                JSONArray array = o.getJSONArray("sub_profession");
                ArrayList<String> sublist = new ArrayList();
                for (int j = 0;j < array.length();j++){
                    Object o1 = array.get(j);
                    sublist.add(o1.toString());
                }
                info.setSubProfession(sublist);
                list.add(info);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
