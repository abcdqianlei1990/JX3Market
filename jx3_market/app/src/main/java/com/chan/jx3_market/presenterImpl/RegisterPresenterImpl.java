package com.chan.jx3_market.presenterImpl;

import android.util.Log;

import com.chan.jx3_market.bean.UserInfo;
import com.chan.jx3_market.modelimpl.RegisterModelImpl;
import com.chan.jx3_market.presenter.IRegisterPresenter;
import com.chan.jx3_market.viewimpl.RegisterActivity;

import constants.ToastMsg;

/**
 * Created by qianlei on 2016-03-28.19:37
 * class description:
 */
public class RegisterPresenterImpl implements IRegisterPresenter {

    private static RegisterActivity activity;
    private static RegisterPresenterImpl presenter;
    private static RegisterModelImpl model;

    private RegisterPresenterImpl() {
    }

    public static RegisterPresenterImpl getInstance(RegisterActivity ac) {
        if (presenter == null) {
            synchronized (RegisterPresenterImpl.class) {
                if (presenter == null) {
                    presenter = new RegisterPresenterImpl();
                    activity = ac;
                    model = new RegisterModelImpl(ac);
                }
            }
        }
        return presenter;
    }

    @Override
    public void register(UserInfo info) {
        if (info == null) {
            return;
        }
        //验证用户是否已经存在
        model.verifyUser(info);
        //如果该用户没被注册，那么进行注册
    }

    @Override
    public void handleRet(int ret) {
        Log.d("chan", "ret ==> " + ret);
        if (ret == 3) {
            activity.showToast(ToastMsg.USER_EXIST);
        }
    }

    @Override
    public void handleCallBack(int code) {
        if(0 == code){
            activity.showToast(ToastMsg.REGISTER_SUCCESS);
        }else {
            activity.showToast(ToastMsg.REGISTER_FAILURE);
        }
    }
}
