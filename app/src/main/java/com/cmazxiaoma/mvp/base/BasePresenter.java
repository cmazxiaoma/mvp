package com.cmazxiaoma.mvp.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Description: 沉梦昂志
 * Data：2017/5/8-15:46
 * Author: xiaoma
 */

public abstract class BasePresenter<V>{
    public V mView;
    private final CompositeDisposable compositeDisposable=new CompositeDisposable();

    public void  attachView(V mView){
        this.mView=mView;
    }

    public void detachView(){
        this.mView=null;
        unDisposable();

    }

    public void addDisposable(Disposable d) {
        compositeDisposable.add(d);
    }

    public void unDisposable() {
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
    }
}
