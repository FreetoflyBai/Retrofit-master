package com.android.retrofit.entity;

/**
 * Description:
 * Author     : kevin.bai
 * Time       : 2016/11/4 13:07
 * QQ         : 904869397@qq.com
 */

public class HttpResult<T> {
    private int count;
    private int start;
    private int total;
    private String title;
    private T subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }
}
