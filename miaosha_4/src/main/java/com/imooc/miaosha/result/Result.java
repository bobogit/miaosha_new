package com.imooc.miaosha.result;

import lombok.Data;
import lombok.Getter;

/**
 * Created By wangbo
 * 2019/9/3
 */
@Getter
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if (cm != null) {
            this.code = cm.getCode();
            this.msg = cm.getMsg();
        }
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<T>(cm);
    }
}
