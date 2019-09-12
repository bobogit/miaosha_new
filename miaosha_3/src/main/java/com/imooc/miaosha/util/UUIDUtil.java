package com.imooc.miaosha.util;

import java.util.UUID;

/**
 * Created By wangbo
 * 2019/9/10
 */
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
