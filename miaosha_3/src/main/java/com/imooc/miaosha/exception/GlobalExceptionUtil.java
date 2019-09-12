package com.imooc.miaosha.exception;

import com.imooc.miaosha.result.CodeMsg;

/**
 * Created By wangbo
 * 2019/9/10
 */
public class GlobalExceptionUtil {

    public static GlobalException checkException(CodeMsg codeMsg) {
        return new GlobalException(codeMsg);
    }
}
