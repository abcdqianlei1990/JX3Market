package com.chan.jx3_market.viewimpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chan.jx3_market.R;
import com.chan.jx3_market.bean.GoldInfo;
import com.chan.jx3_market.presenterImpl.GoldInfoListPresenterImpl;
import com.chan.jx3_market.view.BaseActivityInterface;

import java.util.ArrayList;

import base.BaseActivity;
import base.BaseEntity;

/**
 * Created by ex-qianlei349 on 2016-06-02.
 */
public class GoldInfoListActivity extends BaseActivity implements BaseActivityInterface {

    private Toolbar mToolBar;

    private ArrayList<GoldInfo> mDataList = new ArrayList<GoldInfo>();
    private GoldInfoListPresenterImpl presenter;
    private int mRecord = 0;
    private int mTotal = 0;
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
        initToolBar();


    }

    public void initToolBar(){
        mToolBar = (Toolbar) findViewById(R.id.actity_goldinfo_toolbar);
        mToolBar.setTitle("金币信息列表");
        mToolBar.setTitleTextColor(getResources().getColor(R.color.cardview_light_background));
        mToolBar.inflateMenu(R.menu.goldinfo_menu);

        mToolBar.setNavigationIcon(R.mipmap.ic_launcher);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mToolBar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    public void initParams(){
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
        mDataList = entity.getList();
        mTotal = entity.getTotal();
    }

    @Override
    public void onDataGetFailure(int code, String s) {
//        showToast();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
