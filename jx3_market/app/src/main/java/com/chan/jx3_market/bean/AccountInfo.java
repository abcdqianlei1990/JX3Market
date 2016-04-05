package com.chan.jx3_market.bean;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import cn.bmob.v3.BmobObject;
import constants.Keys;

/**
 * Created by qianlei on 2016-04-01.10:32
 * class description:账号信息
 */
public class AccountInfo extends BmobObject implements Parcelable{

    /**
     * 发布者用户名
     */
    private String username;

    /**
     * 发布信息类型
     */
    private Integer infoType;

    /**
     * 门派
     */
    private String profession;

    /**
     * 体型
     */
    private String bodyType;

    /**
     * pvp pve
     */
    private String pvpOrPve;

    /**
     * 装分
     */
    private String score;

    /**
     * 资历
     */
    private String expScore;

    /**
     * 限量
     */
    private String limit;

    /**
     * 发型
     */
    private String hair;

    /**
     * 坐骑
     */
    private String mount;

    /**
     * 脸型
     */
    private String face;

    /**
     * 宠物
     */
    private String pet;

    /**
     * 成衣
     */
    private String clothes;

    /**
     * 战阶
     */
    private String zhanjie;

    /**
     * jjc段位
     */
    private String jjcLv;

    /**
     * 称号
     */
    private String calling;

    /**
     * 其他信息
     */
    private String other;

    public AccountInfo() {
        this.setTableName("account_t");
    }

    protected AccountInfo(Parcel in) {
        username = in.readString();
        infoType = in.readInt();
        profession = in.readString();
        bodyType = in.readString();
        pvpOrPve = in.readString();
        score = in.readString();
        expScore = in.readString();
        limit = in.readString();
        hair = in.readString();
        mount = in.readString();
        face = in.readString();
        pet = in.readString();
        clothes = in.readString();
        zhanjie = in.readString();
        jjcLv = in.readString();
        calling = in.readString();
        other = in.readString();
    }

    public static final Creator<AccountInfo> CREATOR = new Creator<AccountInfo>() {
        @Override
        public AccountInfo createFromParcel(Parcel in) {
            return new AccountInfo(in);
        }

        @Override
        public AccountInfo[] newArray(int size) {
            return new AccountInfo[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getPvpOrPve() {
        return pvpOrPve;
    }

    public void setPvpOrPve(String pvpOrPve) {
        this.pvpOrPve = pvpOrPve;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getExpScore() {
        return expScore;
    }

    public void setExpScore(String expScore) {
        this.expScore = expScore;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getMount() {
        return mount;
    }

    public void setMount(String mount) {
        this.mount = mount;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getClothes() {
        return clothes;
    }

    public void setClothes(String clothes) {
        this.clothes = clothes;
    }

    public String getZhanjie() {
        return zhanjie;
    }

    public void setZhanjie(String zhanjie) {
        this.zhanjie = zhanjie;
    }

    public String getJjcLv() {
        return jjcLv;
    }

    public void setJjcLv(String jjcLv) {
        this.jjcLv = jjcLv;
    }

    public String getCalling() {
        return calling;
    }

    public void setCalling(String calling) {
        this.calling = calling;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeInt(infoType);
        dest.writeString(profession);
        dest.writeString(bodyType);
        dest.writeString(pvpOrPve);
        dest.writeString(score);
        dest.writeString(expScore);
        dest.writeString(limit);
        dest.writeString(hair);
        dest.writeString(mount);
        dest.writeString(face);
        dest.writeString(pet);
        dest.writeString(clothes);
        dest.writeString(zhanjie);
        dest.writeString(jjcLv);
        dest.writeString(calling);
        dest.writeString(other);
    }
}
