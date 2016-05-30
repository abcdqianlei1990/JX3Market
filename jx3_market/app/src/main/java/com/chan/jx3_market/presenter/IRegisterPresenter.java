package com.chan.jx3_market.presenter;

import com.chan.jx3_market.bean.UserInfo;

/**
 * Created by qianlei on 2016-03-28.19:36
 * class description:
 */
public interface IRegisterPresenter {
    public void register(UserInfo info);
    public void handleRet(int ret);

    /**
     * 处理类似数据保存等操作的success或者failure
     * @param code success 0        failure -1
     */
    public void handleCallBack(int code);

    public void onRegisterSuccess();
    public void onRegisterFailure(int code);
}
