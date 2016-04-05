package com.chan.jx3_market.viewimpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chan.jx3_market.R;
import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.presenterImpl.AccountInfoListPresenterImpl;
import com.chan.jx3_market.view.IAccountInfoListActivity;

import java.util.ArrayList;

import base.BaseActivity;

/**
 * Created by qianlei on 2016-04-05.15:00
 * class description:
 */
public class AccountInfoListActivity extends BaseActivity implements IAccountInfoListActivity,View.OnClickListener{

    private AccountInfoListPresenterImpl presenter;
    private ArrayList<AccountInfo> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        presenter = AccountInfoListPresenterImpl.getInstance(this);
        initData();

    }

    public void initViews() {
        setContentView(R.layout.activity_accountinfo_list);
    }

    public void initData(){
        mData = presenter.initDataPool();
    }

    public static void jumpToThisActivity(Activity activity){
        Intent intent = new Intent(activity,AccountInfoListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onSuccess(ArrayList<AccountInfo> list) {

    }

    @Override
    public void onFailure(int code, String msg) {

    }
}
