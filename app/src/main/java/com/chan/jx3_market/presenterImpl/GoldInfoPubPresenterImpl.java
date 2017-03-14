package com.chan.jx3_market.presenterImpl;

import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.bean.GoldInfo;
import com.chan.jx3_market.modelimpl.AccountInfoPubModelImpl;
import com.chan.jx3_market.modelimpl.GoldInfoPubModelImpl;
import com.chan.jx3_market.presenter.GoldInfoPubPresenterInterface;
import com.chan.jx3_market.presenter.IAccountInfoPubPresenter;
import com.chan.jx3_market.viewimpl.AccountInfoPubActivity;
import com.chan.jx3_market.viewimpl.GoldInfoPubActivity;

/**
 * Created by qianlei on 2016-04-01.14:26
 * class description:
 */
public class GoldInfoPubPresenterImpl implements GoldInfoPubPresenterInterface {

    private GoldInfoPubActivity activity;
    private GoldInfoPubModelImpl model;

    public GoldInfoPubPresenterImpl(GoldInfoPubActivity ac) {
        activity = ac;
        model = new GoldInfoPubModelImpl(ac,this);
    }

    @Override
    public void performSubmitClickEvent(GoldInfo info) {
//        model.userPubInfoCheck(info);
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
