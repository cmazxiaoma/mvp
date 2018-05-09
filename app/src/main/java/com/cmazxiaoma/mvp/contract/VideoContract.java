package com.cmazxiaoma.mvp.contract;

import com.cmazxiaoma.mvp.bean.VideoResult;

import io.reactivex.Observable;

/**
 * Description: 沉梦昂志
 * Data：2017/5/12-13:44
 * Author: xiaoma
 */
public interface VideoContract {
    interface Model {
        //title用于模糊查找
        Observable<VideoResult> getVideoInfo(String appId,String sign,String type,String title,String page);
    }

    interface View {
        void onSuccessed(VideoResult result);
        void onFailed(Throwable e);
    }

    interface Presenter {
        //模糊查找
        void getVideoInfoForSearch(String type,String title,String page);
        void getVideoInfo(String type,String page);
    }
}
