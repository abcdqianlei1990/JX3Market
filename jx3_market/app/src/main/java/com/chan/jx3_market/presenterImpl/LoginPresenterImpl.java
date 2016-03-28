package com.chan.jx3_market.presenterImpl;

import android.app.Activity;
import android.util.Log;

import com.chan.jx3_market.bean.UserInfo;
import com.chan.jx3_market.modelimpl.LoginModelImpl;
import com.chan.jx3_market.presenter.ILoginPresenter;
import com.chan.jx3_market.view.ILogin;
import com.chan.jx3_market.viewimpl.LoginActivity;

import constants.ToastMsg;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by qianlei on 2016-03-28.14:40
 * class description:
 */
public class LoginPresenterImpl implements ILoginPresenter {

    private static LoginActivity mLoginActivity;
    private static LoginModelImpl loginModel;
    private static volatile LoginPresenterImpl presenter;

    private LoginPresenterImpl() {
    }

    public static LoginPresenterImpl getInstance(LoginActivity mLoginView){
        if(presenter == null){
            synchronized(LoginPresenterImpl.class){
                if(presenter == null){
                    presenter = new LoginPresenterImpl();
                    mLoginActivity = mLoginView;
                    loginModel = new LoginModelImpl(mLoginActivity);
                }
            }
        }
        return presenter;
    }
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

    public void handleResult(int ret){
        Log.d("chan", "ret ==> " + ret);
        if (ret == 1) {
            mLoginActivity.showToast(ToastMsg.USER_NOT_EXIST);
        } else if (ret == 2) {
            mLoginActivity.showToast(ToastMsg.NAME_OR_PWD_ERR);
        } else {
            mLoginActivity.showToast("登录成功");
        }
    }
}
