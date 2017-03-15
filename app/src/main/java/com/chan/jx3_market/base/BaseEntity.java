package com.chan.jx3_market.base;

import java.util.ArrayList;

/**
 * Created by qianlei on 2016-04-06.10:01
 * class description:
 */
public class BaseEntity<T> {

    private int total;

    private ArrayList<T> list;

    public BaseEntity() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }
}
