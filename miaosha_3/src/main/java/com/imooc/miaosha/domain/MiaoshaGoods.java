package com.imooc.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created By wangbo
 * 2019/9/11
 */
@Data
public class MiaoshaGoods {
    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
