package com.cmazxiaoma.mvp.contract;

import android.app.Dialog;

import com.cmazxiaoma.mvp.bean.GankResult;
import com.cmazxiaoma.mvp.presenter.BasePresenter;

import io.reactivex.Observable;

/**
 * Description: 沉梦昂志
 * Data：2017/5/7-10:13
 * Author: xiaoma
 */
public interface TestContract {
    interface Model {
        Observable<GankResult> getInfo(String page);
    }
    interface View {
        Dialog  showDialog();
        void Success(GankResult result);
        void Failed(Throwable e);
    }
    interface Presenter {
        void getInfo(String page);
    }
}
