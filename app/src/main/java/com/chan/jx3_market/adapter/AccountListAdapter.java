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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        this.pos = position;
        if(holder instanceof AccountListViewHolder) {
            final AccountListViewHolder h = (AccountListViewHolder) holder;
            AccountInfo info = data.get(position);

            h.title.setText((position+1)+"."+info.getProfession()+" # "+info.getBodyType());
            //============================ BASE INFO ============================
            h.profession.setText("门派："+info.getProfession());
            h.bodyType.setText("体型："+info.getBodyType());

            //============================ PVP INFO ============================
            String pvpScore = info.getPvpScore();
            String zhanjie = info.getZhanjie();
            String jjcLv = info.getJjcLv();
            if((TextUtils.isEmpty(pvpScore) || "0".equals(pvpScore)) && (TextUtils.isEmpty(zhanjie) || "0".equals(zhanjie))
                    && (TextUtils.isEmpty(jjcLv) || "0".equals(jjcLv))){
                h.pvpInfoBlockLl.setVisibility(View.GONE);
                h.blockLine1.setVisibility(View.GONE);
            }else {
                h.pvpInfoBlockLl.setVisibility(View.VISIBLE);
                h.blockLine1.setVisibility(View.VISIBLE);
                h.pvpScore.setText("装分："+ pvpScore);
                //战阶
                if (TextUtils.isEmpty(zhanjie) || "0".equals(zhanjie)) {
                    h.zhanjie.setVisibility(View.GONE);
                } else {
                    h.zhanjie.setVisibility(View.VISIBLE);
                    h.zhanjie.setText("战阶: "+zhanjie);
                }
                //JJC段位
                if (TextUtils.isEmpty(jjcLv) || "0".equals(jjcLv)) {
                    h.jjcLv.setVisibility(View.GONE);
                } else {
                    h.jjcLv.setVisibility(View.VISIBLE);
                    h.jjcLv.setText("竞技场段位: "+jjcLv);
                }
            }
            //============================ PVE INFO ============================
            String pveScore = info.getPveScore();
            if(TextUtils.isEmpty(pveScore) || "0".equals(pveScore)){
                h.pveInfoBlockLl.setVisibility(View.GONE);
                h.blockLine2.setVisibility(View.GONE);
            }else {
                h.pveInfoBlockLl.setVisibility(View.VISIBLE);
                h.blockLine2.setVisibility(View.VISIBLE);
                h.pveScore.setText("装分："+ pveScore);
            }

            //============================ OUTLOOK INFO ============================
            String clothes = info.getClothes();
            String mount = info.getMount();
            String limit = info.getLimit();
            String hair = info.getHair();
            String face = info.getFace();
            boolean clothesEmpty;
            boolean mountEmpty;
            boolean limitEmpty;
            boolean hairEmpty;
            boolean faceEmpty;

            //衣服
            if (TextUtils.isEmpty(clothes)) {
                clothesEmpty = true;
            } else {
                clothesEmpty = false;
            }

            //坐骑
            if (TextUtils.isEmpty(mount)) {
                mountEmpty = true;
            } else {
                mountEmpty = false;
            }
            //限量
            if (TextUtils.isEmpty(limit)) {
                limitEmpty = true;
            } else {
                limitEmpty = false;
            }
            //发型
            if (TextUtils.isEmpty(hair)) {
                hairEmpty = true;
            } else {
                hairEmpty = false;
            }

            //脸型
            if (TextUtils.isEmpty(face)) {
                faceEmpty = true;
            } else {
                faceEmpty = false;
            }
            if(clothesEmpty && mountEmpty && limitEmpty && hairEmpty && faceEmpty){
                h.outlookInfoBlockLl.setVisibility(View.GONE);
                h.blockLine3.setVisibility(View.GONE);
            }else {
                h.outlookInfoBlockLl.setVisibility(View.VISIBLE);
                h.blockLine3.setVisibility(View.VISIBLE);
                //衣服
                if (clothesEmpty) {
                    h.clothes.setVisibility(View.GONE);
                } else {
                    h.clothes.setVisibility(View.VISIBLE);
                    h.clothes.setText("成衣："+clothes);
                }

                //坐骑
                if (mountEmpty) {
                    h.mount.setVisibility(View.GONE);
                } else {
                    h.mount.setVisibility(View.VISIBLE);
                    h.mount.setText("坐骑："+mount);
                }
                //限量
                if (limitEmpty) {
                    h.limit.setVisibility(View.GONE);
                } else {
                    h.limit.setVisibility(View.VISIBLE);
                    h.limit.setText("限量："+limit);
                }
                //发型
                if (hairEmpty) {
                    h.hair.setVisibility(View.GONE);
                } else {
                    h.hair.setVisibility(View.VISIBLE);
                    h.hair.setText("发型："+hair);
                }

                //脸型
                if (faceEmpty) {
                    h.face.setVisibility(View.GONE);
                } else {
                    h.face.setVisibility(View.VISIBLE);
                    h.face.setText("脸型："+face);
                }
            }
            //============================ OTHER INFO ============================
            String expScore = info.getExpScore();
            String calling = info.getCalling();
            String pet = info.getPet();
            String other = info.getOther();
            boolean expScoreEmpty;
            boolean callingEmpty;
            boolean petmpty;
            boolean otherEmpty;
            //资历
            if (TextUtils.isEmpty(expScore)) {
                expScoreEmpty = true;
            } else {
                expScoreEmpty = false;
            }

            //宠物
            if (TextUtils.isEmpty(pet)) {
                petmpty = true;
            } else {
                petmpty = false;
            }
            //称号
            if (TextUtils.isEmpty(calling)) {
                callingEmpty = true;
            } else {
                callingEmpty = false;
            }
            //其他描述
            if (TextUtils.isEmpty(other)) {
                otherEmpty = true;
            } else {
                otherEmpty = false;
            }

            if(expScoreEmpty && petmpty && callingEmpty && otherEmpty){
                h.otherInfoBlockLl.setVisibility(View.GONE);
                h.blockLine4.setVisibility(View.GONE);
            }else {
                h.otherInfoBlockLl.setVisibility(View.VISIBLE);
                h.blockLine4.setVisibility(View.VISIBLE);

                if (expScoreEmpty) {
                    h.expScore.setVisibility(View.GONE);
                } else {
                    h.expScore.setVisibility(View.VISIBLE);
                    h.expScore.setText("资历："+ expScore);
                }
                //宠物
                if (petmpty) {
                    h.pet.setVisibility(View.GONE);
                } else {
                    h.pet.setVisibility(View.VISIBLE);
                    h.pet.setText("宠物："+pet);
                }
                //称号
                if (callingEmpty) {
                    h.calling.setVisibility(View.GONE);
                } else {
                    h.calling.setVisibility(View.VISIBLE);
                    h.calling.setText("称号："+calling);
                }
                //其他描述
                if (otherEmpty) {
                    h.other.setVisibility(View.GONE);
                } else {
                    h.other.setVisibility(View.VISIBLE);
                    h.other.setText("其他亮点："+other);
                }
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
