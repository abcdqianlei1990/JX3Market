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

    private static AccountInfoPubActivity activity;
    private static AccountInfoPubModelImpl model;
    private static volatile AccountInfoPubPresenterImpl presenter;

    private AccountInfoPubPresenterImpl() {
    }

    public static AccountInfoPubPresenterImpl getInstance(final AccountInfoPubActivity ac){
        if(presenter == null){
            synchronized(LoginPresenterImpl.class){
                if(presenter == null){
                    presenter = new AccountInfoPubPresenterImpl();
                    activity = ac;
                    model = new AccountInfoPubModelImpl(ac);
                }
            }
        }
        return presenter;
    }
    @Override
    public void performSubmitClickEvent(AccountInfo info) {
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
