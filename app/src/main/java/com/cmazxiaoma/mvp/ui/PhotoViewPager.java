package com.cmazxiaoma.mvp.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Description: 沉梦昂志
 * Data：2017/5/18-10:31
 * Author: xiaoma
 */

public class PhotoViewPager extends ViewPager {
    public PhotoViewPager(Context context) {
        super(context);
    }

    public PhotoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try{
            return super.onTouchEvent(ev);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try{
            return super.onInterceptTouchEvent(ev);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
