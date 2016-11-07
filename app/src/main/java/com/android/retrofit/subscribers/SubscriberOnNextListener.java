package com.android.retrofit.subscribers;

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}