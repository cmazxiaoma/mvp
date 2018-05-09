package com.cmazxiaoma.mvp.consant;

import com.cmazxiaoma.mvp.bean.GankPhotoResult;
import com.cmazxiaoma.mvp.bean.PhotoResult;
import com.cmazxiaoma.mvp.bean.NewResult;
import com.cmazxiaoma.mvp.bean.VideoResult;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Description: 沉梦昂志
 * Data：2017/5/7-11:01
 * Author: xiaoma
 */

public interface ApiService {

    @GET("imgs?col=美女&sort=0&tag3=&p=channel&from=1")
    Observable<PhotoResult>getPhotoInfo(@Query("tag")String type,@Query("pn") String page,@Query("rn") String num);

    @FormUrlEncoded
    @POST("index")
    Observable<NewResult>getNewsInfo(@Field("type")String type,@Field("key")String key);

    @GET("api/data/{type}/{count}/{page}")
    Observable<GankPhotoResult> getGankPhotoInfo(@Path("type")String type,@Path("count") String count,@Path("page") String page);

    @GET("255-1")
    Observable<VideoResult> getVideoResultInfo(@Query("showapi_appid")String appId,@Query("showapi_sign")String sign,@Query("type")String type,@Query("title")String title,@Query("page")String page);

}
