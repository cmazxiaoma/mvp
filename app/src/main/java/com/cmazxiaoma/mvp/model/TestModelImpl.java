package com.cmazxiaoma.mvp.model;

import com.cmazxiaoma.mvp.bean.GankResult;
import com.cmazxiaoma.mvp.consant.RetrofitServiceManager;
import com.cmazxiaoma.mvp.contract.TestContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description: 沉梦昂志
 * Data：2017/5/7-10:13
 * Author: xiaoma
 */
public class TestModelImpl implements TestContract.Model{
    @Override
    public Observable<GankResult> getInfo(String page) {
        return RetrofitServiceManager.getInstance().getService().getInfo("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
