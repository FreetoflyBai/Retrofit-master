package com.android.retrofit.http;


import com.android.retrofit.entity.HttpResult;
import com.android.retrofit.entity.Subject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/11/4 12:02
 * QQ         : 904869397@qq.com
 */

public class HttpMethods {

    public static final String BASE_URL="https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIMEOUT=5;
    private Retrofit retrofit;
    private MovieService movieService;

    //构造方法私有
    private HttpMethods(){
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder=new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit=new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService=retrofit.create(MovieService.class);

    }

    //在访问HttpMothods时创建单例模式
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCount() == 0) {
                try {
                    throw new ApiException(httpResult.getCount());
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
            return httpResult.getSubjects();
        }
    }

    /**
     * Retrofit and RxJava数据预处理和添加进度条
     */
    public void getTopMovie(Subscriber<List<Subject>> subscriber, int start, int count){

        movieService.getTopMovie(start, count)
                .map(new HttpResultFunc<List<Subject>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * Retrofit and RxJava简单封装
     */
//    public void getTopMovie(Subscriber<HttpResult<List<Subject>>> subscriber, int start, int count){
//        movieService.getTopMovie(start,count)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }


    /**
     * Retrofit and RxJava初步使用 不使用此方法
     */
//    public void getTopMovie(){
//
//    }

    /**
     * 原生Retrofit 不使用此方法
     */
//    public void getTopMovie(){
//
//    }
}