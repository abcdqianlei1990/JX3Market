package com.chan.jx3_market.model;

import com.chan.jx3_market.bean.AccountInfo;

import java.util.ArrayList;

/**
 * Created by qianlei on 2016-04-05.15:22
 * class description:
 */
public interface IAccountInfoListModel {
    void queryAll(int record);
    void getUserInfo(String userName);
}
