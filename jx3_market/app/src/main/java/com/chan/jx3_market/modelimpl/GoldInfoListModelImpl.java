package com.chan.jx3_market.modelimpl;

import android.util.Log;

import com.chan.jx3_market.bean.AccountEntity;
import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.model.IAccountInfoListModel;
import com.chan.jx3_market.presenterImpl.AccountInfoListPresenterImpl;
import com.chan.jx3_market.viewimpl.AccountInfoListActivity;

import org.json.JSONArray;

import java.util.ArrayList;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.SQLQueryListener;
import util.JsonUtil;

/**
 * Created by qianlei on 2016-04-05.15:23
 * class description:
 */
public class GoldInfoListModelImpl implements IAccountInfoListModel {

    private AccountInfoListActivity activity;
    private AccountInfoListPresenterImpl presenter;

    public GoldInfoListModelImpl(AccountInfoListActivity activity, AccountInfoListPresenterImpl presenter) {
        this.activity = activity;
        this.presenter = presenter;
    }

    @Override
    public void queryAll(int record) {
        final BmobQuery<AccountInfo> query = new BmobQuery<AccountInfo>("account_t");
        query.setSkip(record);
        query.setLimit(20);
//        String bql ="select count(*) from account_t";
//        query.doSQLQuery(activity, bql, new SQLQueryListener<AccountInfo>() {
//            @Override
//            public void done(BmobQueryResult<AccountInfo> bmobQueryResult, BmobException e) {
//                Log.d("chan", "count==>" + bmobQueryResult.getCount());
//            }
//        });

        query.findObjects(activity, new FindCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {

                Log.d("chan", "result==>" + jsonArray.toString());
                //每次执行查询的时候，都统计当前表中数据条数，用来进行分页操作
                ArrayList<AccountInfo> infos = (ArrayList<AccountInfo>) JsonUtil.parseArray(AccountInfo.class, jsonArray.toString());
                countAll(infos);
            }

            @Override
            public void onFailure(int i, String s) {
                presenter.onFailure(i,s);
            }
        });
    }

    public void countAll(final ArrayList<AccountInfo> et){
        final String bql = "select count(*) from account_t";
        BmobQuery<AccountInfo> query = new BmobQuery<AccountInfo>("account_t");
        query.doSQLQuery(activity, bql, new SQLQueryListener<AccountInfo>() {
            @Override
            public void done(BmobQueryResult<AccountInfo> bmobQueryResult, BmobException e) {
                if (e == null) {
                    int count = bmobQueryResult.getCount();//这里得到符合条件的记录数
                    AccountEntity entity = new AccountEntity();
                    entity.setTotal(count);
                    entity.setList(et);
                    presenter.onSuccess(entity);
                } else {
                    Log.i("chan", "错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
                }
            }
        });
    }
}
