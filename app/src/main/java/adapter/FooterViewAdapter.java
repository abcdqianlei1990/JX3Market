package adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chan.jx3_market.R;

import listener.FooterViewClickListener;
import viewholder.FooterViewHolder;

/**
 * Created by qianlei on 2016-04-06.15:58
 * class description:
 */
public class FooterViewAdapter extends RecyclerView.Adapter<FooterViewHolder> {

    FooterViewClickListener listener;
    Activity activity;

    public FooterViewAdapter(Activity activity){
        this.activity = activity;
    }

    @Override
    public FooterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_footer_list,null);
        return new FooterViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(FooterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void setOnFooterViewClickListener(FooterViewClickListener listener){
        this.listener = listener;
    }
}
