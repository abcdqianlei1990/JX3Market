package com.chan.jx3_market.modelimpl;

import android.util.Log;

import com.chan.jx3_market.bean.AccountEntity;
import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.bean.GoldInfo;
import com.chan.jx3_market.model.GoldInfoListModelInterface;
import com.chan.jx3_market.model.IAccountInfoListModel;
import com.chan.jx3_market.presenterImpl.AccountInfoListPresenterImpl;
import com.chan.jx3_market.presenterImpl.GoldInfoListPresenterImpl;
import com.chan.jx3_market.viewimpl.AccountInfoListActivity;
import com.chan.jx3_market.viewimpl.GoldInfoListActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import base.BaseEntity;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;
import util.JsonUtil;

/**
 * Created by qianlei on 2016-04-05.15:23
 * class description:
 */
public class GoldInfoListModelImpl implements GoldInfoListModelInterface {

    private GoldInfoListActivity activity;
    private GoldInfoListPresenterImpl presenter;

    public GoldInfoListModelImpl(GoldInfoListActivity activity, GoldInfoListPresenterImpl presenter) {
        this.activity = activity;
        this.presenter = presenter;
    }

    @Override
    public void queryAll(int record) {
        final BmobQuery<GoldInfo> query = new BmobQuery<GoldInfo>("gold_t");
        query.setSkip(record);
        query.setLimit(20);
        query.findObjects(activity, new FindCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Log.d("chan","金币信息列表model:"+jsonArray.toString());
                BaseEntity<GoldInfo> entity = JsonUtil.parseEntity(GoldInfo.class, jsonArray.toString());
                if(entity != null){
                    presenter.onSuccess(entity);
                }else{
                    presenter.onFailure(10001,"entity == null");
                }
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("chan",s);
                presenter.onFailure(i,s);
            }
        });

    }
}
