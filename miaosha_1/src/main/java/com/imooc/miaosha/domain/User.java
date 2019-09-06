package com.imooc.miaosha.domain;

import lombok.Data;

/**
 * Created By wangbo
 * 2019/9/3
 */
@Data
public class User {
    private Integer id;
    private String name;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
