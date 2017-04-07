package com.chan.jx3_market.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by qianlei on 2016-04-01.10:32
 * class description:账号信息
 */
public class AccountInfo extends BmobObject{

    /**
     * 发布者用户id
     */
    private String userId;

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
     * pvp装分
     */
    private String pvpScore;

    /**
     * pve装分
     */
    private String pveScore;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPvpScore() {
        return pvpScore;
    }

    public void setPvpScore(String pvpScore) {
        this.pvpScore = pvpScore;
    }

    public String getPveScore() {
        return pveScore;
    }

    public void setPveScore(String pveScore) {
        this.pveScore = pveScore;
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
}
