package com.chan.jx3_market.view;

import android.view.View;

import com.chan.jx3_market.bean.UserInfo;

/**
 * Created by qianlei on 2016-03-25.19:35
 * class description:
 */
public interface ILogin {

    /**
     * 获取输入的用户名
     * @return
     */
    public String getName();

    /**
     * 获取输入的用户密码
     * @return
     */
    public String getPassword();

    public void onLoginSuccess(UserInfo info);

    public void onLoginFailure();

}
