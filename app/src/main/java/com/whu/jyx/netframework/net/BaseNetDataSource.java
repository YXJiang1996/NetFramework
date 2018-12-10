package com.whu.jyx.netframework.net;

import com.whu.jyx.netframework.util.JsonUtil;
import com.whu.jyx.netframework.util.SecurityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;


/**
 * 网络数据源基类
 *
 * @author Wang.J
 * @date 2016-10-23
 */
public class BaseNetDataSource {

    protected List<Call> mCallList=new ArrayList<>(4);

    /**
     * 取消当前对象的所有请求
     */
    public void cancelAll(){
        for(Call call:mCallList){
            if(call.isExecuted()&&!call.isCanceled()){
                call.cancel();
            }
        }
    }

    protected Map<String,String> processAndGetRequestMap(Object request){
        return SecurityUtil.Base64EncodeMap(JsonUtil.convertObject2Map(request));
    }
}
