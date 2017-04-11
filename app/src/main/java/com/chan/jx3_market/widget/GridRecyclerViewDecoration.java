package com.chan.jx3_market.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by channey on 2017/4/10.
 */

public class GridRecyclerViewDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public GridRecyclerViewDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.bottom = space;
    }
}
