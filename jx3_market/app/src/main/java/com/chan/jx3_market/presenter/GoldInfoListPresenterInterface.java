package com.chan.jx3_market.presenter;

import com.chan.jx3_market.bean.AccountEntity;
import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.bean.GoldInfo;

import java.util.ArrayList;

import base.BaseEntity;

/**
 * Created by qianlei on 2016-04-05.15:15
 * class description:
 */
public interface GoldInfoListPresenterInterface {
    public void initDataPool(int record);
    public void onSuccess(BaseEntity entity);
    public void onFailure(int code, String msg);
}
