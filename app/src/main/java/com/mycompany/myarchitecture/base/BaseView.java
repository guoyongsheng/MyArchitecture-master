package com.mycompany.myarchitecture.base;

/**
 * Created by lenovo on 2016/8/19.
 * <p>
 * 所有的view都要实现这个接口
 */
public interface BaseView<T> {
    void setPresenter(T presenter); //让view层持有p层的引用
}
