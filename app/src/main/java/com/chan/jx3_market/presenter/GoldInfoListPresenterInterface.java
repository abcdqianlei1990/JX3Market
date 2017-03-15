package com.chan.jx3_market.presenter;

import com.chan.jx3_market.base.BaseEntity;

/**
 * Created by qianlei on 2016-04-05.15:15
 * class description:
 */
public interface GoldInfoListPresenterInterface {
    public void initDataPool(int record);
    public void onSuccess(BaseEntity entity);
    public void onFailure(int code, String msg);
}
