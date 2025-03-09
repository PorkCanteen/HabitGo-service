package com.example.habitgoservice.common;

import lombok.Data;

@Data
public class Result {

    private static final String SUCCESS_CODE = "0";
    private static final String ERROR_CODE = "1001";

    private String code; // 状态码
    private String message; // 信息
    private Object data; // 数据

    public static Result success() {
        Result result =  new Result();
        result.setCode(SUCCESS_CODE);
        return result;
    }

    public static Result success(Object data) {
        Result result =  new Result();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    public static Result error(String message) {
        Result result =  new Result();
        result.setCode(ERROR_CODE);
        result.setMessage(message);
        return result;
    }
}
