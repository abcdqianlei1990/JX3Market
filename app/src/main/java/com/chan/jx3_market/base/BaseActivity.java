package com.chan.jx3_market.base;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chan.jx3_market.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qianlei on 2016-03-28.16:43
 * class description:
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    public JX3Application app;
    public LinearLayout mTitleArrowTv;
    public TextView mTitleLabelTv;
    public TextView mTitleRightTv;
    public AppCompatImageView mShareIcon;
    private FrameLayout mBaseContentView;
    @BindView(R.id.base_title)
    public View mTitleView;
    @BindView(R.id.loading_page)
    RelativeLayout mLoadingPage ;
    private View mChildActivityView;
    @BindView(R.id.base_empty_view)
    View mEmptyView;
    private static final int SHOW_TOAST = 1;
    private static final int HIDE_TOAST = 2;
    private android.app.AlertDialog mLoadingDialog;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW_TOAST:
                    showLoadingDialog();
                    break;
                case HIDE_TOAST:
                    dismissLoadingDialog();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = JX3Application.getInstance();
        initBaseView();
        initTitle();
        mTitleArrowTv.setOnClickListener(this);
    }

    public void showBackArrow(boolean b){
        if (b){
            mTitleArrowTv.setVisibility(View.VISIBLE);
        }else{
            mTitleArrowTv.setVisibility(View.GONE);
        }
    }

    public void addContentView(@LayoutRes int layoutResID){
        mChildActivityView = LayoutInflater.from(this).inflate(layoutResID, null, true);
        mBaseContentView.addView(mChildActivityView);
        ButterKnife.bind(this);
    }

    /**
     * 显示无数据页面
     * @param resID 图片
     * @param str 提示文案
     */
    public void showEmptyView(int resID,String str){
        mChildActivityView.setVisibility(View.GONE);
        hideLoadingPage();
        mEmptyView.setVisibility(View.VISIBLE);
        TextView img = (TextView) mEmptyView.findViewById(R.id.empty_page_image);
        TextView content = (TextView) mEmptyView.findViewById(R.id.empty_page_content);
        img.setBackgroundDrawable(getResources().getDrawable(resID));
        content.setText(str);
    }
    public void hideEmptyView(){
        mChildActivityView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    public void initBaseView(){
        setContentView(R.layout.activity_base);
        mBaseContentView = (FrameLayout) findViewById(R.id.activity_base_layout);
    }
    private void initTitle(){
        mTitleArrowTv = (LinearLayout) findViewById(R.id.title_arrow);
        mTitleLabelTv = (TextView) findViewById(R.id.title_label);
        mTitleRightTv = (TextView) findViewById(R.id.title_right_tv);
        mShareIcon = (AppCompatImageView) findViewById(R.id.title_share_icon);
        mTitleArrowTv.setOnClickListener(this);
    }
    public void setTitle(String title){
        mTitleLabelTv.setText(title);
    }
    public void setRightTvContent(String str){
        mTitleRightTv.setText(str);
    }
    public void showRightTv(boolean b){
        if (b){
            mTitleRightTv.setVisibility(View.VISIBLE);
        }else{
            mTitleRightTv.setVisibility(View.GONE);
        }
    }

    public void showLoadingDialog(){
        if(mLoadingDialog == null){
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setCancelable(false);
            mLoadingDialog = builder.create();
            mLoadingDialog.show();
            mLoadingDialog.setContentView(R.layout.loading);
            mLoadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }else {
            mLoadingDialog.show();
        }

    }
    public void dismissLoadingDialog(){
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
        }
    }

    public void showLoadingPage(){
        mLoadingPage.setVisibility(View.VISIBLE);
        mChildActivityView.setVisibility(View.GONE);
    }
    public void hideLoadingPage(){
        mLoadingPage.setVisibility(View.GONE);
        mChildActivityView.setVisibility(View.VISIBLE);
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    public void showToast(View view,String toastMsg) {
        if(!"非法用户".equals(toastMsg)){
            showToast(view,toastMsg, Toast.LENGTH_SHORT);
        }
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    private void showToast(View view,String toastMsg, int duration) {
//        Toast.makeText(this, toastMsg, duration).show();
        Snackbar snackbar = Snackbar.make(view, toastMsg, duration);
        View v = snackbar.getView();
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.title_arrow:
                finish();
                break;
        }
    }

    public interface RecyclerViewItemClickListener{
        public void onItemClick(View v,int position);
    }

}
