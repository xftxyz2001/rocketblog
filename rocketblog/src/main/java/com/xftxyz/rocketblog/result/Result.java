package com.xftxyz.rocketblog.result;

import java.io.Serializable;

import lombok.Data;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 返回编码
     */
    private String code;

    /**
     * 编码描述
     */
    private String msg;

    /**
     * 业务数据
     */
    private T data;

    /**
     * 返回成功结果对象
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        return result;
    }

    public static <T> Result<T> fail(ResultCode resultCodes) {
        Result<T> result = new Result<T>();
        result.setCode(resultCodes.getCode());
        result.setMsg(resultCodes.getMsg());
        return result;
    }

    public static <T> Result<T> fail(String code, String msg) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
