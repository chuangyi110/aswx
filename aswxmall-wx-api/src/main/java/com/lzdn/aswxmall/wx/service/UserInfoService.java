package com.lzdn.aswxmall.wx.service;

import com.lzdn.aswxmall.db.domain.AswxmallUser;
import com.lzdn.aswxmall.db.service.AswxmallUserService;
import com.lzdn.aswxmall.wx.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserInfoService {
    @Autowired
    private AswxmallUserService userService;


    public UserInfo getInfo(Integer userId) {
        AswxmallUser user = userService.findById(userId);
        Assert.state(user != null, "用户不存在");
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(user.getNickname());
        userInfo.setAvatarUrl(user.getAvatar());
        return userInfo;
    }
}
