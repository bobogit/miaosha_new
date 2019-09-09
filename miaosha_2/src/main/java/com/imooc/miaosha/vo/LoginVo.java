package com.imooc.miaosha.vo;

import lombok.Data;

/**
 * Created By wangbo
 * 2019/9/9
 */
@Data
public class LoginVo {

    private String mobile;
    private String password;

    @Override
    public String toString() {
        return "LoginVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
