package com.whu.jyx.netframework.controller;

import com.whu.jyx.netframework.app.Injection;
import com.whu.jyx.netframework.bean.info.DemoInfo;
import com.whu.jyx.netframework.bean.request.DemoRequest;
import com.whu.jyx.netframework.contract.DemoContract;
import com.whu.jyx.netframework.exception.ResponseException;
import com.whu.jyx.netframework.net.datasoruce.DemoNetDataSource;

public class DemoController implements DemoContract.Controller {
    private DemoContract.View mView;
    private DemoNetDataSource demoNetData;

    public DemoController(DemoContract.View mView){
        this.mView=mView;
        demoNetData=Injection.provideDemoNetDataSource();
    }

    @Override
    public void doSomethingFeedback(DemoRequest request) {
        demoNetData.doSomething(request, new ControllerCallback<DemoInfo>() {
            @Override
            public void onSuccess(DemoInfo demoInfo) {
                mView.showSomethingSuccess(demoInfo);
            }

            @Override
            public void onError(ResponseException exp) {
                mView.showSomethingError(exp);
            }
        });
    }

    @Override
    public void onDestroy() {
        demoNetData.cancelAll();
        mView=null;
    }
}
