package com.mycompany.myarchitecture.entity;

import java.io.Serializable;

/**
 * Created by lenovo on 2016/8/20.
 * 服务器端返回数据的实体
 */
public class ResponseInfo<T> implements Serializable {
    private boolean error; //是否错误
    private T results; //返回数据的结果

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
