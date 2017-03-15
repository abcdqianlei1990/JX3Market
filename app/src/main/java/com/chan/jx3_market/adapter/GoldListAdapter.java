package com.chan.jx3_market.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chan.jx3_market.R;
import com.chan.jx3_market.bean.GoldInfo;

import java.util.ArrayList;

import com.chan.jx3_market.base.BaseActivity;
import com.chan.jx3_market.viewholder.GoldListViewHolder;

/**
 * Created by ex-qianlei349 on 2016-06-08.
 */
public class GoldListAdapter extends RecyclerView.Adapter<GoldListViewHolder> {

    private Context context;
    private ArrayList<GoldInfo> dataList;
    private BaseActivity.RecyclerViewItemClickListener listener;

    public GoldListAdapter(Context context, ArrayList<GoldInfo> dataList) {
//        Log.d("chan","GoldListAdapter");
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public GoldListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.d("chan","onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.item_gold_list,null);
        return new GoldListViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(GoldListViewHolder holder, int position) {
        GoldInfo info = dataList.get(position);
//        Log.d("chan","nBindViewHolder");
        if(info != null){
            holder.count.setText(info.getCount());
            holder.proportion.setText(info.getProportion());

            if(info.isSupportLittleDeal()){
                holder.support.setText("是");
            }else{
                holder.support.setText("否");
            }

            holder.way.setText(info.getDealType());
        }
    }

    @Override
    public int getItemCount() {
//        Log.d("chan","getItemCount");
        return dataList.size();
    }

    public void setOnItemClickListener(BaseActivity.RecyclerViewItemClickListener listener){
        this.listener = listener;
    }
}
