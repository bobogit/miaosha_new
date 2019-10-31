package com.imooc.miaosha.domain;

import lombok.Data;

/**
 * Created By wangbo
 * 2019/9/11
 */
@Data
public class Goods {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;
}
