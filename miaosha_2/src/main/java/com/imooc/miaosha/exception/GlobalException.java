package com.imooc.miaosha.exception;

import com.imooc.miaosha.result.CodeMsg;
import lombok.Getter;

/**
 * Created By wangbo
 * 2019/9/10
 */
@Getter
public class GlobalException extends  RuntimeException{


    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super();
        this.codeMsg = codeMsg;
    }
}
