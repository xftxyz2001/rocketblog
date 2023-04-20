package com.xftxyz.rocketblog.result;

public class Result<T> {
    private Integer code; // code: 状态码
    private String message; // message: 接口调用的提示信息
    private T data; // data: 返回数据

    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功返回结果
     * @param <T>
     * @param data 返回的数据
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }
    
    /**
     * 失败返回失败信息
     * @param <T>
     * @param code 错误码
     * @param message 错误信息
     * @return
     */
    public static Result<Object> error(Integer code, String message) {
        return new Result<>(code, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
}
