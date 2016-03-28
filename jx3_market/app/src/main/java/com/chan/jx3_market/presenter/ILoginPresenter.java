package com.chan.jx3_market.presenter;

import com.chan.jx3_market.bean.UserInfo;

/**
 * Created by qianlei on 2016-03-28.14:39
 * class description:
 */
public interface ILoginPresenter {

    /**
     * 生命周期开始
     */
    public void onCreate();

    /**
     * 生命周期结束
     */
    public void onDestroy();

    /**
     * 注册
     */
    public void register(UserInfo info);

    /**
     * 登录
     */
    public void login(UserInfo info);
}
