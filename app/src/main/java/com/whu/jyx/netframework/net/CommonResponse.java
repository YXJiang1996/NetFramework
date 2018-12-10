package com.whu.jyx.netframework.net;

/**
 * 响应公共实体类
 *
 * @author Wang.J
 * @date 2016-10-23
 */
public class CommonResponse {
    private int code;
    private Object data;
    private String message;


    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
