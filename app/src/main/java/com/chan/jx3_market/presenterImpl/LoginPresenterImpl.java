package com.chan.jx3_market.presenterImpl;

import android.util.Log;

import com.chan.jx3_market.bean.UserInfo;
import com.chan.jx3_market.modelimpl.LoginModelImpl;
import com.chan.jx3_market.presenter.ILoginPresenter;
import com.chan.jx3_market.viewimpl.LoginActivity;

import com.chan.jx3_market.constants.ToastMsg;

/**
 * Created by qianlei on 2016-03-28.14:40
 * class description:
 */
public class LoginPresenterImpl implements ILoginPresenter {

    public LoginActivity mLoginActivity;
    public LoginModelImpl loginModel;

    public LoginPresenterImpl(LoginActivity activity) {
        this.mLoginActivity = activity;
        loginModel = new LoginModelImpl(activity,this);
    }

//    public static LoginPresenterImpl getInstance(LoginActivity mLoginView){
//        if(presenter == null){
//            synchronized(LoginPresenterImpl.class){
//                if(presenter == null){
//                    presenter = new LoginPresenterImpl();
//                    mLoginActivity = mLoginView;
//                    loginModel = new LoginModelImpl(mLoginActivity,presenter);
//                }
//            }
//        }
//        return presenter;
//    }
    @Override
    public void onCreate() {
        Log.d("chan","LoginPresenterImpl create");
    }

    @Override
    public void onDestroy() {
        Log.d("chan","LoginPresenterImpl destroy");
    }

    @Override
    public void register(UserInfo info) {

    }

    @Override
    public void login(final UserInfo info) {
//        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                subscriber.onNext(loginModel.verifyUser(info));
//                subscriber.onCompleted();
//            }
//        });
//        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//
//            }
//        });

        loginModel.verifyUser(info);
    }

    @Override
    public void onLoginSuccess(UserInfo info) {
        mLoginActivity.onLoginSuccess(info);
    }

    @Override
    public void onLoginFailure() {
        mLoginActivity.onLoginFailure();
    }

}
