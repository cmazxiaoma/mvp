package com.cmazxiaoma.mvp.consant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description: 沉梦昂志
 * Data：2017/5/7-11:15
 * Author: xiaoma
 */

public class RetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT=5;
    private static final int DEFAULT_READ_TIME_OUT=10;
    private GankService service;

    private RetrofitServiceManager(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//设置操作超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);
        service=new Retrofit.Builder()
                .client(builder.build())
                .baseUrl("http://gank.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GankService.class);
    }

    public static RetrofitServiceManager getInstance(){
        return RetrofitServiceManagerHolder.instance;
    }

    private static class RetrofitServiceManagerHolder{
        private static final RetrofitServiceManager instance=new RetrofitServiceManager();
    }

    public GankService getService(){
        return service;
    }


}
