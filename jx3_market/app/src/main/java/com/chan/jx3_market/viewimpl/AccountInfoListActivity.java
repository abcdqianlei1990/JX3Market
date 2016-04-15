package com.chan.jx3_market.viewimpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chan.jx3_market.R;
import com.chan.jx3_market.bean.AccountEntity;
import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.presenterImpl.AccountInfoListPresenterImpl;
import com.chan.jx3_market.view.IAccountInfoListActivity;

import java.util.ArrayList;

import adapter.AccountListAdapter;
import base.BaseActivity;
import listener.FooterViewClickListener;
import listener.RecyclerViewItemClickListener;

/**
 * Created by qianlei on 2016-04-05.15:00
 * class description:
 */
public class AccountInfoListActivity extends BaseActivity implements IAccountInfoListActivity, RecyclerViewItemClickListener, FooterViewClickListener{

    private AccountInfoListPresenterImpl presenter;
    private ArrayList<AccountInfo> mData = new ArrayList<AccountInfo>();
    private AccountListAdapter adapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    TextView mFooter;

    //数据偏移量
    private int mRecord = 0;

    private long lastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        presenter = new AccountInfoListPresenterImpl(this);
        initData();

    }

    public void initViews() {
        setContentView(R.layout.activity_accountinfo_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.accountinfo_list_sp);
        mRecyclerView = (RecyclerView) findViewById(R.id.accountinfo_list_recyclerview);
    }

    public void initData(){
        initDataPoolByPresenter(0);
        adapter = new AccountListAdapter(this,mData);
        adapter.setOnClickListener(this);
        adapter.setOnFooterClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRecord = 0;
                initDataPoolByPresenter(mRecord);
            }
        });
    }

    public static void jumpToThisActivity(Activity activity){
        Intent intent = new Intent(activity,AccountInfoListActivity.class);
        activity.startActivity(intent);
    }


    @Override
    public void onSuccess(AccountEntity entity) {
//        mData.addAll(list);
        int total = entity.getTotal();
        ArrayList list = entity.getList();
        if(mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }

        if(total > entity.getList().size()){
            adapter.hasFooter(true);
        }else{
            adapter.hasFooter(false);
        }

        Log.d("chan", "mRecord = " + mRecord);
        if(mRecord == 0){
            mData.clear();
        }
        mData.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(int code, String msg) {

    }

    @Override
    public void onClick() {
        Log.d("chan","item is clicked...");
    }

    @Override
    public void onFooterViewClick(View v) {
        Log.d("chan","footer is clicked..."+v.getId());
        mFooter = (TextView) v.findViewById(R.id.footer);
//        tv.setText("加载中。。。");
        long currentTimeMillis = System.currentTimeMillis();
        if(currentTimeMillis - lastClickTime > 500){
            mRecord = mData.size();
//            mFooter.setVisibility(View.GONE);
//            mFooter.setClickable(false);
            initDataPoolByPresenter(mRecord);
//            mFooter.setVisibility(View.VISIBLE);
//            mFooter.setClickable(true);
        }
        lastClickTime = currentTimeMillis;
    }

    public void initDataPoolByPresenter(int record){
        mSwipeRefreshLayout.setRefreshing(true);
        presenter.initDataPool(record);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
