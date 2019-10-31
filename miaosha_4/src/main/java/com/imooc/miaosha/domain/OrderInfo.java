package com.imooc.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created By wangbo
 * 2019/9/11
 */
@Data
public class OrderInfo {

    private Long id;
    private Long userId;
    private Long goodsId;
    private Long  deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;
}
