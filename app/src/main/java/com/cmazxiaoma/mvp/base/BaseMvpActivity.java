package com.cmazxiaoma.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Description: 沉梦昂志
 * Data：2017/5/9-21:29
 * Author: xiaoma
 */

public abstract class BaseMvpActivity<V,P extends BasePresenter<V>> extends BaseOrdinaryActivity{
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=createPresenter();//创建Presener;
        mPresenter.attachView((V)this); //关联view
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public abstract P createPresenter();

}
