package com.chan.jx3_market.model;

import com.chan.jx3_market.bean.UserInfo;

/**
 * Created by qianlei on 2016-03-28.15:48
 * class description:
 */
public interface ILoginModel {

    /**
     * 用户信息验证
     * @return 返回验证结果code,成功返回0，未注册返回1，信息错误返回2（用户名或密码错误）
     */
    public void verifyUser(UserInfo info);
}
