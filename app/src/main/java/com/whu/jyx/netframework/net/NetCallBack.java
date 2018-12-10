package com.whu.jyx.netframework.net;

import com.orhanobut.logger.Logger;
import com.whu.jyx.netframework.BuildConfig;
import com.whu.jyx.netframework.controller.ControllerCallback;
import com.whu.jyx.netframework.exception.HttpException;
import com.whu.jyx.netframework.exception.ResponseException;
import com.whu.jyx.netframework.util.JsonUtil;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 网络回调接口基类
 *
 * @author 汪俊
 * @date 2016-10-23
 */
public class NetCallBack<T> implements Callback<CommonResponse> {

    private ControllerCallback<T> mCallback;
    private Type mClazzType;

    public NetCallBack(ControllerCallback<T> callback, Type type) {
        mCallback = callback;
        mClazzType = type;
    }

    @Override
    public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
        CommonResponse bean = response.body();
        if(BuildConfig.DEBUG){
            //TODO 应该过滤掉太长的输出
            Logger.json(JsonUtil.toJson(bean));
        }
        try {
            if (bean.getCode() == ResponseException.CODE_SUCCESS) {
                String json = JsonUtil.toJson(bean.getData());
                T info = JsonUtil.fromJson(json, mClazzType);
                mCallback.onSuccess(info);
            } else {
                mCallback.onError(getException(bean.getCode(), bean.getMessage()));
            }
        }catch (Exception e){
            if(BuildConfig.DEBUG){
                e.printStackTrace();
            }
            mCallback.onError(getException(ResponseException.ERROR_UNKOWN, e.getMessage()));
        }
    }

    @Override
    public void onFailure(Call<CommonResponse> call, Throwable t) {
        //如果当前请求已取消，则忽略
        if(call.isCanceled()){
            return;
        }
        mCallback.onError(new HttpException(t));
    }

    protected ResponseException getException(int code, String message){
        return new ResponseException(code, message);
    }

}
