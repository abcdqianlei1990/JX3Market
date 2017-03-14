package com.chan.jx3_market.bean;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * Created by qianlei on 2016-04-01.10:56
 * class description:
 */
public class ServiceInfo extends BmobObject implements Parcelable{

    /**
     * 服务类型。
     * 0-pvp 1-pve
     */
    private int pvpOrPve;

    /**
     * 具体业务
     */
    private String serviceContent;

    /**
     * 价格
     */
    private int price;

    public ServiceInfo() {
        this.setTableName("service_t");
    }

    protected ServiceInfo(Parcel in) {
        pvpOrPve = in.readInt();
        serviceContent = in.readString();
        price = in.readInt();
    }

    public static final Creator<ServiceInfo> CREATOR = new Creator<ServiceInfo>() {
        @Override
        public ServiceInfo createFromParcel(Parcel in) {
            return new ServiceInfo(in);
        }

        @Override
        public ServiceInfo[] newArray(int size) {
            return new ServiceInfo[size];
        }
    };

    public int getPvpOrPve() {
        return pvpOrPve;
    }

    public void setPvpOrPve(int pvpOrPve) {
        this.pvpOrPve = pvpOrPve;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
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
        dest.writeInt(pvpOrPve);
        dest.writeString(serviceContent);
        dest.writeInt(price);
    }
}
