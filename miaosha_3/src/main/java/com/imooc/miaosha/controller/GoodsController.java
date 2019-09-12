package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.GoodsService;
import com.imooc.miaosha.service.MiaoshaUserService;
import com.imooc.miaosha.vo.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created By wangbo
 * 2019/9/10
 */
@Controller
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private MiaoshaUserService userService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("to_list")
    public String toList(Model model
//            , @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String cookieToken
//            , @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String paramToken
                         , MiaoshaUser user
            , HttpServletResponse response) {

        model.addAttribute("user", user);

        List<GoodsVo> goodsVoList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsVoList);

        return "goods_list";
    }

    @RequestMapping("to_detail/{goodsId}")
    public String detail(Model model, MiaoshaUser user, @PathVariable Long goodsId) {

        model.addAttribute("user", user);
        //id生成策略 snowflake

        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goodsVo);

        long startAt = goodsVo.getStartDate().getTime();
        long endAt = goodsVo.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remianSeconds = 0;

        if(now<startAt) {
            //秒杀还没开始,倒计时
            remianSeconds = (int) ((startAt - now) / 1000);
        } else if(now > endAt) {
            //秒杀已经结束
            miaoshaStatus = 2;
            remianSeconds = -1;
        } else{
            //秒杀进行中
            miaoshaStatus = 1;
        }

        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remianSeconds", remianSeconds);


        return "goods_detail";
    }


}
