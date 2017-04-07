package com.chan.jx3_market.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by channey on 2016/11/21.
 * version:1.0
 * desc:
 */

public class BottomMarginDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public BottomMarginDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
    }
}
