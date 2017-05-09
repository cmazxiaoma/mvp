package com.cmazxiaoma.mvp.consant;

import com.cmazxiaoma.mvp.bean.GankResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Description: 沉梦昂志
 * Data：2017/5/7-11:01
 * Author: xiaoma
 */

public interface  GankService {
    @GET("api/data/Android/10/{page}")
    Observable<GankResult> getInfo(@Path("page") String page);
}
