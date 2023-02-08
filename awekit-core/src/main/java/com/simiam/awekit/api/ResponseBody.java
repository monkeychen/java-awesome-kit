package com.simiam.awekit.api;

import com.google.common.base.MoreObjects;

/**
 * <p>Title: ResponseBody</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/19 9:06 上午</p>
 */
public class ResponseBody<T> {
    private Long code;

    private String message;

    private String token;

    private T data;

    public ResponseBody(Long code) {
        this.code = code;
    }

    public ResponseBody(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseBody(Long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("message", message)
                .add("token", token)
                .add("data", data)
                .toString();
    }
}
