package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.MiaoshaUserDao;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.exception.GlobalException;
import com.imooc.miaosha.exception.GlobalExceptionUtil;
import com.imooc.miaosha.redis.MiaoshaUserKey;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.util.MD5Util;
import com.imooc.miaosha.util.UUIDUtil;
import com.imooc.miaosha.vo.LoginVo;
import com.sun.org.apache.xerces.internal.parsers.DTDParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By wangbo
 * 2019/9/9
 */
@Service
public class MiaoshaUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private MiaoshaUserDao miaoshaUserDao;

    @Autowired
    private RedisService redisService;

    public MiaoshaUser getById(long id) {
        return miaoshaUserDao.getById(id);
    }

    public boolean login(LoginVo loginVo, HttpServletResponse response) {
        if(loginVo == null) {
            GlobalExceptionUtil.checkException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        MiaoshaUser miaoshaUser = getById(Long.valueOf(mobile));
        if(miaoshaUser == null)
            GlobalExceptionUtil.checkException(CodeMsg.MOBILE_NOT_EXIST);
        //验证密码
        String dbPass = miaoshaUser.getPassword();
        String dbSalt = miaoshaUser.getSalt();

        String calcPass = MD5Util.formPassToDBPass(loginVo.getPassword(), dbSalt);
        if(calcPass.equals(dbPass)) {
            GlobalExceptionUtil.checkException(CodeMsg.PASSWORD_ERROR);
        }

        addCookie(response, miaoshaUser);

        return true;
    }

    public MiaoshaUser getByToken(String token, HttpServletResponse response) {
        if(StringUtils.isEmpty(token))
            return null;

        MiaoshaUser miaoshaUser = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);

        if(miaoshaUser != null) {
            //延长有效期
            addCookie(response, miaoshaUser);
        }

        return miaoshaUser;
    }

    private void addCookie(HttpServletResponse response, MiaoshaUser miaoshaUser){

        //生成cookie
        String token = UUIDUtil.uuid();
        redisService.set(MiaoshaUserKey.token, token, miaoshaUser);

        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");

        response.addCookie(cookie);
    }
}
