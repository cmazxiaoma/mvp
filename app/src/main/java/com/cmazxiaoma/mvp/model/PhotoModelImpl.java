package com.cmazxiaoma.mvp.model;

import com.cmazxiaoma.mvp.bean.GankPhotoResult;
import com.cmazxiaoma.mvp.bean.PhotoResult;
import com.cmazxiaoma.mvp.consant.ServiceManager;
import com.cmazxiaoma.mvp.contract.PhotoContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description: 沉梦昂志
 * Data：2017/5/7-10:13
 * Author: xiaoma
 */
public class PhotoModelImpl implements PhotoContract.Model{
    @Override
    public Observable<PhotoResult> getPhotoInfo(String type,String page,String num) {
        return ServiceManager.getInstance().getService("http://image.baidu.com/data/").getPhotoInfo(type,page,num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<GankPhotoResult> getGankPhotoInfo(String type,String count, String page) {
        return ServiceManager.getInstance().getService("http://gank.io/").getGankPhotoInfo(type,count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
