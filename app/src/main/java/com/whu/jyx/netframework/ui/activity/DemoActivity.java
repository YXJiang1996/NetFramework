package com.whu.jyx.netframework.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.whu.jyx.netframework.R;
import com.whu.jyx.netframework.bean.info.DemoInfo;
import com.whu.jyx.netframework.bean.request.DemoRequest;
import com.whu.jyx.netframework.contract.DemoContract;
import com.whu.jyx.netframework.controller.DemoController;
import com.whu.jyx.netframework.exception.ResponseException;

public class DemoActivity extends AppCompatActivity implements DemoContract.View {

    private DemoController mDemoController;
    private DemoRequest mDemoRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        mDemoController=new DemoController(this);
        mDemoRequest=new DemoRequest().setParam1("param1").setParam2("param2");
        mDemoController.doSomethingFeedback(mDemoRequest);
    }

    @Override
    public void showSomethingSuccess(DemoInfo info) {
        Log.i("state","success");
    }

    @Override
    public void showSomethingError(ResponseException exp) {
        Log.i("state","fail");
    }
}
