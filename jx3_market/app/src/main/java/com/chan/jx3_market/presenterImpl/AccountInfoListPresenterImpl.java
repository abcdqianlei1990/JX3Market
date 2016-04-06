package com.chan.jx3_market.presenterImpl;

import com.chan.jx3_market.bean.AccountEntity;
import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.modelimpl.AccountInfoListModelImpl;
import com.chan.jx3_market.presenter.IAccountInfoListPresenter;
import com.chan.jx3_market.viewimpl.AccountInfoListActivity;

import java.util.ArrayList;

/**
 * Created by qianlei on 2016-04-05.15:17
 * class description:
 */
public class AccountInfoListPresenterImpl implements IAccountInfoListPresenter {

    private static AccountInfoListActivity activity;
    private static AccountInfoListModelImpl model;
    private static volatile AccountInfoListPresenterImpl presenter;

    private ArrayList<AccountInfo> data;

    private AccountInfoListPresenterImpl() {
    }

    public static AccountInfoListPresenterImpl getInstance(final AccountInfoListActivity ac){
        if(presenter == null){
            synchronized(LoginPresenterImpl.class){
                if(presenter == null){
                    presenter = new AccountInfoListPresenterImpl();
                    activity = ac;
                    model = new AccountInfoListModelImpl(ac);
                }
            }
        }
        return presenter;
    }

    @Override
    public ArrayList<AccountInfo> initDataPool(int record) {
        model.queryAll(record);
        return null;
    }

    @Override
    public void onSuccess(AccountEntity entity) {
        activity.onSuccess(entity);
    }

    @Override
    public void onFailure(int code, String msg) {
        activity.onFailure(code,msg);
    }
}
