package com.cmazxiaoma.mvp.Utils;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Description: 沉梦昂志
 * Data：2017/5/15-15:22
 * Author: xiaoma
 */

public class MyAnim {
    public static TranslateAnimation showAction(){
        TranslateAnimation showAction=new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        showAction.setDuration(1000);
        return showAction;
    }

    public static TranslateAnimation hideAction(){
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(1000);
        return mHiddenAction;
    }
}
