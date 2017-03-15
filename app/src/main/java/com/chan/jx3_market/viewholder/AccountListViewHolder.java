package com.chan.jx3_market.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chan.jx3_market.R;

import com.chan.jx3_market.listener.RecyclerViewItemClickListener;

/**
 * Created by qianlei on 2016-04-06.11:45
 * class description:
 */
public class AccountListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView profession;
    public TextView limit;
    public TextView pvporpve;
    public TextView hair;
    public TextView face;
    public TextView pet;
    public TextView calling;
    public TextView clothes;
    public TextView mount;
    public TextView zhanjie;
    public TextView jjcLv;
    public TextView bodyType;
    public TextView score;
    public TextView expScore;
    public TextView other;

    private RecyclerViewItemClickListener listener;

    public AccountListViewHolder(View itemView, RecyclerViewItemClickListener listener) {
        super(itemView);
        profession = (TextView) itemView.findViewById(R.id.profession);
        limit = (TextView) itemView.findViewById(R.id.limit);
        pvporpve = (TextView) itemView.findViewById(R.id.pvporpve);
        hair = (TextView) itemView.findViewById(R.id.hair);
        face = (TextView) itemView.findViewById(R.id.face);
        pet = (TextView) itemView.findViewById(R.id.pet);
        calling = (TextView) itemView.findViewById(R.id.calling);
        clothes = (TextView) itemView.findViewById(R.id.clothes);
        mount = (TextView) itemView.findViewById(R.id.mount);
        zhanjie = (TextView) itemView.findViewById(R.id.zhanjie);
        jjcLv = (TextView) itemView.findViewById(R.id.jjcLv);
        bodyType = (TextView) itemView.findViewById(R.id.bodyType);
        score = (TextView) itemView.findViewById(R.id.score);
        expScore = (TextView) itemView.findViewById(R.id.expScore);
        other = (TextView) itemView.findViewById(R.id.other);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick();
        }
    }
}
