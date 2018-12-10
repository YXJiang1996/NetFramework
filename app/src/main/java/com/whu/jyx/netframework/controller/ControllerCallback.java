package com.whu.jyx.netframework.controller;


import com.whu.jyx.netframework.exception.ResponseException;

/**
 * 控制器回调接口
 *
 * @param <T> 成功回调的泛型实体参数
 * @author 汪俊
 * @date 2016-10-18
 */
public interface ControllerCallback<T> {

    void onSuccess(T t);

    void onError(ResponseException exp);

}