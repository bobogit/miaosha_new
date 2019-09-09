package com.imooc.miaosha.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created By wangbo
 * 2019/9/9
 */
@Data
public class MiaoshaUser {

    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
