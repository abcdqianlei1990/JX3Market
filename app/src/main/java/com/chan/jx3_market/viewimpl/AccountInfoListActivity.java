package com.chan.jx3_market.viewimpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chan.jx3_market.R;
import com.chan.jx3_market.adapter.BottomMarginDecoration;
import com.chan.jx3_market.bean.AccountEntity;
import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.bean.UserInfo;
import com.chan.jx3_market.constants.Constants;
import com.chan.jx3_market.listener.OnContactGetBtnClickListener;
import com.chan.jx3_market.presenterImpl.AccountInfoListPresenterImpl;
import com.chan.jx3_market.util.TextUtil;
import com.chan.jx3_market.util.UIUtil;
import com.chan.jx3_market.view.IAccountInfoListActivity;

import java.util.ArrayList;

import com.chan.jx3_market.adapter.AccountListAdapter;
import com.chan.jx3_market.base.BaseActivity;
import com.chan.jx3_market.listener.FooterViewClickListener;
import com.chan.jx3_market.listener.RecyclerViewItemClickListener;

/**
 * Created by qianlei on 2016-04-05.15:00
 * class description:
 */
public class AccountInfoListActivity extends BaseActivity implements IAccountInfoListActivity, RecyclerViewItemClickListener, FooterViewClickListener,View.OnTouchListener,OnContactGetBtnClickListener{

    private AccountInfoListPresenterImpl presenter;
    private ArrayList<AccountInfo> mData = new ArrayList<AccountInfo>();
    private AccountListAdapter adapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    TextView mFooter;

    //数据偏移量
    private int mRecord = 0;

    private long lastClickTime = 0;

    private int mTotal = 0;

    int pointX;
    int pointY;
    private int currentOpenItemPos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        presenter = new AccountInfoListPresenterImpl(this);
        initData();

    }

    public void initViews() {
        addContentView(R.layout.activity_accountinfo_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.accountinfo_list_sp);
        mRecyclerView = (RecyclerView) findViewById(R.id.accountinfo_list_recyclerview);

        mRecyclerView.setOnTouchListener(this);
    }

    public void initData(){
        showLoadingPage();
        initDataPoolByPresenter(0);
        adapter = new AccountListAdapter(this,mData);
        adapter.setOnClickListener(this);
        adapter.setOnContactGetBtnClickListener(this);
        adapter.setOnFooterClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new BottomMarginDecoration(UIUtil.dip2px(this,15)));
        mRecyclerView.setAdapter(adapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("chan","AccountInfoListActivity ==>  recycler view on refresh ...");
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
        hideLoadingPage();
        mTotal = entity.getTotal();
        ArrayList list = entity.getList();
        if(mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }

        if(mTotal > entity.getList().size()){
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
        switch (code){
            case Constants.NETWORK_UNAVAILABLE:
                showEmptyView(R.mipmap.ic_launcher,"网络错误");
                break;
            default:
                showEmptyView(R.mipmap.ic_launcher,"暂无数据");
                break;
        }
    }

    @Override
    public void onGetUserInfoSuccess(UserInfo userInfo) {
        dismissLoadingDialog();
        showContactsDialog(userInfo);
    }

    @Override
    public void onGetUserInfoFailure(int code, String msg) {
        dismissLoadingDialog();
    }

    @Override
    public void onClick(View view,int position) {
        RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
        View itemView = manager.findViewByPosition(position);
        LinearLayout detailWrapper = (LinearLayout) itemView.findViewById(R.id.item_accountinfo_detail);
        if(currentOpenItemPos == -1){
            detailWrapper.setVisibility(View.VISIBLE);
            currentOpenItemPos = position;
        }else {
            if(currentOpenItemPos == position){
                detailWrapper.setVisibility(View.GONE);
                currentOpenItemPos = -1;
            }else {
                LinearLayout detailWrapper2 = (LinearLayout) manager.findViewByPosition(currentOpenItemPos).findViewById(R.id.item_accountinfo_detail);
                detailWrapper2.setVisibility(View.GONE);
                detailWrapper.setVisibility(View.VISIBLE);
                currentOpenItemPos = position;
            }
        }
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int id = v.getId();
        if(R.id.accountinfo_list_recyclerview == id){
            switch(action){
                case MotionEvent.ACTION_DOWN:
                    pointX = (int) event.getRawX();
                    pointY = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_UP:
                    int x =(int)event.getRawX();
                    int y =(int)event.getRawY();
                    int distanceX = Math.abs(x - pointX);
                    int distanceY = Math.abs(y - pointY);
                    Log.d("chan", "X轴移动：" + distanceX + "|Y轴移动：" + distanceY);

                    if(distanceY > 250){
//                        if(mTotal == mData.size()){
//                            showToast(mRecyclerView,"暂无更多数据");
//                        }else if(mTotal > mData.size()){
//                            initDataPoolByPresenter(mData.size());
//                        }
                    }
                    break;
            }
        }
        return false;
    }

    @Override
    public void onContactGetBtnClick(int position) {
        showLoadingDialog();
        String userId = mData.get(position).getUserId();
        presenter.getUserInfo(userId);
    }

    public void showContactsDialog(UserInfo info){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_user_contacts,null);
        builder.setView(view);
        TextView qqTv = (TextView) view.findViewById(R.id.dialog_user_contacts_qq);
        TextView yyTv = (TextView) view.findViewById(R.id.dialog_user_contacts_yy);
        TextView telTv = (TextView) view.findViewById(R.id.dialog_user_contacts_tel);
        String qq = info.getQq();
        String yy = info.getYy();
        String phone = info.getPhone();
        if(TextUtil.isEmpty(qq)){
            qqTv.setVisibility(View.GONE);
        }else {
            qqTv.setVisibility(View.VISIBLE);
            qqTv.setText("QQ : "+ qq);
        }
        if(TextUtil.isEmpty(yy)){
            yyTv.setVisibility(View.GONE);
        }else {
            qqTv.setVisibility(View.VISIBLE);
            yyTv.setText("YY : "+ yy);
        }
        if(TextUtil.isEmpty(phone)){
            telTv.setVisibility(View.GONE);
        }else {
            qqTv.setVisibility(View.VISIBLE);
            telTv.setText("TEL : "+ phone);
        }
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
