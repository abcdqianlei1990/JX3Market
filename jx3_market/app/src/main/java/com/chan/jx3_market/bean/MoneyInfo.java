package com.chan.jx3_market.bean;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * Created by qianlei on 2016-04-01.10:50
 * class description:游戏币信息实体类
 */
public class MoneyInfo extends BmobObject implements Parcelable{

    /**
     * 比例。（1元所能购买的数量）
     */
    private int scale;

    /**
     * 出售数量(单位金)
     */
    private int saleCount;

    /**
     * 交易方式。（客户端不作定义，又发布者自行定义）
     */
    private String dealType;

    /**
     * 是否支持小额交易
     * 1-yes   0-no
     */
    private int supportLittleDeal;

    public MoneyInfo() {
        this.setTableName("money_t");
    }

    protected MoneyInfo(Parcel in) {
        scale = in.readInt();
        saleCount = in.readInt();
        dealType = in.readString();
        supportLittleDeal = in.readInt();
    }

    public static final Creator<MoneyInfo> CREATOR = new Creator<MoneyInfo>() {
        @Override
        public MoneyInfo createFromParcel(Parcel in) {
            return new MoneyInfo(in);
        }

        @Override
        public MoneyInfo[] newArray(int size) {
            return new MoneyInfo[size];
        }
    };

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public int getSupportLittleDeal() {
        return supportLittleDeal;
    }

    public void setSupportLittleDeal(int supportLittleDeal) {
        this.supportLittleDeal = supportLittleDeal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(scale);
        dest.writeInt(saleCount);
        dest.writeString(dealType);
        dest.writeInt(supportLittleDeal);
    }
}
