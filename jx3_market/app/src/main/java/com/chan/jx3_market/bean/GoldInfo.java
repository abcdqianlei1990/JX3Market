package com.chan.jx3_market.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by ex-qianlei349 on 2016-05-31.
 * 金币订单实体类
 */
public class GoldInfo extends BmobObject {

    /**
     * 发布者用户名
     */
    private String username;

    /**
     * 发布信息类型
     * @link Keys.PUBLISH_INFO_TYPE_GOLD;
     */
    private Integer infoType;

    /**
     * 出售数量
     */
    private String count;

    /**
     * 比例。
     * 如 1:600 ，那么这里的proportion就是600
     */
    private String proportion;

    /**
     * 交易方式。（客户端不作定义，又发布者自行定义）
     */
    private String dealType;

    /**
     * 是否支持小额交易
     * 1-yes   0-no
     */
    private boolean supportLittleDeal;

    public GoldInfo() {
        this.setTableName("gold_t");
    }

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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public boolean isSupportLittleDeal() {
        return supportLittleDeal;
    }

    public void setSupportLittleDeal(boolean supportLittleDeal) {
        this.supportLittleDeal = supportLittleDeal;
    }
}
