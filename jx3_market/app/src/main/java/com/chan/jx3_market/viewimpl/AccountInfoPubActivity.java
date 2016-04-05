package com.chan.jx3_market.viewimpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chan.jx3_market.R;
import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.presenterImpl.AccountInfoPubPresenterImpl;
import com.chan.jx3_market.view.IAccountInfoPubActivity;


import base.BaseActivity;
import constants.Keys;
import util.AnimatorUtil;

/**
 * Created by qianlei on 2016-04-01.11:05
 * class description:
 */
public class AccountInfoPubActivity extends BaseActivity implements IAccountInfoPubActivity,View.OnClickListener{

    private EditText mProfession;
    private EditText mBodyType;
    private EditText mPvpOrPve;
    private EditText mLimit;
    private EditText mHair;
    private EditText mClothes;
    private EditText mMount;
    private EditText mExpScore;
    private EditText mScore;
    private EditText mFace;
    private EditText mPet;
    private EditText mZhanjie;
    private EditText mJJCLv;
    private EditText mCalling;
    private EditText mOther;
    private CardView mSubmit;

    private AccountInfoPubPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = AccountInfoPubPresenterImpl.getInstance(this);
        initViews();
    }

    public void initViews(){
        setContentView(R.layout.activity_accountinfo_pub);
        mProfession = (EditText) findViewById(R.id.account_pub_profession);
        mBodyType = (EditText) findViewById(R.id.account_pub_body);
        mPvpOrPve = (EditText) findViewById(R.id.account_pub_pvporpve);
        mLimit = (EditText) findViewById(R.id.account_pub_limit);
        mHair = (EditText) findViewById(R.id.account_pub_hair);
        mClothes = (EditText) findViewById(R.id.account_pub_clothes);
        mMount = (EditText) findViewById(R.id.account_pub_mount);
        mExpScore = (EditText) findViewById(R.id.account_pub_expscore);
        mScore = (EditText) findViewById(R.id.account_pub_score);
        mFace = (EditText) findViewById(R.id.account_pub_face);
        mPet = (EditText) findViewById(R.id.account_pub_pet);
        mZhanjie = (EditText) findViewById(R.id.account_pub_zhangjie);
        mJJCLv = (EditText) findViewById(R.id.account_pub_jjc);
        mCalling = (EditText) findViewById(R.id.account_pub_calling);
        mOther = (EditText) findViewById(R.id.account_pub_other);
        mSubmit = (CardView) findViewById(R.id.account_pub_submit);

        mSubmit.setOnClickListener(this);

    }
    public static void jumpToThisActivity(Activity activity){
        Intent intent = new Intent(activity,AccountInfoPubActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.account_pub_submit){
            AnimatorUtil.performClickAnimator(mSubmit);
            //先判断必填项有没有填
            if(inputCheckedOK()){
                //增加数据
                //每个用户每天只能发布一条信息，可以编辑发布的信息
                presenter.performSubmitClickEvent(packageInputInfo());
            }else{
                showToast(mSubmit,"小伙子，你有必填项没写啊");
            }
        }
    }

    public AccountInfo packageInputInfo(){
        AccountInfo info = new AccountInfo();
        info.setUsername(app.getInfo().getUsername());
        info.setProfession(mProfession.getText().toString().trim());
        info.setBodyType(mBodyType.getText().toString().trim());
        info.setPvpOrPve(mPvpOrPve.getText().toString().trim());
        info.setLimit(mLimit.getText().toString().trim());
        info.setHair(mHair.getText().toString().trim());
        info.setClothes(mClothes.getText().toString().trim());
        info.setMount(mMount.getText().toString().trim());
        info.setExpScore(mExpScore.getText().toString().trim());
        info.setScore(mScore.getText().toString().trim());
        info.setFace(mFace.getText().toString().trim());
        info.setPet(mPet.getText().toString().trim());
        info.setZhanjie(mZhanjie.getText().toString().trim());
        info.setJjcLv(mJJCLv.getText().toString().trim());
        info.setCalling(mCalling.getText().toString().trim());
        info.setOther(mOther.getText().toString().trim());
        info.setInfoType(Keys.PUBLISH_INFO_TYPE_ACCOUNT);
        return info;
    }

    /**
     * 检查必填项是否有填写
     * @return
     */
    public boolean inputCheckedOK(){
        String pro = mProfession.getText().toString().trim();
        String body = mBodyType.getText().toString().trim();
        String pv = mPvpOrPve.getText().toString().trim();
        String score = mScore.getText().toString().trim();
        if(TextUtils.isEmpty(pro)||TextUtils.isEmpty(body)||TextUtils.isEmpty(pv)||TextUtils.isEmpty(score)){
            return false;
        }
        return true;
    }

    @Override
    public void onDataSaveSuccess() {
        Log.d("chan","账号信息发布完成");
        showToast(mSubmit,"账号信息发布完成");
    }

    @Override
    public void onDataSaveFailure(int i, String s) {
        Log.d("chan","账号信息发布失败，code="+i+"| msg:"+s);
    }
}
