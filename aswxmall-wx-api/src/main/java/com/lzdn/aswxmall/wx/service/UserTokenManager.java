package com.lzdn.aswxmall.wx.service;

import com.lzdn.aswxmall.wx.util.JwtHelper;

/**
 * 维护用户token
 * 2019.10.15 Token内部添加用户等级信息
 */
public class UserTokenManager {
    //
    public static String generateToken(Integer id,Byte userLevel) {
        JwtHelper jwtHelper = new JwtHelper();
        return jwtHelper.createToken(id,userLevel);
    }
    public static Integer getUserId(String token) {
    	JwtHelper jwtHelper = new JwtHelper();
    	Integer userId = jwtHelper.verifyTokenAndGetUserId(token);
    	if(userId == null || userId == 0){
    		return null;
    	}
        return userId;
    }
    public static Byte getUserLevel(String token) {
        JwtHelper jwtHelper = new JwtHelper();
        Byte userLevel = jwtHelper.verifyTokenAndGetUserLevel(token);
        if(userLevel == null || userLevel == -1){
            return null;
        }
        return userLevel;
    }
}
