package com.chan.jx3_market.bean;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * Created by qianlei on 2016-04-01.11:01
 * class description:
 */
public class LittleGoodsInfo extends BmobObject implements Parcelable{

    /**
     * 小商品名称
     */
    private String name;

    /**
     * 价格
     */
    private int price;

    public LittleGoodsInfo() {
        this.setTableName("littlegoods_t");
    }

    protected LittleGoodsInfo(Parcel in) {
        name = in.readString();
        price = in.readInt();
    }

    public static final Creator<LittleGoodsInfo> CREATOR = new Creator<LittleGoodsInfo>() {
        @Override
        public LittleGoodsInfo createFromParcel(Parcel in) {
            return new LittleGoodsInfo(in);
        }

        @Override
        public LittleGoodsInfo[] newArray(int size) {
            return new LittleGoodsInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
    }
}
