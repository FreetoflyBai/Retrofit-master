package com.android.retrofit.http;


import com.android.retrofit.entity.HttpResult;
import com.android.retrofit.entity.Subject;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/11/4 11:30
 * QQ         : 904869397@qq.com
 */

public interface MovieService {

    /**
     * https://api.douban.com/v2/movie/top250?start=0&count=10
     */

    /**
     * 原生Retrofit方式
     * @param start
     * @param count
     * @return
     */
//    @GET("top250")
//    Call<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

    /**
     * Retrofit and RxJava初步使用
     */
//    @GET("top250")
//    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

    /**
     * Retrofit and RxJava简单封装
     */
//    @GET("top250")
//    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

    /**
     * Retrofit and RxJava数据预处理和添加进度条
     */
    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
