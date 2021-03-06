package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created By wangbo
 * 2019/9/3
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private MiaoshaUserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("info")
    @ResponseBody
    public Result<MiaoshaUser> info(MiaoshaUser user) {
        return Result.success(user);
    }
}
