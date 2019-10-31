package com.imooc.miaosha.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created By wangbo
 * 2019/9/9
 */
public class ValidatorUtil {

    private static final Pattern PATTERN = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String mobile) {
        if(StringUtils.isEmpty(mobile))
            return false;

        Matcher matcher = PATTERN.matcher(mobile);
        return matcher.matches();
    }

    	public static void main(String[] args) {
			System.out.println(isMobile("18912341234"));
			System.out.println(isMobile("1891234123"));
	}
}
