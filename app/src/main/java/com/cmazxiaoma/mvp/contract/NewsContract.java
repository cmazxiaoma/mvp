package com.cmazxiaoma.mvp.contract;

import com.cmazxiaoma.mvp.bean.NewResult;

import io.reactivex.Observable;

/**
 * Description: 沉梦昂志
 * Data：2017/5/12-13:44
 * Author: xiaoma
 */
public interface NewsContract {
    interface Model {
        Observable<NewResult> getNewsInfo(String type);
    }

    interface View {
        void onSuccessed(NewResult newResult);
        void onFailed(Throwable e);
    }

    interface Presenter {
        void getNewsInfo(String type);
    }
}
