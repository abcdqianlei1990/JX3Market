package com.chan.jx3_market.modelimpl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.chan.jx3_market.bean.AccountEntity;
import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.model.IAccountInfoListModel;
import com.chan.jx3_market.presenterImpl.AccountInfoListPresenterImpl;
import com.chan.jx3_market.viewimpl.AccountInfoListActivity;

import org.json.JSONArray;

import java.util.ArrayList;

import base.BaseEntity;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.SQLQueryListener;
import constants.Keys;
import util.JsonUtil;

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
    public void queryAll(int record) {
        final BmobQuery<AccountInfo> query = new BmobQuery<AccountInfo>("account_t");



        //当实体类名与表名一致的时候使用该方法查询，不用自己解析json
//        query.findObjects(activity, new FindListener<AccountInfo>() {
//            @Override
//            public void onSuccess(List<AccountInfo> list) {
//                Log.d("chan","result==>"+list.toString());
//            }
//
//            @Override
//            public void onError(int i, String s) {
//                Log.d("chan","result==>"+s);
//            }
//        });
        query.setSkip(record);
        query.setLimit(1);
        query.findObjects(activity, new FindCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {

                Log.d("chan", "result==>" + jsonArray.toString());
                //每次执行查询的时候，都统计当前表中数据条数，用来进行分页操作
                final BaseEntity<AccountInfo> entity = JsonUtil.parseEntity(BaseEntity.class,jsonArray.toString());
                countAll(entity);
            }

            @Override
            public void onFailure(int i, String s) {
                presenter.onFailure(i,s);
            }
        });
    }

    public void countAll(final BaseEntity<AccountInfo> et){
        final String bql = "select count(*) from account_t";
        BmobQuery<AccountInfo> query = new BmobQuery<AccountInfo>("account_t");
        query.doSQLQuery(activity, bql, new SQLQueryListener<AccountInfo>() {
            @Override
            public void done(BmobQueryResult<AccountInfo> bmobQueryResult, BmobException e) {
                if (e == null) {
                    int count = bmobQueryResult.getCount();//这里得到符合条件的记录数
                    AccountEntity entity = new AccountEntity();
                    entity.setTotal(count);
                    entity.setList(et.getList());
                    presenter.onSuccess(entity);
                } else {
                    Log.i("chan", "错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
                }
            }
        });
    }
}
