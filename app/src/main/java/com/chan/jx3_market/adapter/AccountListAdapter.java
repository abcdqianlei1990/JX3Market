package com.chan.jx3_market.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chan.jx3_market.R;
import com.chan.jx3_market.bean.AccountInfo;

import java.util.ArrayList;

import com.chan.jx3_market.listener.FooterViewClickListener;
import com.chan.jx3_market.listener.RecyclerViewItemClickListener;
import com.chan.jx3_market.viewholder.AccountListViewHolder;
import com.chan.jx3_market.viewholder.FooterViewHolder;

/**
 * Created by qianlei on 2016-04-06.11:44
 * class description:
 */
public class AccountListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private ArrayList<AccountInfo> data;
    private RecyclerViewItemClickListener listener;
    private FooterViewClickListener footerViewClickListener;
    private boolean hasFooter = false;
    int pos = 0;

    public AccountListAdapter(Activity activity,ArrayList<AccountInfo> data){
        this.activity = activity;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        Log.d("qianlei","AccountListAdapter line42====>viewType="+viewType+" || position="+pos);
        if(viewType == 1){
            view = LayoutInflater.from(activity).inflate(R.layout.item_footer_list,null);
            holder = new FooterViewHolder(view,footerViewClickListener);
        }else{
            view = LayoutInflater.from(activity).inflate(R.layout.item_accountinfo,null);
            holder = new AccountListViewHolder(view,listener);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        this.pos = position;
        Log.d("qianlei","AccountListAdapter line57====>position="+position+"||data.size():"+data.size());
        if(holder instanceof AccountListViewHolder) {
            AccountListViewHolder h = (AccountListViewHolder) holder;
            AccountInfo info = data.get(position);
            h.profession.setText("门派："+info.getProfession());
            h.pvporpve.setText(info.getPvpOrPve());
            h.score.setText("装分："+info.getScore());
            h.expScore.setText("资历："+info.getExpScore());
            h.bodyType.setText("体型："+info.getBodyType());
            //限量
            if (TextUtils.isEmpty(info.getLimit())) {
                h.limit.setVisibility(View.GONE);
            } else {
                h.limit.setText(info.getLimit());
            }
            //发型
            if (TextUtils.isEmpty(info.getHair())) {
                h.hair.setVisibility(View.GONE);
            } else {
                h.hair.setText(info.getHair());
            }

            //脸型
            if (TextUtils.isEmpty(info.getFace())) {
                h.face.setVisibility(View.GONE);
            } else {
                h.face.setText(info.getFace());
            }

            //宠物
            if (TextUtils.isEmpty(info.getPet())) {
                h.pet.setVisibility(View.GONE);
            } else {
                h.pet.setText(info.getPet());
            }

            //称号
            if (TextUtils.isEmpty(info.getCalling())) {
                h.calling.setVisibility(View.GONE);
            } else {
                h.calling.setText(info.getCalling());
            }

            //衣服
            if (TextUtils.isEmpty(info.getClothes())) {
                h.clothes.setVisibility(View.GONE);
            } else {
                h.clothes.setText(info.getClothes());
            }

            //坐骑
            if (TextUtils.isEmpty(info.getMount())) {
                h.mount.setVisibility(View.GONE);
            } else {
                h.mount.setText(info.getMount());
            }

            //战阶
            if (TextUtils.isEmpty(info.getZhanjie())) {
                h.zhanjie.setVisibility(View.GONE);
            } else {
                h.zhanjie.setText(info.getZhanjie());
            }

            //JJC段位
            if (TextUtils.isEmpty(info.getJjcLv())) {
                h.jjcLv.setVisibility(View.GONE);
            } else {
                h.jjcLv.setText(info.getJjcLv());
            }

            //其他描述
            if (TextUtils.isEmpty(info.getOther())) {
                h.other.setVisibility(View.GONE);
            } else {
                h.other.setText(info.getOther());
            }
        }else{
            FooterViewHolder h = (FooterViewHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        return hasFooter ? data.size() + 1 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        //如果有footer，那么此时item count为data.size()+1，所以position == data.size()
        if(hasFooter && position == data.size()){
            return 1;
        }
        return 2;
    }


    public void setOnClickListener(RecyclerViewItemClickListener listener){
        this.listener = listener;
    }

    public void setOnFooterClickListener(FooterViewClickListener footerViewClickListener){
        this.footerViewClickListener = footerViewClickListener;
    }

    public void hasFooter(boolean b){
        this.hasFooter = b;
    }
}
