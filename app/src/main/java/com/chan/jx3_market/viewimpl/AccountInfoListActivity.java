package com.chan.jx3_market.viewimpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a.a.V;
import com.chan.jx3_market.BuildConfig;
import com.chan.jx3_market.R;
import com.chan.jx3_market.adapter.BottomMarginDecoration;
import com.chan.jx3_market.bean.AccountEntity;
import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.bean.ProfessionInfo;
import com.chan.jx3_market.bean.UserInfo;
import com.chan.jx3_market.constants.Constants;
import com.chan.jx3_market.listener.OnContactGetBtnClickListener;
import com.chan.jx3_market.presenterImpl.AccountInfoListPresenterImpl;
import com.chan.jx3_market.util.JsonUtil;
import com.chan.jx3_market.util.TextUtil;
import com.chan.jx3_market.util.UIUtil;
import com.chan.jx3_market.view.IAccountInfoListActivity;

import java.util.ArrayList;

import com.chan.jx3_market.adapter.AccountListAdapter;
import com.chan.jx3_market.base.BaseActivity;
import com.chan.jx3_market.listener.FooterViewClickListener;
import com.chan.jx3_market.listener.RecyclerViewItemClickListener;
import com.chan.jx3_market.widget.MyGridRecyclerView;

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
        showRightTv(true);
        setRightTvContent("筛选");
        mTitleRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(mTitleRightTv,"跳转到筛选页");
                showFilterDialog();
            }
        });
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

    private SparseArray<String> selectes = new SparseArray<>();
    private SparseArray<String> selecteBodyType = new SparseArray<>();
    private ArrayList<ProfessionInfo> mProfessionsData = new ArrayList<>();
    private ArrayList<String> professions = new ArrayList<>();
    private ArrayList<String> bodyTypeData = new ArrayList<>();   //在用户选择门派后再初始化该信息
    public void showFilterDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_filter,null);
        RelativeLayout title = (RelativeLayout) view.findViewById(R.id.dialog_filter_profession_title_wrapper);
        final TextView selectedTv = (TextView) view.findViewById(R.id.dialog_filter_profession_selected);
        final TextView arrowTv = (TextView) view.findViewById(R.id.dialog_filter_profession_arrow);
        final MyGridRecyclerView gridRecyclerView = (MyGridRecyclerView) view.findViewById(R.id.dialog_filter_profession_group);
        //body type
        RelativeLayout bodyTypeTitle = (RelativeLayout) view.findViewById(R.id.dialog_filter_bodyType_title_wrapper);
        final TextView bodyTypeSelectedTv = (TextView) view.findViewById(R.id.dialog_filter_bodyType_selected);
        final TextView bodyTypeArrowTv = (TextView) view.findViewById(R.id.dialog_filter_bodyType_arrow);
        final MyGridRecyclerView bodyTypeGridRecyclerView = (MyGridRecyclerView) view.findViewById(R.id.dialog_filter_bodyType_group);

        final TextView confirmBtn = (TextView) view.findViewById(R.id.dialog_filter_confirm_btn);
        final TextView resetBtn = (TextView) view.findViewById(R.id.dialog_filter_reset_btn);

        String currentSelects1 = getCurrentSelects(selectes);
        selectedTv.setText(currentSelects1);
        String currentSelects2 = getCurrentSelects(selecteBodyType);
        bodyTypeSelectedTv.setText(currentSelects2);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = gridRecyclerView.getVisibility();
                if(visibility == View.GONE){
                    gridRecyclerView.setVisibility(View.VISIBLE);
                    arrowTv.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_expand_less_black_24dp));
                }else {
                    gridRecyclerView.setVisibility(View.GONE);
                    arrowTv.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_expand_more_black_24dp));
                }
            }
        });
        bodyTypeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = bodyTypeGridRecyclerView.getVisibility();
                if(visibility == View.GONE){
                    bodyTypeGridRecyclerView.setVisibility(View.VISIBLE);
                    bodyTypeArrowTv.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_expand_less_black_24dp));
                }else {
                    bodyTypeGridRecyclerView.setVisibility(View.GONE);
                    bodyTypeArrowTv.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_expand_more_black_24dp));
                }
            }
        });
        //初始化门派信息
        if(professions.size() <= 0){
            mProfessionsData = JsonUtil.getProfession(this);
            ArrayList<String> professions = getProfessions(mProfessionsData);
            this.professions.addAll(professions);
        }
        gridRecyclerView.setSelectedData(selectes);
        gridRecyclerView.setArrayDataAndRefresh(professions);

        //初始化体型信息
        if(bodyTypeData.size() <= 0){
            ArrayList<String> tmp = new ArrayList();
            tmp.add(getResources().getString(R.string.body_type_1));
            tmp.add(getResources().getString(R.string.body_type_2));
            tmp.add(getResources().getString(R.string.body_type_3));
            tmp.add(getResources().getString(R.string.body_type_4));
            this.bodyTypeData.addAll(tmp);
        }
        bodyTypeGridRecyclerView.setSelectedData(selecteBodyType);
        bodyTypeGridRecyclerView.setArrayDataAndRefresh(bodyTypeData);

        gridRecyclerView.setOnRecyclerViewItemClickListener(new com.chan.jx3_market.listener.RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int postion) {
                String profession = professions.get(postion);
                String s = selectes.get(postion);
                if(s == null){
                    selectes.put(postion,profession);
                }else {
                    selectes.remove(postion);
                }
                String currentSelects = getCurrentSelects(selectes);
                selectedTv.setText(currentSelects);
            }
        });
        bodyTypeGridRecyclerView.setOnRecyclerViewItemClickListener(new com.chan.jx3_market.listener.RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int postion) {
                String profession = bodyTypeData.get(postion);
                String s = selecteBodyType.get(postion);
                if(s == null){
                    selecteBodyType.put(postion,profession);
                }else {
                    selecteBodyType.remove(postion);
                }
                String currentSelects = getCurrentSelects(selecteBodyType);
                bodyTypeSelectedTv.setText(currentSelects);
            }
        });

        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        dialog.show();
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtil.performClickAnimator(confirmBtn);
                dialog.dismiss();
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtil.performClickAnimator(resetBtn);
                selectes.clear();
                gridRecyclerView.clearCache();
                gridRecyclerView.setArrayDataAndRefresh(professions);

                selecteBodyType.clear();
                bodyTypeGridRecyclerView.clearCache();
                bodyTypeGridRecyclerView.setArrayDataAndRefresh(bodyTypeData);

                selectedTv.setText("");
                bodyTypeSelectedTv.setText("");
            }
        });
    }

    /**
     * 获取当前选中的值
     * @return
     */
    public String getCurrentSelects(SparseArray<String> list){
        StringBuffer sb = new StringBuffer();
        int size = list.size();
        for (int i = 0; i < size; i++){
            int key = list.keyAt(i);
            String s = list.get(key);
            sb.append(s);
            if(i != (size - 1)){
                sb.append("、");
            }
        }
        return sb.toString();
    }

    /**
     * 提取出门派信息
     * @param list
     * @return
     */
    public ArrayList<String> getProfessions(ArrayList<ProfessionInfo> list){
        ArrayList<String> res = new ArrayList<>();
        for (ProfessionInfo info:list){
            res.add(info.getProfession());
        }
        return res;
    }

    /**
     * 提取出门派对应心法信息
     * @param list
     * @return
     */
    public ArrayList<String> getSubProfessions(ArrayList<ProfessionInfo> list,@NonNull String profession){
        ArrayList<String> res = new ArrayList<>();
        for (ProfessionInfo info:list){
            if (profession.equals(info.getProfession())){
                res.addAll(info.getSubProfession());
                break;
            }
        }
        return res;
    }
}
