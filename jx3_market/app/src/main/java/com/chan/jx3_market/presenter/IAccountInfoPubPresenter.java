package com.chan.jx3_market.presenter;

import com.chan.jx3_market.bean.AccountInfo;

/**
 * Created by qianlei on 2016-04-01.14:24
 * class description:
 */
public interface IAccountInfoPubPresenter {
    public void performSubmitClickEvent(AccountInfo info);
    public void onDataSaveSuccess();
    public void onDataSaveFailure(int i,String s);
}
