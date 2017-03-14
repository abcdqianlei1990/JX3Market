package com.chan.jx3_market.presenter;

import com.chan.jx3_market.bean.AccountEntity;
import com.chan.jx3_market.bean.AccountInfo;

import java.util.ArrayList;

/**
 * Created by qianlei on 2016-04-05.15:15
 * class description:
 */
public interface IAccountInfoListPresenter {
    public ArrayList<AccountInfo> initDataPool(int record);
    public void onSuccess(AccountEntity entity);
    public void onFailure(int code,String msg);
}
