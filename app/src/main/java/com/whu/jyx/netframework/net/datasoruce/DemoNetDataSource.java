package com.whu.jyx.netframework.net.datasoruce;

import com.whu.jyx.netframework.bean.info.DemoInfo;
import com.whu.jyx.netframework.bean.request.DemoRequest;
import com.whu.jyx.netframework.controller.ControllerCallback;
import com.whu.jyx.netframework.exception.ResponseException;
import com.whu.jyx.netframework.net.ApiProvider;
import com.whu.jyx.netframework.net.BaseNetDataSource;
import com.whu.jyx.netframework.net.CommonResponse;
import com.whu.jyx.netframework.net.NetCallBack;
import com.whu.jyx.netframework.net.api.DemoApi;
import com.whu.jyx.netframework.util.JsonUtil;
import com.whu.jyx.netframework.util.SecurityUtil;

import retrofit2.Call;

/**
 * @author Jiang.YX
 * @date 2018-12-10
 */
public class DemoNetDataSource extends BaseNetDataSource {

    private DemoApi mDemoApi;
    public DemoNetDataSource(){
        mDemoApi=ApiProvider.getmDemoApi();
    }

    public void doSomething(DemoRequest request, final ControllerCallback<DemoInfo> callback){
        //对密码之类的参数进行MD5加密
        request.setParam1(SecurityUtil.MD5encode(request.getParam1()));

        //对所有参数进行Base64加密
        Call<CommonResponse> call=mDemoApi.doSomething(SecurityUtil.Base64EncodeMap(JsonUtil.convertObject2Map(request)));

        call.enqueue(new NetCallBack<DemoInfo>(callback,DemoInfo.class){
            @Override
            protected ResponseException getException(int code, String message) {
                return super.getException(code, message);
            }
        });
        mCallList.add(call);
    }
}
