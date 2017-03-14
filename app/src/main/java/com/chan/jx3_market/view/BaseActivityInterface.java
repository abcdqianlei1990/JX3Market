package com.chan.jx3_market.view;

import base.BaseEntity;

/**
 * Created by qianlei on 2016-04-01.15:00
 * class description:
 */
public interface BaseActivityInterface {
    public void onDataGetSuccess(BaseEntity entity);
    public void onDataGetFailure(int code, String s);
}
