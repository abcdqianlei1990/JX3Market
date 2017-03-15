package com.chan.jx3_market.presenterImpl;

import com.chan.jx3_market.modelimpl.GoldInfoListModelImpl;
import com.chan.jx3_market.presenter.GoldInfoListPresenterInterface;
import com.chan.jx3_market.viewimpl.GoldInfoListActivity;

import com.chan.jx3_market.base.BaseEntity;

/**
 * Created by qianlei on 2016-04-01.14:26
 * class description:
 */
public class GoldInfoListPresenterImpl implements GoldInfoListPresenterInterface {

    private GoldInfoListActivity activity;
    private GoldInfoListModelImpl model;

    public GoldInfoListPresenterImpl(GoldInfoListActivity ac) {
        activity = ac;
        model = new GoldInfoListModelImpl(ac,this);
    }


    @Override
    public void initDataPool(int record) {
        model.queryAll(record);
    }

    @Override
    public void onSuccess(BaseEntity entity) {
        activity.onDataGetSuccess(entity);
    }

    @Override
    public void onFailure(int code, String msg) {
        activity.onDataGetFailure(code,msg);
    }
}
