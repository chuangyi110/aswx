package com.lzdn.aswxmall.wx.web;

import com.lzdn.aswxmall.db.domain.AswxmallUser;
import com.lzdn.aswxmall.db.domain.AswxmallUserFormid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lzdn.aswxmall.core.util.ResponseUtil;
import com.lzdn.aswxmall.db.service.AswxmallUserFormIdService;
import com.lzdn.aswxmall.db.service.AswxmallUserService;
import com.lzdn.aswxmall.wx.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/wx/formid")
@Validated
public class WxUserFormId {
    private final Log logger = LogFactory.getLog(WxUserFormId.class);

    @Autowired
    private AswxmallUserService userService;

    @Autowired
    private AswxmallUserFormIdService formIdService;

    @GetMapping("create")
    public Object create(@LoginUser Integer userId, @NotNull String formId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        AswxmallUser user = userService.findById(userId);
        AswxmallUserFormid userFormid = new AswxmallUserFormid();
        userFormid.setOpenid(user.getWeixinOpenid());
        userFormid.setFormid(formId);
        userFormid.setIsprepay(false);
        userFormid.setUseamount(1);
        userFormid.setExpireTime(LocalDateTime.now().plusDays(7));
        formIdService.addUserFormid(userFormid);

        return ResponseUtil.ok();
    }
}
