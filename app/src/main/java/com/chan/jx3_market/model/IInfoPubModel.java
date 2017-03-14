package com.chan.jx3_market.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by qianlei on 2016-04-01.14:29
 * class description:信息发布统一model
 */
public interface IInfoPubModel {

    /**
     * 同一类型信息，同一用户只可以发布一条
     * 如果
     */
    public boolean userPubInfoCheck(BmobObject obj);

    /**
     * 账号信息插入数据库
     */
    public void insertToDB(BmobObject obj);

}
