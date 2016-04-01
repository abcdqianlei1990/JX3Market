package com.chan.jx3_market.modelimpl;

import android.app.Activity;
import android.util.Log;

import com.chan.jx3_market.bean.UserInfo;
import com.chan.jx3_market.model.ILoginModel;
import com.chan.jx3_market.presenterImpl.LoginPresenterImpl;
import com.chan.jx3_market.viewimpl.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import base.BaseModelImpl;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import constants.Keys;

/**
 * Created by qianlei on 2016-03-28.15:55
 * class description:
 */
public class LoginModelImpl extends BaseModelImpl implements ILoginModel {

    private LoginActivity activity;

    int ret = -1;   //返回值
    LoginPresenterImpl presenter;

    public LoginModelImpl(LoginActivity activity) {
        this.activity = activity;
        presenter = LoginPresenterImpl.getInstance(activity);
    }

    //[{"createdAt":"2016-03-28 15:36:20","objectId":"8P2YNNNu","password":"123456","qq":"123456789","updatedAt":"2016-03-28 15:36:56","username":"abcdql520","yy":"987654321"}]
    @Override
    public void verifyUser(final UserInfo info) {
        BmobQuery<UserInfo> query = new BmobQuery<UserInfo>(USER_TABLE_NAME);
        query.addWhereEqualTo("username", info.getUsername());
        query.findObjects(activity, new FindCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Log.d("chan", "jsonArray ==>" + jsonArray.toString());
                try {
                    if (jsonArray.length() == 0) {
//                        presenter.handleResult(Keys.USER_NOT_EXIST);
                        presenter.onLoginFailure(Keys.USER_NOT_EXIST);
                    } else {
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String password = jsonObject.getString("password");
                        if (password.equals(info.getPassword())) {
//                            presenter.handleResult(Keys.LOGIN_SUCCESS);
                            presenter.onLoginSuccess(info);
                        } else {
                            ret = Keys.NAME_OR_PWD_ERR;
//                            presenter.handleResult(Keys.NAME_OR_PWD_ERR);
                            presenter.onLoginFailure(Keys.NAME_OR_PWD_ERR);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("chan", "failure ==>" + s);
            }
        });
    }
}
