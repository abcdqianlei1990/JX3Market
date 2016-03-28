package com.chan.jx3_market.bean;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * Created by qianlei on 2016-03-28.14:45
 * class description:登录信息
 */
public class UserInfo extends BmobObject implements Parcelable{

    private String username;
    private String password;
    private String qq;
    private String yy;

    protected UserInfo(Parcel in) {
        username = in.readString();
        password = in.readString();
        qq = in.readString();
        yy = in.readString();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getYy() {
        return yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }

    public UserInfo() {
        this.setTableName("user_t");
    }

    public UserInfo(String username, String password, String qq, String yy) {
        this.setTableName("user_t");
        this.username = username;
        this.password = password;
        this.qq = qq;
        this.yy = yy;
    }

    public UserInfo(String tableName, String username, String password, String qq, String yy) {
        super(tableName);
        this.username = username;
        this.password = password;
        this.qq = qq;
        this.yy = yy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(qq);
        dest.writeString(yy);
    }
}
