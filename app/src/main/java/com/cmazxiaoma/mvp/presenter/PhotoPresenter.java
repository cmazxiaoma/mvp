package com.cmazxiaoma.mvp.presenter;

import android.app.Dialog;

import com.cmazxiaoma.mvp.base.BasePresenter;
import com.cmazxiaoma.mvp.bean.GankPhotoResult;
import com.cmazxiaoma.mvp.bean.PhotoResult;
import com.cmazxiaoma.mvp.contract.PhotoContract;
import com.cmazxiaoma.mvp.model.PhotoModelImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: 沉梦昂志
 * Data：2017/5/7-10:13
 * Author: xiaoma
 */
public class PhotoPresenter extends BasePresenter<PhotoContract.View> implements PhotoContract.Presenter{
    private  PhotoContract.Model model;

    public PhotoPresenter(){
        model=new PhotoModelImpl();
    }

    @Override
    public void getPhotoInfo(String type, String page,String num){
        model.getPhotoInfo(type,page,num)
                    .subscribe(new Observer<PhotoResult>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            addDisposable(d);
                        }
                        @Override
                        public void onNext(PhotoResult photoResult) {
                            mView.onSuccessed(photoResult);
                        }
                        @Override
                        public void onError(Throwable e) {
                            mView.onFailed(e);
                        }
                        @Override
                        public void onComplete() {
                        }
                    });
    }

    @Override
    public void getGankPhotoInfo(String count, String page) {
//        String type=null;
//        try {
//            type=URLEncoder.encode("福利","UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        model.getGankPhotoInfo("福利",count,page).subscribe(new Observer<GankPhotoResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }
            @Override
            public void onNext(GankPhotoResult gankPhotoResult) {
                mView.onSuccessed(gankPhotoResult);
            }
            @Override
            public void onError(Throwable e) {
                mView.onFailed(e);
            }
            @Override
            public void onComplete() {
            }
        });

    }
}
