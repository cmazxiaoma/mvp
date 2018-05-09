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

public class ServiceManager {
    private static final int DEFAULT_TIME_OUT=5;
    private static final int DEFAULT_READ_TIME_OUT=10;;
    private OkHttpClient.Builder builder;

    private ServiceManager(){
        builder=new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//设置操作超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);
        builder.addInterceptor(new LoggingInterceptor());
    }

    public static ServiceManager getInstance(){
        return GankServiceManagerHolder.instance;
    }

    private static class GankServiceManagerHolder{
        private static final ServiceManager instance=new ServiceManager();
    }

    public ApiService getService(String baseUrl){
       ApiService service=new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class);
        return service;
    }


}
