package com.chan.jx3_market.modelimpl;

import android.util.Log;

import com.chan.jx3_market.bean.UserInfo;
import com.chan.jx3_market.model.ILoginModel;
import com.chan.jx3_market.presenterImpl.LoginPresenterImpl;
import com.chan.jx3_market.viewimpl.LoginActivity;

import java.util.List;

import com.chan.jx3_market.base.BaseModelImpl;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by qianlei on 2016-03-28.15:55
 * class description:
 */
public class LoginModelImpl extends BaseModelImpl implements ILoginModel {

    private LoginActivity activity;
    LoginPresenterImpl presenter;

    public LoginModelImpl(LoginActivity activity,LoginPresenterImpl presenter) {
        this.activity = activity;
        this.presenter = presenter;
    }

    //[{"createdAt":"2016-03-28 15:36:20","objectId":"8P2YNNNu","password":"123456","qq":"123456789","updatedAt":"2016-03-28 15:36:56","username":"abcdql520","yy":"987654321"}]
    @Override
    public void verifyUser(final UserInfo info) {
        BmobQuery<UserInfo> query = new BmobQuery<UserInfo>();
        query.addWhereEqualTo("username", info.getUsername());
        query.addWhereEqualTo("password", info.getPassword());
        query.findObjects(activity, new FindListener<UserInfo>() {
            @Override
            public void onSuccess(List<UserInfo> list) {
                if(list.size() > 0){
                    UserInfo userInfo = list.get(0);
                    presenter.onLoginSuccess(userInfo);
                }else{
                    presenter.onLoginFailure();
                }
            }

            @Override
            public void onError(int i, String s) {
                presenter.onLoginFailure();
            }
        });
    }
}
