package com.lzdn.aswxmall.admin.web;

import com.lzdn.aswxmall.admin.annotation.RequiresPermissionsDesc;
import com.lzdn.aswxmall.admin.vo.RebatesVo;
import com.lzdn.aswxmall.db.domain.AswxmallUser;
import com.lzdn.aswxmall.db.domain.AswxmallUserExample;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.lzdn.aswxmall.core.util.ResponseUtil;
import com.lzdn.aswxmall.core.validator.Order;
import com.lzdn.aswxmall.core.validator.Sort;
import com.lzdn.aswxmall.db.service.AswxmallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
@Validated
public class AdminUserController {
    private final Log logger = LogFactory.getLog(AdminUserController.class);

    @Autowired
    private AswxmallUserService userService;

    @RequiresPermissions("admin:user:list")
    @RequiresPermissionsDesc(menu = {"用户管理", "会员管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String username, String mobile,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<AswxmallUser> userList = userService.querySelective(username, mobile, page, limit, sort, order);
        return ResponseUtil.okList(userList);
    }
    @RequiresPermissions("admin:user:update")
    @RequiresPermissionsDesc(menu = {"用户管理", "会员更新"}, button = "查询")
    @PostMapping("/update")
    public Object update(AswxmallUser aswxmallUser){
        System.out.println(aswxmallUser);
        int num = userService.updateById(aswxmallUser);
        return num ==1?ResponseUtil.ok():ResponseUtil.fail();
    }

}
