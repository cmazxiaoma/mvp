package com.cmazxiaoma.mvp.presenter;

import com.cmazxiaoma.mvp.base.BasePresenter;
import com.cmazxiaoma.mvp.bean.NewResult;
import com.cmazxiaoma.mvp.contract.NewsContract;
import com.cmazxiaoma.mvp.model.NewsModelImpl;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: 沉梦昂志
 * Data：2017/5/12-13:44
 * Author: xiaoma
 */
public class NewsPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter{
    private NewsContract.Model model;

    public NewsPresenter() {
        this.model =new NewsModelImpl();
    }

    public void getNewsInfo(String type) {
        model.getNewsInfo(type).subscribe(new Observer<NewResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(NewResult newResult) {
                mView.onSuccessed(newResult);
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
