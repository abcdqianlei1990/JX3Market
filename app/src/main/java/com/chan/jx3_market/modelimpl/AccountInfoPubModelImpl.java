package com.chan.jx3_market.modelimpl;

import android.accounts.Account;
import android.app.Activity;

import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.model.IInfoPubModel;
import com.chan.jx3_market.presenterImpl.AccountInfoPubPresenterImpl;
import com.chan.jx3_market.viewimpl.AccountInfoPubActivity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by qianlei on 2016-04-01.14:29
 * class description:
 */
public class AccountInfoPubModelImpl implements IInfoPubModel {

    private AccountInfoPubActivity activity;
    private AccountInfoPubPresenterImpl presenter;

    public AccountInfoPubModelImpl(AccountInfoPubActivity activity,AccountInfoPubPresenterImpl presenter) {
        this.activity = activity;
        this.presenter = presenter;
    }

    @Override
    public boolean userPubInfoCheck(BmobObject obj) {
        AccountInfo info = (AccountInfo)obj;
        return false;
    }

    @Override
    public void insertToDB(BmobObject obj) {
        AccountInfo info = (AccountInfo)obj;
        info.setTableName("account_t");
        info.save(activity, new SaveListener() {
            @Override
            public void onSuccess() {
                activity.dismissLoadingDialog();
                presenter.onDataSaveSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                activity.dismissLoadingDialog();
                presenter.onDataSaveFailure(i,s);
            }
        });
    }
}
