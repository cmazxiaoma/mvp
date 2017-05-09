package com.cmazxiaoma.mvp.presenter;

import android.app.Dialog;

import com.cmazxiaoma.mvp.bean.GankResult;
import com.cmazxiaoma.mvp.contract.TestContract;
import com.cmazxiaoma.mvp.model.TestModelImpl;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Description: 沉梦昂志
 * Data：2017/5/7-10:13
 * Author: xiaoma
 */
public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter{
    private  TestContract.Model model;

    public TestPresenter(){
        model=new TestModelImpl();
    }

    public void getInfo(String page) {
        final Dialog dialog=mView.showDialog();
        model.getInfo("1")
        .subscribe(new Observer<GankResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }
            @Override
            public void onNext(GankResult gankResult) {
                mView.Success(gankResult);
            }
            @Override
            public void onError(Throwable e) {
                if(dialog!=null&&dialog.isShowing()){
                    dialog.dismiss();
                }
                mView.Failed(e);
            }
            @Override
            public void onComplete() {
                if(dialog!=null&&dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });
    }

}
