package com.imooc.miaosha.redis;

/**
 * Created By wangbo
 * 2019/9/6
 */
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
