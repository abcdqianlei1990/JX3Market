package com.chan.jx3_market.viewimpl;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chan.jx3_market.R;
import com.chan.jx3_market.base.BaseActivity;
import com.chan.jx3_market.bean.UserInfo;
import com.chan.jx3_market.presenter.ILoginPresenter;
import com.chan.jx3_market.presenterImpl.LoginPresenterImpl;
import com.chan.jx3_market.util.AnimatorUtil;
import com.chan.jx3_market.view.ILogin;

import cn.bmob.v3.Bmob;


/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements ILogin,OnClickListener{


    // UI references.
    private AutoCompleteTextView mUserNameEd;
    private EditText mPasswordView;
    private Button mSignInButton;
    private TextView mRegisterButton;
    private ILoginPresenter mLoginPresenter;

    private static final String APPLICATION_ID = "5ddeaad9b9bd27cddaf87b5f7144667e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, APPLICATION_ID);

        mLoginPresenter = new LoginPresenterImpl(this);
        mLoginPresenter.onCreate();
        initViews();
    }

    public void initViews(){
        setContentView(R.layout.activity_login);

        mUserNameEd = (AutoCompleteTextView) findViewById(R.id.username);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }
        });

        mSignInButton = (Button) findViewById(R.id.login_sign_in_button);
        mRegisterButton = (TextView) findViewById(R.id.login_register);
        mSignInButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);}


    @Override
    public String getName() {
        return mUserNameEd.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mPasswordView.getText().toString().trim();
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_register:
            {
                Log.d("chan", "REGISTER BUTTON IS CLICKED...");
                RegisterActivity.jumpToThisActivity(this);
            }
            break;
            case R.id.login_sign_in_button:
            {
                Log.d("chan", "LOGIN BUTTON IS CLICKED...");
                AnimatorUtil.performClickAnimator(mSignInButton);
                if(inputOK()){
                    showLoadingDialog();
                    UserInfo info = new UserInfo();
                    info.setUsername(mUserNameEd.getText().toString().trim());
                    info.setPassword(mPasswordView.getText().toString().trim());
                    mLoginPresenter.login(info);
                }else{
                    showToast(mSignInButton,"用户名或密码不能为空");
                }

            }
            break;
        }
    }

    @Override
    public void onLoginSuccess(UserInfo info) {
        dismissLoadingDialog();
        UserInfo i = new UserInfo();
        i.setUsername(mUserNameEd.getText().toString().trim());
        i.setPassword(mPasswordView.getText().toString().trim());
        i.setPhone(info.getPhone());
        i.setQq(info.getQq());
        i.setYy(info.getYy());
        app.setInfo(i);
        MainActivity.jumpToMainActivity(this);
        finish();
    }

    @Override
    public void onLoginFailure() {
        dismissLoadingDialog();
        showToast(mSignInButton,"登录失败！用户名或密码错误！");
    }

    /**
     * 用户输入check
     * @return
     */
    public boolean inputOK(){
        String username = mUserNameEd.getText().toString().trim();
        String pwd = mPasswordView.getText().toString().trim();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)){
            return false;
        }
        return true;
    }
}

