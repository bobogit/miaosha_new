package com.imooc.miaosha.controller;

import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.MiaoshaUserService;
import com.imooc.miaosha.service.UserService;
import com.imooc.miaosha.util.ValidatorUtil;
import com.imooc.miaosha.vo.LoginVo;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created By wangbo
 * 2019/9/9
 */
@Controller
@RequestMapping("login")
@Log
public class LoginController {

    @Autowired
    private MiaoshaUserService userService;

    @RequestMapping("to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("do_login")
    @ResponseBody
    public Result<Boolean> doLogin(LoginVo loginVo) {
        log.info(loginVo.toString());
        if(StringUtils.isEmpty(loginVo.getPassword())){
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }

        if(StringUtils.isEmpty(loginVo.getMobile())){
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }

        if(!ValidatorUtil.isMobile(loginVo.getMobile())) {
            return Result.error(CodeMsg.MOBILE_ERROR);
        }

        //登录
        CodeMsg codeMsg = userService.login(loginVo);
        if(codeMsg.getCode() == 0) {
            return Result.success(true);
        }else{
            return Result.error(codeMsg);
        }
    }
}
