package com.cmazxiaoma.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Description: 沉梦昂志
 * Data：2017/5/13-12:17
 * Author: xiaoma
 */

public abstract class BaseMvpFragment<V,P extends BasePresenter<V>> extends  BaseOrdinaryFragment{
    private P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=createPresenter();//创建Presener;
        mPresenter.attachView((V)this); //关联view
    }


    @Override
    public  void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public abstract P createPresenter();

}
