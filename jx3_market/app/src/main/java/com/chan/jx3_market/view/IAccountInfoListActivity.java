package com.chan.jx3_market.view;

import com.chan.jx3_market.bean.AccountInfo;

import java.util.ArrayList;

/**
 * Created by qianlei on 2016-04-05.15:09
 * class description:
 */
public interface IAccountInfoListActivity {
    public void onSuccess(ArrayList<AccountInfo> list);
    public void onFailure(int code,String msg);
}
