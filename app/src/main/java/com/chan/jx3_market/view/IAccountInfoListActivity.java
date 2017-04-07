package com.chan.jx3_market.view;

import com.chan.jx3_market.bean.AccountEntity;
import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.bean.UserInfo;

import java.util.ArrayList;

/**
 * Created by qianlei on 2016-04-05.15:09
 * class description:
 */
public interface IAccountInfoListActivity {
    public void onSuccess(AccountEntity entity);
    public void onFailure(int code,String msg);

    public void onGetUserInfoSuccess(UserInfo userInfo);
    public void onGetUserInfoFailure(int code, String msg);
}
