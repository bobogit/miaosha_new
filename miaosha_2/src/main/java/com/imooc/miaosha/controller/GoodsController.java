package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.MiaoshaUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By wangbo
 * 2019/9/10
 */
@Controller
@RequestMapping("goods")
public class GoodsController {

    @RequestMapping("to_list")
    public String toList(Model model){
        model.addAttribute("user", new MiaoshaUser());
        return "goods_list";
    }
}
