package viewholder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chan.jx3_market.R;

import listener.FooterViewClickListener;

/**
 * Created by qianlei on 2016-04-06.15:53
 * class description:
 */
public class FooterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tv;
    FooterViewClickListener listener;

    public FooterViewHolder(View itemView,FooterViewClickListener listener) {
        super(itemView);
        this.listener = listener;
        tv = (TextView) itemView.findViewById(R.id.footer);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onFooterViewClick();
    }
}
