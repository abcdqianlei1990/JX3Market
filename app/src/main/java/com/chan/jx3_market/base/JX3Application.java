package com.chan.jx3_market.base;

import android.app.Application;

import com.chan.jx3_market.bean.UserInfo;

/**
 * Created by qianlei on 2016-04-01.15:59
 * class description:
 */
public class JX3Application extends Application {

    private UserInfo userInfo;
    private static JX3Application app;

    public JX3Application() {
    }

    public static JX3Application getInstance(){
        if(app == null){
            synchronized (JX3Application.class){
                if(app == null){
                    app = new JX3Application();
                }
            }
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
