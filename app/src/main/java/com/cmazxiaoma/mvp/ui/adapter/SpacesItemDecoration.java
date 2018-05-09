package com.cmazxiaoma.mvp.ui.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Description: 沉梦昂志
 * Data：2017/5/16-9:44
 * Author: xiaoma
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    public SpacesItemDecoration(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space;
        //获取view在Adapter中的position
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space;
        }
    }
}
