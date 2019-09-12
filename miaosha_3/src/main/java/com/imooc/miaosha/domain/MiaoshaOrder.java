package com.imooc.miaosha.domain;

import lombok.Data;

/**
 * Created By wangbo
 * 2019/9/11
 */
@Data
public class MiaoshaOrder {
    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;
}
