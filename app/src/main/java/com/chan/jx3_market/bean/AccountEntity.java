package com.chan.jx3_market.bean;

import java.util.ArrayList;

import com.chan.jx3_market.base.BaseEntity;

/**
 * Created by qianlei on 2016-04-06.10:00
 * class description:
 */
public class AccountEntity extends BaseEntity<AccountInfo>{

    private int total;

    private ArrayList<AccountInfo> list;

    public ArrayList<AccountInfo> getList() {
        return list;
    }

    public void setList(ArrayList<AccountInfo> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
