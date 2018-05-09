package com.cmazxiaoma.mvp.model;

import com.cmazxiaoma.mvp.bean.VideoResult;
import com.cmazxiaoma.mvp.consant.ServiceManager;
import com.cmazxiaoma.mvp.contract.VideoContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description: 沉梦昂志
 * Data：2017/5/12-13:44
 * Author: xiaoma
 */
public class VideoModelImpl implements VideoContract.Model{
    @Override
    public Observable<VideoResult> getVideoInfo(String appId, String sign, String type,String title,String page) {
        return ServiceManager.getInstance().getService("http://route.showapi.com/")
                .getVideoResultInfo(appId, sign, type, title, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
