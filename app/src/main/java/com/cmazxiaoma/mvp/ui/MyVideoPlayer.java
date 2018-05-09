package com.cmazxiaoma.mvp.ui;

import android.content.Context;
import android.util.AttributeSet;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Description: 沉梦昂志
 * Data：2017/7/10-6:56
 * Author: xiaoma
 */

public class MyVideoPlayer extends JCVideoPlayer{

    public MyVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    public MyVideoPlayer(Context context) {
        super(context);
    }
}
