package com.chan.jx3_market.viewimpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.chan.jx3_market.R;
import com.chan.jx3_market.bean.AccountInfo;
import com.chan.jx3_market.presenterImpl.AccountInfoPubPresenterImpl;
import com.chan.jx3_market.view.IAccountInfoPubActivity;


import com.chan.jx3_market.base.BaseActivity;
import com.chan.jx3_market.constants.Constants;
import com.chan.jx3_market.util.AnimatorUtil;

/**
 * Created by qianlei on 2016-04-01.11:05
 * class description:
 */
public class AccountInfoPubActivity extends BaseActivity implements IAccountInfoPubActivity,View.OnClickListener,View.OnFocusChangeListener{

    private EditText mProfession;
    private EditText mLimit;
    private EditText mHair;
    private EditText mClothes;
    private EditText mMount;
    private EditText mExpScore;
    private EditText mPveScore;
    private EditText mPvpScore;
    private EditText mFace;
    private EditText mPet;
    private EditText mZhanjie;
    private EditText mJJCLv;
    private EditText mCalling;
    private EditText mOther;
    private CardView mSubmit;
    private RadioGroup mBodyTypeRadioGroup;


    private AccountInfoPubPresenterImpl presenter;
    private int mBodyTypeInt = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AccountInfoPubPresenterImpl(this);
        initViews();
    }

    public void initViews(){
        setContentView(R.layout.activity_accountinfo_pub);
        mProfession = (EditText) findViewById(R.id.account_pub_profession);
        mLimit = (EditText) findViewById(R.id.account_pub_limit);
        mHair = (EditText) findViewById(R.id.account_pub_hair);
        mClothes = (EditText) findViewById(R.id.account_pub_clothes);
        mMount = (EditText) findViewById(R.id.account_pub_mount);
        mExpScore = (EditText) findViewById(R.id.account_pub_expscore);
        mPveScore = (EditText) findViewById(R.id.account_pub_pveScore);
        mPvpScore = (EditText) findViewById(R.id.account_pub_pvpScore);
        mFace = (EditText) findViewById(R.id.account_pub_face);
        mPet = (EditText) findViewById(R.id.account_pub_pet);
        mZhanjie = (EditText) findViewById(R.id.account_pub_zhangjie);
        mJJCLv = (EditText) findViewById(R.id.account_pub_jjc);
        mCalling = (EditText) findViewById(R.id.account_pub_calling);
        mOther = (EditText) findViewById(R.id.account_pub_other);
        mSubmit = (CardView) findViewById(R.id.account_pub_submit);
        mBodyTypeRadioGroup = (RadioGroup) findViewById(R.id.account_pub_rg);

        mProfession.addTextChangedListener(new MyTextChangedListener(mProfession));
        mPveScore.addTextChangedListener(new MyTextChangedListener(mPveScore));
        mPvpScore.addTextChangedListener(new MyTextChangedListener(mPvpScore));
        mExpScore.addTextChangedListener(new MyTextChangedListener(mExpScore));
        //设置焦点监控
        mProfession.setOnFocusChangeListener(this);
        mExpScore.setOnFocusChangeListener(this);
        mPveScore.setOnFocusChangeListener(this);
        mPvpScore.setOnFocusChangeListener(this);
        mZhanjie.setOnFocusChangeListener(this);
        mJJCLv.setOnFocusChangeListener(this);


        mSubmit.setOnClickListener(this);
        mBodyTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.account_pub_rb_1:
                        mBodyTypeInt = Constants.BodyType.male;
                        break;
                    case R.id.account_pub_rb_2:
                        mBodyTypeInt = Constants.BodyType.female;
                        break;
                    case R.id.account_pub_rb_3:
                        mBodyTypeInt = Constants.BodyType.boy;
                        break;
                    case R.id.account_pub_rb_4:
                        mBodyTypeInt = Constants.BodyType.girl;
                        break;
                    default:
                        break;
                }
            }
        });
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
        info.setUserId(app.getUserInfo().getObjectId());
        info.setProfession(mProfession.getText().toString().trim());
        info.setBodyType(mBodyTypeInt);
        info.setLimit(mLimit.getText().toString().trim());
        info.setHair(mHair.getText().toString().trim());
        info.setClothes(mClothes.getText().toString().trim());
        info.setMount(mMount.getText().toString().trim());
        info.setExpScore(mExpScore.getText().toString().trim());
        info.setPveScore(mPveScore.getText().toString().trim());
        info.setPvpScore(mPvpScore.getText().toString().trim());
        info.setFace(mFace.getText().toString().trim());
        info.setPet(mPet.getText().toString().trim());
        info.setZhanjie(mZhanjie.getText().toString().trim());
        info.setJjcLv(mJJCLv.getText().toString().trim());
        info.setCalling(mCalling.getText().toString().trim());
        info.setOther(mOther.getText().toString().trim());
        info.setInfoType(Constants.PUBLISH_INFO_TYPE_ACCOUNT);
        return info;
    }

    /**
     * 检查必填项是否有填写
     * @return
     */
    public boolean inputCheckedOK(){
        String pro = mProfession.getText().toString().trim();
        String pveScore = mPveScore.getText().toString().trim();
        String pvpScore = mPvpScore.getText().toString().trim();
        String exp = mExpScore.getText().toString().trim();
        String zhanjie = mZhanjie.getText().toString().trim();
        String jjc = mJJCLv.getText().toString().trim();
        if(TextUtils.isEmpty(pro)|| (mBodyTypeInt == -1) ||TextUtils.isEmpty(pveScore) ||TextUtils.isEmpty(pvpScore)
                || TextUtils.isEmpty(exp) || TextUtils.isEmpty(zhanjie) || TextUtils.isEmpty(jjc)){
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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id){
            case R.id.account_pub_profession:
                if(!hasFocus && TextUtils.isEmpty(mProfession.getText().toString().trim())){
                    mProfession.setError("该项为必填项");
                }
                break;
            case R.id.account_pub_pveScore:
                if(!hasFocus && TextUtils.isEmpty(mPveScore.getText().toString().trim())){
                    mPveScore.setError("该项为必填项");
                }
                break;
            case R.id.account_pub_pvpScore:
                if(!hasFocus && TextUtils.isEmpty(mPvpScore.getText().toString().trim())){
                    mPvpScore.setError("该项为必填项");
                }
                break;
            case R.id.account_pub_expscore:
                if(!hasFocus && TextUtils.isEmpty(mExpScore.getText().toString().trim())){
                    mExpScore.setError("该项为必填项");
                }
                break;
            case R.id.account_pub_zhangjie:
                if(!hasFocus && TextUtils.isEmpty(mZhanjie.getText().toString().trim())){
                    mZhanjie.setError("该项为必填项");
                }
                break;
            case R.id.account_pub_jjc:
                if(!hasFocus && TextUtils.isEmpty(mJJCLv.getText().toString().trim())){
                    mJJCLv.setError("该项为必填项");
                }
                break;
        }
    }

    public class MyTextChangedListener implements TextWatcher {
        EditText ed;
        public MyTextChangedListener(EditText ed){
            this.ed = ed;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            Log.d("chan","beforeTextChanged");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            Log.d("chan","onTextChanged");
            if(s.length() == 0){
                ed.setError("该项为必填项");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
//            if(s.toString().trim().length() <= 0){
//                ed.setError("该项为必填项");
//            }
        }
    }
}
