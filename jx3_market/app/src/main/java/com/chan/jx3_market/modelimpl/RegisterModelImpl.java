package com.chan.jx3_market.modelimpl;

import android.util.Log;

import com.chan.jx3_market.bean.UserInfo;
import com.chan.jx3_market.model.IRegisterModel;
import com.chan.jx3_market.presenterImpl.RegisterPresenterImpl;
import com.chan.jx3_market.viewimpl.RegisterActivity;

import org.json.JSONArray;

import base.BaseModelImpl;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.SaveListener;
import constants.Keys;

/**
 * Created by qianlei on 2016-03-28.19:25
 * class description:
 */
public class RegisterModelImpl extends BaseModelImpl implements IRegisterModel {

    private RegisterActivity activity;

    int ret = -1;   //返回值
    RegisterPresenterImpl presenter;

    public RegisterModelImpl(RegisterActivity activity) {
        this.activity = activity;
        presenter = RegisterPresenterImpl.getInstance(activity);
    }

    @Override
    public void register(UserInfo info) {

    }

    @Override
    public void verifyUser(final UserInfo info) {
        final BmobQuery<UserInfo> query = new BmobQuery<UserInfo>(USER_TABLE_NAME);
        query.addWhereEqualTo("username", info.getUsername());
        query.findObjects(activity, new FindCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Log.d("chan", "jsonArray ==>" + jsonArray.toString());
                if (jsonArray.length() == 0) {
                    //插入数据库
                    info.save(activity, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            presenter.handleCallBack(0);
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            presenter.handleCallBack(-1);
                        }
                    });
                } else {
                    ret = Keys.USER_EXIST;
                    presenter.handleRet(ret);
                }
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("chan", "failure ==>" + s);
            }
        });

    }
}
