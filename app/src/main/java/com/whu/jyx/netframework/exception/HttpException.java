package com.whu.jyx.netframework.exception;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * 网络异常类
 *
 * @author 汪俊
 * @version 1.0
 * @date 2016-10-23
 */
public class HttpException extends ResponseException{

    public static final int ERROR_TIME_OUT= -2;
    public static final int ERROR_BAD_NET = -3;
    public static final int ERROR_OTHERS = -4;



    public HttpException(Throwable t){
        super(t.getMessage());
        if(t instanceof SocketTimeoutException){
            code = ERROR_TIME_OUT;
        }else if(t instanceof ConnectException){
            code = ERROR_BAD_NET;
        }else{
            code = ResponseException.ERROR_UNKOWN;
        }
    }


}
