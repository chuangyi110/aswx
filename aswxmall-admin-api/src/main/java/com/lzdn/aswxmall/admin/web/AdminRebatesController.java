package com.lzdn.aswxmall.admin.web;

import com.lzdn.aswxmall.admin.service.AdminRebatesService;
import com.lzdn.aswxmall.admin.vo.RebatesVo;
import com.lzdn.aswxmall.core.util.ResponseUtil;
import com.lzdn.aswxmall.core.validator.Order;
import com.lzdn.aswxmall.core.validator.Sort;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/rebates")
@Validated
public class AdminRebatesController {
    @Autowired
    private AdminRebatesService rebatesService;
    @GetMapping("list")
    public Object referrerList(Integer uid,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer limit,
                               @Sort @RequestParam(defaultValue = "id") String sort,
                               @Order @RequestParam(defaultValue = "desc") String order){

        return  rebatesService.countRebatesSeletive(uid,page,limit,sort,order);

    }
}
