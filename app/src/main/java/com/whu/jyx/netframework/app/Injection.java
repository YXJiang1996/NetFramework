package com.whu.jyx.netframework.app;

import com.whu.jyx.netframework.net.datasoruce.DemoNetDataSource;

/**
 * 全局依赖注入管理类
 *
 * @author Jiang.YX
 * @date 2018-12-10
 */
public class Injection {
    public static DemoNetDataSource provideDemoNetDataSource(){
        return new DemoNetDataSource();
    }
}
