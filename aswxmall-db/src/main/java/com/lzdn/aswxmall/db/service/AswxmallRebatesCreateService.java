package com.lzdn.aswxmall.db.service;

import com.lzdn.aswxmall.db.dao.AswxmallRebatesCreateOrderMapper;
import com.lzdn.aswxmall.db.domain.AswxmallRebatesCreateOrder;
import com.lzdn.aswxmall.db.domain.AswxmallRebatesCreateOrderExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AswxmallRebatesCreateService {
    @Resource
    private AswxmallRebatesCreateOrderMapper aswxmallRebatesCreateOrderMapper;
    public List<AswxmallRebatesCreateOrder> selectOrderByUid(Integer uid) {
        AswxmallRebatesCreateOrderExample aswxmallRebatesCreateOrderExample = new AswxmallRebatesCreateOrderExample();
        aswxmallRebatesCreateOrderExample.or().andUserIdEqualTo(uid);
        return  aswxmallRebatesCreateOrderMapper.selectByExample(aswxmallRebatesCreateOrderExample);
    }
}
