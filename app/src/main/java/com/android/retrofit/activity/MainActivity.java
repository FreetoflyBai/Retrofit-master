package com.android.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.android.retrofit.R;
import com.android.retrofit.entity.Subject;
import com.android.retrofit.http.HttpMethods;
import com.android.retrofit.subscribers.ProgressSubscriber;
import com.android.retrofit.subscribers.SubscriberOnNextListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.click_me_BN)
    Button clickMeBN;
    @Bind(R.id.result_TV)
    TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.click_me_BN)
    public void onClick(){
        getMovie();
    }

    /**
     * Retrofit and RxJava数据预处理和添加进度条
     */
    private void getMovie(){
        SubscriberOnNextListener getTopMovieOnNext = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                resultTV.setText(subjects.toString());
            }
        };
        HttpMethods.getInstance().getTopMovie(
                new ProgressSubscriber(getTopMovieOnNext, MainActivity.this),
                0, 10);
    }

    /**
     * Retrofit and RxJava简单封装
     */
//    private void getMovie(){
//        HttpMethods.getInstance().getTopMovie(new Subscriber<HttpResult<List<Subject>>>() {
//            @Override
//            public void onCompleted() {
//                Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onError(Throwable e) {
//                resultTV.setText(e.getMessage());
//            }
//
//            @Override
//            public void onNext(HttpResult<List<Subject>> movieEntity) {
//                resultTV.setText(movieEntity.getSubjects().get(0).toString());
//            }
//        },0,10);
//    }

    /**
     * Retrofit and RxJava初步使用
     */
//    private void getMovie(){
//        String baseUrl="https://api.douban.com/v2/movie/";
//
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//
//        MovieService movieService=retrofit.create(MovieService.class);
//
//        movieService.getTopMovie(0,10)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<HttpResult<List<Subject>>>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                        resultTV.setText(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<List<Subject>> movieEntity) {
//                        resultTV.setText(movieEntity.getSubjects().get(0).toString());
//                    }
//                });
//
//    }

    /**
     * 原生Retrofit请求方式
     */
//    private void getMovie(){
//        String baseUrl="https://api.douban.com/v2/movie/";
//
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        MovieService movieService=retrofit.create(MovieService.class);
//        Call<HttpResult<List<Subject>>> call= movieService.getTopMovie(0,10);
//        call.enqueue(new Callback<HttpResult<List<Subject>>>() {
//            @Override
//            public void onResponse(Call<HttpResult<List<Subject>>> call, Response<HttpResult<List<Subject>>> response) {
//                resultTV.setText(response.body().getSubjects().get(0).toString());
//            }
//
//            @Override
//            public void onFailure(Call<HttpResult<List<Subject>>> call, Throwable t) {
//                resultTV.setText(t.getMessage());
//            }
//        });
//    }

}
