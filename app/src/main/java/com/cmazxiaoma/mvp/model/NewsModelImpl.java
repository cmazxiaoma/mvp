package com.cmazxiaoma.mvp.model;

import com.cmazxiaoma.mvp.Config;
import com.cmazxiaoma.mvp.bean.NewResult;
import com.cmazxiaoma.mvp.consant.ServiceManager;
import com.cmazxiaoma.mvp.contract.NewsContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description: 沉梦昂志
 * Data：2017/5/12-13:44
 * Author: xiaoma
 */
public class NewsModelImpl implements NewsContract.Model{
    @Override
    public Observable<NewResult> getNewsInfo(String type) {
        return ServiceManager.getInstance().getService("http://v.juhe.cn/toutiao/").getNewsInfo(type, Config.NEWS_APPKEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
