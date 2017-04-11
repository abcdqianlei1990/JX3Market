package com.chan.jx3_market.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chan.jx3_market.R;
import com.chan.jx3_market.adapter.BottomMarginDecoration;
import com.chan.jx3_market.listener.RecyclerViewItemClickListener;
import com.chan.jx3_market.util.UIUtil;

import java.util.ArrayList;

/**
 * Created by channey on 2017/4/10.
 */

public class MyGridRecyclerView extends LinearLayout implements RecyclerViewItemClickListener{
    private Context context;
    private RecyclerViewItemClickListener listener;
    private RecyclerView mRecyclerView;
    private ArrayList<String> mArrayData  = new ArrayList<>();
    private MyGridRecyclerViewAdapter mAdapter;
    private SparseArray<String> mSelectes = new SparseArray<>(); //上次选中后，再次打开设为选中状态
    private int mSpanCount;
    public MyGridRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_grid_recyclerview,this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.grid_recyclerview);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyGridRecyclerView);
        mSpanCount = array.getInteger(R.styleable.MyGridRecyclerView_spanCount,2);
        initRecyclerView();
    }

    public void initRecyclerView(){
        GridLayoutManager manager = new GridLayoutManager(context,mSpanCount);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new MyGridRecyclerViewAdapter(context,mArrayData,mSelectes);
        mAdapter.setOnRecyclerViewItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridRecyclerViewDecoration(UIUtil.dip2px(context,17)));
    }

    public void setArrayDataAndRefresh(ArrayList<String> array){
        mArrayData.clear();
        mArrayData.addAll(array);
        mAdapter.notifyDataSetChanged();
    }

    public void setSelectedData(SparseArray<String> selectes){
        for (int i= 0;i < selectes.size();i++){
            int key = selectes.keyAt(i);
            String value = selectes.get(key);
            mSelectes.put(key,value);
        }
    }

    @Override
    public void onClick(View view, int postion) {
        listener.onClick(view,postion);
    }

    public void setOnRecyclerViewItemClickListener(RecyclerViewItemClickListener listener){
        this.listener = listener;
    }

    /**
     * 清楚数据缓存
     */
    public void clearCache(){
        mAdapter.clearCache();
    }
}
