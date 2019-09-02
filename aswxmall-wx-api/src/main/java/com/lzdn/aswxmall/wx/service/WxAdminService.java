package com.lzdn.aswxmall.wx.service;

import com.lzdn.aswxmall.db.dao.AswxmallAdminMapper;
import com.lzdn.aswxmall.db.domain.AswxmallAdmin;
import com.lzdn.aswxmall.db.domain.AswxmallAdminExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WxAdminService {
    @Resource
    private AswxmallAdminMapper aswxmallAdminMapper;

    public Object findAdminByRoles() {
        AswxmallAdminExample adminExample = new AswxmallAdminExample();
        Integer[] roles = new Integer[1];
        roles[0] = 4;
        adminExample.or().andRoleIdsEqualTo(roles);
        return aswxmallAdminMapper.selectByExample(adminExample);
    }
}
