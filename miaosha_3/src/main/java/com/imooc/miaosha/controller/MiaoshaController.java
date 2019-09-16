package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.service.GoodsService;
import com.imooc.miaosha.service.OrderService;
import com.imooc.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created By wangbo
 * 2019/9/16
 */
@Controller
@RequestMapping("miaosha")
public class MiaoshaController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;


    @RequestMapping("do_miaosha")
    public String doMiaosha(Model model, MiaoshaUser user, @RequestParam("goodsId") Long goodsId) {
        if(user == null) {
            return "login";
        }
        model.addAttribute("user", user);

        //判断库存
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);

        if(goodsVo.getStockCount() <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }

        //判断是否已经秒杀到了

        return null;
    }

}
