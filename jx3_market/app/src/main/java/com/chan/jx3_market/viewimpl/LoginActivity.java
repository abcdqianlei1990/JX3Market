package com.chan.jx3_market.viewimpl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chan.jx3_market.R;
import com.chan.jx3_market.bean.UserInfo;
import com.chan.jx3_market.presenter.ILoginPresenter;
import com.chan.jx3_market.presenterImpl.LoginPresenterImpl;
import com.chan.jx3_market.view.ILogin;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import cn.bmob.v3.Bmob;

import static android.Manifest.permission.READ_CONTACTS;

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

        mLoginPresenter = LoginPresenterImpl.getInstance(this);
        mLoginPresenter.onCreate();
        initViews();

//        mLoginFormView = findViewById(R.id.login_form);
//        mProgressView = findViewById(R.id.login_progress);
    }

    protected void initViews(){
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
        mRegisterButton.setOnClickListener(this);
    }


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
                initAnimators(mSignInButton);
                if(inputOK()){
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

    public void initAnimators(Object target){
        ObjectAnimator animator1 = ObjectAnimator.
                ofFloat(target, "scaleX", 1.0f, 0.7f);
        ObjectAnimator animator2 = ObjectAnimator.
                ofFloat(target, "scaleX", 0.7f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.play(animator1);
        set.play(animator2);
        set.setDuration(500);
        set.start();
    }
}

