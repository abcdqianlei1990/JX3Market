package com.chan.jx3_market.bean;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * Created by qianlei on 2016-03-30.14:31
 * class description:
 */
public class OrderInfo extends BmobObject implements Parcelable {

    /**
     * 唯一标识
     */
    private String objectID;

    /**
     * 订单所有者
     */
    private String owner;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 类型。1.账号订单  2.游戏币订单  3.代练代打  4.帮贡   5.其他
     */
    private int type;

    /**
     * 具体内容
     */
    private String content;

    public OrderInfo() {
        this.setTableName("order_t");
    }

    public OrderInfo(String objectID, String owner, String createDate, int type, String content) {
        this.setTableName("order_t");
        this.objectID = objectID;
        this.owner = owner;
        this.createDate = createDate;
        this.type = type;
        this.content = content;
    }

    protected OrderInfo(Parcel in) {
        objectID = in.readString();
        owner = in.readString();
        createDate = in.readString();
        type = in.readInt();
        content = in.readString();
    }

    public static final Creator<OrderInfo> CREATOR = new Creator<OrderInfo>() {
        @Override
        public OrderInfo createFromParcel(Parcel in) {
            return new OrderInfo(in);
        }

        @Override
        public OrderInfo[] newArray(int size) {
            return new OrderInfo[size];
        }
    };

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(objectID);
        dest.writeString(owner);
        dest.writeString(createDate);
        dest.writeInt(type);
        dest.writeString(content);
    }
}
