package com.chan.jx3_market.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chan.jx3_market.R;

import com.chan.jx3_market.listener.RecyclerViewItemClickListener;

/**
 * Created by qianlei on 2016-04-06.11:45
 * class description:
 */
public class AccountListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView title;
    public TextView getContactBtn;
    public LinearLayout detailWrapper;
    public RelativeLayout baseInfoBlockRl;
    public LinearLayout pvpInfoBlockLl;
    public LinearLayout pveInfoBlockLl;
    public LinearLayout outlookInfoBlockLl;
    public LinearLayout otherInfoBlockLl;
    public TextView profession;
    public TextView bodyType;
    public TextView pvpScore;
    public TextView zhanjie;
    public TextView jjcLv;
    public TextView pveScore;
    public TextView limit;
    public TextView hair;
    public TextView face;
    public TextView mount;
    public TextView clothes;

    public TextView pet;
    public TextView calling;
    public TextView expScore;
    public TextView other;
    public View blockLine1;
    public View blockLine2;
    public View blockLine3;
    public View blockLine4;
    private RecyclerViewItemClickListener listener;

    public AccountListViewHolder(View itemView, RecyclerViewItemClickListener listener) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.item_accountinfo_title);
        getContactBtn = (TextView) itemView.findViewById(R.id.item_accountinfo_title_btn);
        detailWrapper = (LinearLayout) itemView.findViewById(R.id.item_accountinfo_detail);
        baseInfoBlockRl = (RelativeLayout) itemView.findViewById(R.id.item_accountinfo_baseinfo_block_rl);
        pvpInfoBlockLl = (LinearLayout) itemView.findViewById(R.id.item_accountinfo_pvpinfo_block_ll);
        pveInfoBlockLl = (LinearLayout) itemView.findViewById(R.id.item_accountinfo_pveinfo_block_ll);
        outlookInfoBlockLl = (LinearLayout) itemView.findViewById(R.id.item_accountinfo_outlook_block_ll);
        otherInfoBlockLl = (LinearLayout) itemView.findViewById(R.id.item_accountinfo_others_block_ll);

        blockLine1 = itemView.findViewById(R.id.item_accountinfo_block_line1);
        blockLine2 = itemView.findViewById(R.id.item_accountinfo_block_line2);
        blockLine3 = itemView.findViewById(R.id.item_accountinfo_block_line3);
        blockLine4 = itemView.findViewById(R.id.item_accountinfo_block_line4);

        profession = (TextView) itemView.findViewById(R.id.profession);
        limit = (TextView) itemView.findViewById(R.id.limit);
        hair = (TextView) itemView.findViewById(R.id.hair);
        face = (TextView) itemView.findViewById(R.id.face);
        pet = (TextView) itemView.findViewById(R.id.pet);
        calling = (TextView) itemView.findViewById(R.id.calling);
        clothes = (TextView) itemView.findViewById(R.id.clothes);
        mount = (TextView) itemView.findViewById(R.id.mount);
        zhanjie = (TextView) itemView.findViewById(R.id.zhanjie);
        jjcLv = (TextView) itemView.findViewById(R.id.jjcLv);
        bodyType = (TextView) itemView.findViewById(R.id.bodyType);
        pvpScore = (TextView) itemView.findViewById(R.id.item_accountinfo_pvp_score);
        pveScore = (TextView) itemView.findViewById(R.id.item_accountinfo_pve_score);
        expScore = (TextView) itemView.findViewById(R.id.expScore);
        other = (TextView) itemView.findViewById(R.id.other);
        this.listener = listener;
        title.setOnClickListener(this);
        detailWrapper.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v,getLayoutPosition());
        }
    }
}
