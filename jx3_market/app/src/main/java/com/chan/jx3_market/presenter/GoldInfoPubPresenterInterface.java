package com.chan.jx3_market.presenter;

import com.chan.jx3_market.bean.GoldInfo;

/**
 * Created by qianlei on 2016-04-01.14:24
 * class description:
 */
public interface GoldInfoPubPresenterInterface {
    public void performSubmitClickEvent(GoldInfo info);
    public void onDataSaveSuccess();
    public void onDataSaveFailure(int i, String s);
}
