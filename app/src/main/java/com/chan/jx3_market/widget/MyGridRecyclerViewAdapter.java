package com.chan.jx3_market.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chan.jx3_market.R;
import com.chan.jx3_market.listener.RecyclerViewItemClickListener;

import java.util.ArrayList;

/**
 * Created by channey on 2017/4/10.
 */

public class MyGridRecyclerViewAdapter extends RecyclerView.Adapter<MyGridRecyclerViewViewHolder> {
    private Context context;
    private ArrayList<String> mArrayData  = new ArrayList<>();
    private RecyclerViewItemClickListener listener;
    private SparseArray<String> mSelectes = new SparseArray<>(); //上次选中后，再次打开设为选中状态

    public MyGridRecyclerViewAdapter(Context context,ArrayList<String> array,SparseArray<String> selectes) {
        this.context = context;
        this.mArrayData = array;
        Log.d("qian","MyGridRecyclerViewAdapter 中 "+mArrayData.hashCode());
        this.mSelectes = selectes;
    }

    @Override
    public MyGridRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid_recyclerview, null);
        return new MyGridRecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyGridRecyclerViewViewHolder holder, final int position) {
        final String value = mArrayData.get(position);
        holder.tv.setText(value);
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(holder.tv,position);
                String selectedValue = mSelectes.get(position);
                if(selectedValue != null){
                    mSelectes.delete(position);
                    holder.tv.setTextColor(context.getResources().getColor(R.color.commonTextColorGrayColor_9));
                    holder.tv.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_grid_recyclerview_item_unselected));
                }else {
                    mSelectes.put(position,value);
                    holder.tv.setTextColor(context.getResources().getColor(R.color.white));
                    holder.tv.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_grid_recyclerview_item_selected));
                }
            }
        });

        String selectedValue = mSelectes.get(position);
        if(selectedValue != null){
            holder.tv.setTextColor(context.getResources().getColor(R.color.white));
            holder.tv.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_grid_recyclerview_item_selected));
        }else {
            holder.tv.setTextColor(context.getResources().getColor(R.color.commonTextColorGrayColor_9));
            holder.tv.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_grid_recyclerview_item_unselected));
        }
    }

    @Override
    public int getItemCount() {
        return mArrayData.size();
    }

    public void setOnRecyclerViewItemClickListener(RecyclerViewItemClickListener listener){
        this.listener = listener;
    }

    /**
     * 清楚数据缓存
     */
    public void clearCache(){
        mSelectes.clear();
    }
}
