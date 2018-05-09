package com.cmazxiaoma.mvp.ui.fragment.PhotoContain;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.cmazxiaoma.mvp.Utils.UtilsLog;

/**
 * Description: 沉梦昂志
 * Data：2017/5/17-11:00
 * Author: xiaoma
 */

public class WrapContentStaggeredGridLayoutManager extends StaggeredGridLayoutManager{


    public WrapContentStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public WrapContentStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            UtilsLog.i("meet a IOOBE in RecyclerView");
        }
    }
}
