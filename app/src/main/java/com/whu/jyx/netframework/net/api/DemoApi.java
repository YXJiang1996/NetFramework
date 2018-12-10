package com.whu.jyx.netframework.net.api;

import com.whu.jyx.netframework.net.CommonResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 示例网络接口Api
 *
 * @author Jiang.YX
 * @date 2018-12-09
 */
public interface DemoApi {
    /**
     * 示例
     * @param params
     * @return
     */
    @POST("Demo/doSomething")
    @FormUrlEncoded
    Call<CommonResponse> doSomething(@FieldMap(encoded = true) Map<String,String> params);
}
