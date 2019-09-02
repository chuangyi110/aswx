package com.lzdn.aswxmall.wx.web;

import com.lzdn.aswxmall.core.util.ResponseUtil;
import com.lzdn.aswxmall.core.validator.Order;
import com.lzdn.aswxmall.core.validator.Sort;
import com.lzdn.aswxmall.db.domain.AswxmallAdmin;
import com.lzdn.aswxmall.db.service.AswxmallAdminService;
import com.lzdn.aswxmall.wx.service.WxAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wx/service")
@Validated
public class WxServiceController {
    @Autowired
    private WxAdminService wxAdminService;

    @RequestMapping("/list")
    public Object serviceDetail(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer size,
                                @Sort @RequestParam(defaultValue = "id") String sort,
                                @Order @RequestParam(defaultValue = "desc") String order){

        return ResponseUtil.ok(wxAdminService.findAdminByRoles());
    }
}
