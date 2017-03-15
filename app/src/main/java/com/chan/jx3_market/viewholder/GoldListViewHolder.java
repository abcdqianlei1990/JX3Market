package com.chan.jx3_market.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chan.jx3_market.R;

import com.chan.jx3_market.base.BaseActivity;

/**
 * Created by ex-qianlei349 on 2016-06-08.
 */
public class GoldListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView count;
    public TextView proportion;
    public TextView way;
    public TextView support;
    BaseActivity.RecyclerViewItemClickListener listener;

    public GoldListViewHolder(View itemView,BaseActivity.RecyclerViewItemClickListener listener) {
        super(itemView);
        count = (TextView) itemView.findViewById(R.id.item_gold_list_totalcount);
        proportion = (TextView) itemView.findViewById(R.id.item_gold_list_proportion);
        way = (TextView) itemView.findViewById(R.id.item_gold_list_dealtype);
        support = (TextView) itemView.findViewById(R.id.item_gold_list_supportlittle);

        this.listener = listener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v,getAdapterPosition());
    }
}
