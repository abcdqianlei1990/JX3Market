package com.chan.jx3_market.modelimpl;

import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.bean.GoldInfo;
import com.chan.jx3_market.model.IInfoPubModel;
import com.chan.jx3_market.presenterImpl.AccountInfoPubPresenterImpl;
import com.chan.jx3_market.presenterImpl.GoldInfoPubPresenterImpl;
import com.chan.jx3_market.viewimpl.AccountInfoPubActivity;
import com.chan.jx3_market.viewimpl.GoldInfoPubActivity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by qianlei on 2016-04-01.14:29
 * class description:
 */
public class GoldInfoPubModelImpl implements IInfoPubModel {

    private GoldInfoPubActivity activity;
    private GoldInfoPubPresenterImpl presenter;

    public GoldInfoPubModelImpl(GoldInfoPubActivity activity, GoldInfoPubPresenterImpl presenter) {
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
        GoldInfo info = (GoldInfo)obj;
//        info.setTableName("account_t");
        info.save(activity, new SaveListener() {
            @Override
            public void onSuccess() {
                presenter.onDataSaveSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                presenter.onDataSaveFailure(i,s);
            }
        });
    }
}
