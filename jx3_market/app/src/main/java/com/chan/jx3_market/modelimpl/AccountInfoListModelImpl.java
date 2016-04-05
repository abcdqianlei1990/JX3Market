package com.chan.jx3_market.modelimpl;

import android.util.Log;

import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.model.IAccountInfoListModel;
import com.chan.jx3_market.presenterImpl.AccountInfoListPresenterImpl;
import com.chan.jx3_market.viewimpl.AccountInfoListActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by qianlei on 2016-04-05.15:23
 * class description:
 */
public class AccountInfoListModelImpl implements IAccountInfoListModel {

    private AccountInfoListActivity activity;
    private AccountInfoListPresenterImpl presenter;

    public AccountInfoListModelImpl(AccountInfoListActivity activity) {
        this.activity = activity;
        presenter = AccountInfoListPresenterImpl.getInstance(activity);
    }

    @Override
    public void queryAll() {
        BmobQuery<AccountInfo> query = new BmobQuery<AccountInfo>("account_t");
        query.findObjects(activity, new FindListener<AccountInfo>() {
            @Override
            public void onSuccess(List<AccountInfo> list) {
                Log.d("chan","result==>"+list.toString());
            }

            @Override
            public void onError(int i, String s) {
                Log.d("chan","result==>"+s);
            }
        });
    }
}
