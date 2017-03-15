package com.chan.jx3_market.viewimpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chan.jx3_market.R;
import com.chan.jx3_market.bean.GoldInfo;
import com.chan.jx3_market.presenterImpl.GoldInfoListPresenterImpl;
import com.chan.jx3_market.view.BaseActivityInterface;

import java.util.ArrayList;

import com.chan.jx3_market.adapter.GoldListAdapter;
import com.chan.jx3_market.base.BaseActivity;
import com.chan.jx3_market.base.BaseEntity;

/**
 * Created by ex-qianlei349 on 2016-06-02.
 */
public class GoldInfoListActivity extends BaseActivity implements BaseActivityInterface,BaseActivity.RecyclerViewItemClickListener{

    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;

    private ArrayList<GoldInfo> mDataList = new ArrayList<GoldInfo>();
    private GoldInfoListPresenterImpl presenter;
    private int mRecord = 0;
    private int mTotal = 0;
    private GoldListAdapter mAdapter;

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.ab_search:
                    showToast(mToolBar,"ab_search");
                    break;
                case R.id.action_share:
                    showToast(mToolBar,"action_share");
                    break;
            }
            return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity);
        initViews();
        initParams();
        initData();
    }

    public void initViews(){
        setContentView(R.layout.activity_goldinfo_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.actity_goldinfo_list);
        initToolBar();

    }

    public void initToolBar(){
        mToolBar = (Toolbar) findViewById(R.id.actity_goldinfo_toolbar);
        mToolBar.setTitle("金币信息列表");
        mToolBar.setTitleTextColor(getResources().getColor(R.color.cardview_light_background));
//        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.mipmap.ic_launcher);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolBar.setOnMenuItemClickListener(onMenuItemClickListener);
        mToolBar.inflateMenu(R.menu.goldinfo_menu);


    }

    public void initParams(){
        mAdapter = new GoldListAdapter(this,mDataList);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new GoldInfoListPresenterImpl(this);
    }

    public void initData(){
        presenter.initDataPool(mRecord);
    }

    public static void jumpToThisActivity(Activity activity){
        Intent intent = new Intent(activity,GoldInfoListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onDataGetSuccess(BaseEntity entity) {
        if(mRecord == 0){
            if(mDataList != null){
                mDataList.clear();
            }
        }
        mDataList.addAll(entity.getList());
        mTotal = entity.getTotal();

        refreshListView();
    }

    @Override
    public void onDataGetFailure(int code, String s) {
        showToast(mRecyclerView,s);
    }

    public void refreshListView(){
        Log.d("chan","@@@@@@@refreshListView");
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View v,int position) {
        Log.d("chan","@@@@@@@onItemClick");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_connection,null);
        TextView qq = (TextView) view.findViewById(R.id.dialog_connection_qq);
        TextView yy = (TextView) view.findViewById(R.id.dialog_connection_yy);
        TextView phone = (TextView) view.findViewById(R.id.dialog_connection_phone);

        GoldInfo info = mDataList.get(position);

        if(TextUtils.isEmpty(info.getQq())){
            qq.setVisibility(View.GONE);
        }else{
            qq.setVisibility(View.VISIBLE);
            qq.setText("QQ:"+info.getQq());
        }
        if(TextUtils.isEmpty(info.getYy())){
            yy.setVisibility(View.GONE);
        }else{
            yy.setVisibility(View.VISIBLE);
            yy.setText("YY:"+info.getYy());
        }
        if(TextUtils.isEmpty(info.getPhone())){
            phone.setVisibility(View.GONE);
        }else{
            phone.setVisibility(View.VISIBLE);
            phone.setText("电话:"+info.getPhone());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setView(view);
        builder.create().show();
    }
}
