package com.chan.jx3_market.presenterImpl;

import android.app.Activity;

import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.modelimpl.AccountInfoPubModelImpl;
import com.chan.jx3_market.presenter.IAccountInfoPubPresenter;
import com.chan.jx3_market.viewimpl.AccountInfoPubActivity;

/**
 * Created by qianlei on 2016-04-01.14:26
 * class description:
 */
public class AccountInfoPubPresenterImpl implements IAccountInfoPubPresenter {

    private  AccountInfoPubActivity activity;
    private  AccountInfoPubModelImpl model;

    public AccountInfoPubPresenterImpl(AccountInfoPubActivity ac) {
        activity = ac;
        model = new AccountInfoPubModelImpl(ac,this);
    }

    @Override
    public void performSubmitClickEvent(AccountInfo info) {
        activity.showLoadingDialog();
        model.userPubInfoCheck(info);
        model.insertToDB(info);
    }

    @Override
    public void onDataSaveSuccess() {
        activity.onDataSaveSuccess();
    }

    @Override
    public void onDataSaveFailure(int i,String s) {
        activity.onDataSaveFailure(i,s);
    }

}
