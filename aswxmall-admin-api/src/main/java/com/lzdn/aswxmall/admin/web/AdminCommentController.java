package com.lzdn.aswxmall.admin.web;

import com.lzdn.aswxmall.admin.annotation.RequiresPermissionsDesc;
import com.lzdn.aswxmall.db.domain.AswxmallComment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.lzdn.aswxmall.core.util.ResponseUtil;
import com.lzdn.aswxmall.core.validator.Order;
import com.lzdn.aswxmall.core.validator.Sort;
import com.lzdn.aswxmall.db.service.AswxmallCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/comment")
@Validated
public class AdminCommentController {
    private final Log logger = LogFactory.getLog(AdminCommentController.class);

    @Autowired
    private AswxmallCommentService commentService;

    @RequiresPermissions("admin:comment:list")
    @RequiresPermissionsDesc(menu = {"商品管理", "评论管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String userId, String valueId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<AswxmallComment> commentList = commentService.querySelective(userId, valueId, page, limit, sort, order);
        return ResponseUtil.okList(commentList);
    }

    @RequiresPermissions("admin:comment:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "评论管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody AswxmallComment comment) {
        Integer id = comment.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        commentService.deleteById(id);
        return ResponseUtil.ok();
    }

}
