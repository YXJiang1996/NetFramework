package com.whu.jyx.netframework.contract;

import com.whu.jyx.netframework.bean.info.DemoInfo;
import com.whu.jyx.netframework.bean.request.DemoRequest;
import com.whu.jyx.netframework.controller.IController;
import com.whu.jyx.netframework.exception.ResponseException;
import com.whu.jyx.netframework.ui.IView;

/**
 * @author Jiang.YX
 * @date 2018-12-10
 */
public interface DemoContract {
    interface View extends IView {
        void showSomethingSuccess(DemoInfo info);

        void showSomethingError(ResponseException exp);
    }

    interface Controller extends IController {
        /**
         *
         * @param request
         * @see DemoRequest
         */
        void doSomethingFeedback(DemoRequest request);
    }
}
