package com.chan.jx3_market.presenterImpl;

import android.util.Log;

import com.chan.jx3_market.bean.UserInfo;
import com.chan.jx3_market.modelimpl.RegisterModelImpl;
import com.chan.jx3_market.presenter.IRegisterPresenter;
import com.chan.jx3_market.viewimpl.RegisterActivity;

import com.chan.jx3_market.constants.ToastMsg;

/**
 * Created by qianlei on 2016-03-28.19:37
 * class description:
 */
public class RegisterPresenterImpl implements IRegisterPresenter {

    private  RegisterActivity activity;
    private  RegisterModelImpl model;

    public RegisterPresenterImpl(RegisterActivity ac) {
        this.activity = ac;
        model = new RegisterModelImpl(activity,this);
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
//            activity.showToast(activity,ToastMsg.USER_EXIST);
        }
    }

    @Override
    public void handleCallBack(int code) {
        if(0 == code){

        }else {

        }
    }

    @Override
    public void onRegisterSuccess() {
        activity.onRegisterSuccess();
    }

    @Override
    public void onRegisterFailure(int code) {
        activity.onRegisterFailure(code);
    }
}
