package com.imooc.miaosha.exception;

import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created By wangbo
 * 2019/9/10
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception ex) {

        if(ex instanceof GlobalException) {
            GlobalException globalException = (GlobalException) ex;
            CodeMsg codeMsg = ((GlobalException) ex).getCodeMsg();
            return Result.error(codeMsg);
        }else if(ex instanceof BindException) {
            BindException bex = (BindException) ex;

            List<ObjectError> objectErrorList = ((BindException) ex).getAllErrors();
            ObjectError objectError = objectErrorList.get(0);

            String msg = objectError.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        }else{
            return Result.error(CodeMsg.SERVER_ERROR);
        }

    }
}
