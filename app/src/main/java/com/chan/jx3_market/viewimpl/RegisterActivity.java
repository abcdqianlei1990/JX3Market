package com.chan.jx3_market.viewimpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.chan.jx3_market.R;
import com.chan.jx3_market.bean.UserInfo;
import com.chan.jx3_market.presenterImpl.RegisterPresenterImpl;
import com.chan.jx3_market.view.IRegister;

import com.chan.jx3_market.base.BaseActivity;
import com.chan.jx3_market.constants.Constants;
import com.chan.jx3_market.constants.ToastMsg;
import com.chan.jx3_market.util.AnimatorUtil;

/**
 * Created by qianlei on 2016-03-28.18:07
 * class description:注册界面
 */
public class RegisterActivity extends BaseActivity implements IRegister,View.OnClickListener{

    private TextInputEditText mUserNameEd;
    private TextInputEditText mPasswordEd;
    private TextInputEditText mQQEd;
    private TextInputEditText mYYEd;
    private TextInputEditText mPhoneEd;
    private Button mRegisterBtn;

    private RegisterPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RegisterPresenterImpl(this);
        initViews();
    }

    public void initViews(){
        setContentView(R.layout.activity_register);
        mUserNameEd = (TextInputEditText) findViewById(R.id.register_username);
        mPasswordEd = (TextInputEditText) findViewById(R.id.register_pwd);
        mQQEd = (TextInputEditText) findViewById(R.id.register_qq);
        mYYEd = (TextInputEditText) findViewById(R.id.register_yy);
        mPhoneEd = (TextInputEditText) findViewById(R.id.register_phone);
        mRegisterBtn = (Button) findViewById(R.id.register_sign_up);

        mRegisterBtn.setOnClickListener(this);
    }

    public static void jumpToThisActivity(Activity activity){
        Intent intent = new Intent(activity,RegisterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public String getUserName() {
        return mUserNameEd.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mPasswordEd.getText().toString().trim();
    }

    @Override
    public String getQQ() {
        return mQQEd.getText().toString().trim();
    }

    @Override
    public String getYY() {
        return mYYEd.getText().toString().trim();
    }

    public String getPhone() {
        return mPhoneEd.getText().toString().trim();
    }

    @Override
    public void onRegisterSuccess() {
        showToast(mRegisterBtn,"注册成功，您可以使用该账户登录！");
    }

    @Override
    public void onRegisterFailure(int code) {
        switch (code){
            case Constants.NORMAL:
                showToast(mRegisterBtn,"注册失败！");
                break;
            case Constants.USER_EXIST:
                showToast(mRegisterBtn,"该用户已经存在！");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(R.id.register_sign_up == id){

            AnimatorUtil.performClickAnimator(mRegisterBtn);

            String username = getUserName();
            String pwd = getPassword();
            String qq = getQQ();
            String yy = getYY();
            String phone = getPhone();

            if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd) && (!TextUtils.isEmpty(qq) || !TextUtils.isEmpty(yy)) || !TextUtils.isEmpty(phone)){
                UserInfo info = new UserInfo();
                info.setUsername(username);
                info.setPassword(pwd);
                info.setQq(qq);
                info.setYy(yy);
                info.setYy(phone);
                presenter.register(info);
            }else{
                showToast(mRegisterBtn,ToastMsg.REGISTER_BUTTON_CLICK_NOTE);
            }

        }
    }
}
