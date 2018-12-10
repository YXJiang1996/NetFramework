package com.whu.jyx.netframework.exception;

/**
 * 响应公共异常类
 *
 * @author 汪俊
 * @version 1.0
 * @date 2016-10-23
 */
public class ResponseException extends Exception {

    public static final int CODE_SUCCESS = 200;
    public static final int ERROR_UNKOWN = -1;

    protected int code;

    public ResponseException(){}

    public ResponseException(String message){
        super(message);
    }

    public ResponseException(int code, String message){
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
