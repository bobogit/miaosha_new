package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;

/**
 * Created By wangbo
 * 2019/9/3
 */
@Controller
@RequestMapping("demo")
public class SampleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/")
    @ResponseBody
    Result<String> home() {
        return Result.success("hello world");
    }

    Result<String> helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("thymeleaf")
    public String thymeleaf(Model model) {

        model.addAttribute("name", "world");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet(){
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx(){
        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){
        User user= redisService.get(UserKey.getById, "" + 1, User.class);
        return Result.success(user);
    }


    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet(){
        User user = new User(1, "11111");
        boolean ret = redisService.set(UserKey.getById, "1", user);
        return Result.success(ret);
    }


}


