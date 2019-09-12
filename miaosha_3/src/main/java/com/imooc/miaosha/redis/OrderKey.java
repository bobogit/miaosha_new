package com.imooc.miaosha.redis;

/**
 * Created By wangbo
 * 2019/9/6
 */
public class OrderKey extends BasePrefix {

    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
