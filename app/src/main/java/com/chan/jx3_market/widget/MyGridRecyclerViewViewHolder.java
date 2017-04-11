package com.chan.jx3_market.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chan.jx3_market.R;


/**
 * Created by channey on 2017/4/10.
 */

public class MyGridRecyclerViewViewHolder extends RecyclerView.ViewHolder {
    public TextView tv;
    public MyGridRecyclerViewViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.item_gird_recyclerview_tv);
    }
}
