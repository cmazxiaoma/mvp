package com.cmazxiaoma.mvp.presenter;

import com.cmazxiaoma.mvp.Config;
import com.cmazxiaoma.mvp.base.BasePresenter;
import com.cmazxiaoma.mvp.bean.VideoResult;
import com.cmazxiaoma.mvp.contract.VideoContract;
import com.cmazxiaoma.mvp.model.VideoModelImpl;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: 沉梦昂志
 * Data：2017/5/12-13:44
 * Author: xiaoma
 */
public class VideoPresenter extends BasePresenter<VideoContract.View>implements VideoContract.Presenter{
    private VideoContract.Model model;

    public VideoPresenter(){
        model=new VideoModelImpl();
    }

    @Override
    public void getVideoInfoForSearch(String type, String title, String page) {
        model.getVideoInfo(Config.SHOWAPI_APPID,Config.SHOWAPI_SECRET,type,title,page)
                .subscribe(new Observer<VideoResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }
                    @Override
                    public void onNext(VideoResult result) {
                        mView.onSuccessed(result);
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
    public void getVideoInfo(String type, String page) {
        model.getVideoInfo(Config.SHOWAPI_APPID,Config.SHOWAPI_SECRET,type,"",page)
                .subscribe(new Observer<VideoResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(VideoResult result) {
                        mView.onSuccessed(result);
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
