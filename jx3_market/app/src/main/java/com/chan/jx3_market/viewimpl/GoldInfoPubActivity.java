package com.chan.jx3_market.viewimpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chan.jx3_market.R;
import com.chan.jx3_market.bean.GoldInfo;
import com.chan.jx3_market.presenterImpl.GoldInfoPubPresenterImpl;
import com.chan.jx3_market.view.GoldInfoPubActivityInterface;

import base.BaseActivity;
import constants.Keys;
import util.AnimatorUtil;

/**
 * Created by ex-qianlei349 on 2016-05-31.
 *
 */
public class GoldInfoPubActivity extends BaseActivity implements GoldInfoPubActivityInterface,View.OnClickListener{

    private GoldInfoPubPresenterImpl presenter;

    EditText mCountEd;
    EditText mProportionEd;
    EditText mDealTypeEd;
    View mSubmit;
    RadioGroup mRadioGroup;
    RadioButton mRadioButton;

    //是否支持小额交易的标识
    private boolean mSupportLittleDeal = false;
    private String totalCount;
    private String proportion;
    private String dealType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        initParams();
    }

    public void initViews(){
        setContentView(R.layout.activity_goldinfo_pub);
        mCountEd = (EditText) findViewById(R.id.activity_gold_pub_count);
        mProportionEd = (EditText) findViewById(R.id.activity_gold_pub_proportion);
        mDealTypeEd = (EditText) findViewById(R.id.activity_gold_pub_deal_type);
        mSubmit = findViewById(R.id.activity_gold_pub_submit);
        mRadioGroup = (RadioGroup) findViewById(R.id.activity_gold_pub_radiogroup);
        mRadioButton = (RadioButton) findViewById(R.id.activity_gold_pub_radio1);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.activity_gold_pub_radio1){
                    mSupportLittleDeal = true;
                }else if(checkedId == R.id.activity_gold_pub_radio2){
                    mSupportLittleDeal = false;
                }
            }
        });
        mRadioButton.setChecked(true);

        mSubmit.setOnClickListener(this);
    }

    public void initParams(){
        presenter = new GoldInfoPubPresenterImpl(this);
    }

    public static void jumpToThisPage(Activity activity){
        Intent intent = new Intent(activity,GoldInfoPubActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onDataSaveSuccess() {
        showToast(mSubmit,"信息发布成功！");
    }

    @Override
    public void onDataSaveFailure(int i, String s) {
        showToast(mSubmit,i+":"+s);
    }

    @Override
    public void onClick(View v) {
        AnimatorUtil.performClickAnimator(mSubmit);
        inputCheck();
    }

    public void inputCheck(){
        totalCount = mCountEd.getText().toString();
        proportion = mProportionEd.getText().toString();
        dealType = mDealTypeEd.getText().toString();
        if(TextUtils.isEmpty(totalCount) || TextUtils.isEmpty(proportion) ||TextUtils.isEmpty(dealType)){
            showToast(mSubmit,"请填写完整的信息！");
        }else{
            GoldInfo info = new GoldInfo();
            info.setCount(totalCount);
            info.setDealType(dealType);
            info.setInfoType(Keys.PUBLISH_INFO_TYPE_GOLD);
            info.setProportion(proportion);
            info.setSupportLittleDeal(mSupportLittleDeal);
            info.setUsername(app.getUserInfo().getUsername());
            //插入发布者的联系方式
            info.setQq(app.getUserInfo().getQq());
            info.setYy(app.getUserInfo().getYy());
            info.setPhone(app.getUserInfo().getPhone());

            presenter.performSubmitClickEvent(info);
        }
    }
}
