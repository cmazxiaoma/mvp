package com.cmazxiaoma.mvp.contract;

import android.app.Dialog;

import com.cmazxiaoma.mvp.bean.GankPhotoResult;
import com.cmazxiaoma.mvp.bean.PhotoResult;

import java.io.UnsupportedEncodingException;

import io.reactivex.Observable;

/**
 * Description: 沉梦昂志
 * Data：2017/5/7-10:13
 * Author: xiaoma
 */
public interface PhotoContract{
    interface Model {
        Observable<PhotoResult> getPhotoInfo(String type,String page,String num);
        Observable<GankPhotoResult> getGankPhotoInfo(String type,String count,String page);
    }

    interface View {
        void onSuccessed(Object reuslt);
        void onFailed(Throwable e);
    }


    interface Presenter {
        void getPhotoInfo(String type,String page,String num);
        void getGankPhotoInfo(String count,String page);
    }
}
