package com.imooc.miaosha.redis;

/**
 * Created By wangbo
 * 2019/9/10
 */
public class MiaoshaUserKey extends BasePrefix {

    private MiaoshaUserKey(String prefix) {
        super(prefix);
    }

    public static MiaoshaUserKey token = new MiaoshaUserKey("tk");
}
