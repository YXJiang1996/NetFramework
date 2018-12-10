package com.whu.jyx.netframework.net;

import com.whu.jyx.netframework.net.api.DemoApi;

/**
 * 网络接口Api提供者
 *
 * @author Jiang.YX
 * @date 2018-12-09
 */
public class ApiProvider {
    private static DemoApi mDemoApi;

    public static DemoApi getmDemoApi(){
        if(mDemoApi==null){
            mDemoApi=ApiService.getmInstance().createApi(DemoApi.class);
        }
        return mDemoApi;
    }
}
