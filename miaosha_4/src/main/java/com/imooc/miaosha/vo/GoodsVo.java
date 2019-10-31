package com.imooc.miaosha.vo;

import com.imooc.miaosha.domain.Goods;
import lombok.Data;

import java.util.Date;

/**
 * Created By wangbo
 * 2019/9/11
 */
@Data
public class GoodsVo extends Goods {

    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
