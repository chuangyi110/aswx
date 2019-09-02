package com.lzdn.aswxmall.db.service;

import com.lzdn.aswxmall.db.dao.AswxmallRebatesMapper;
import com.lzdn.aswxmall.db.dao.AswxmallRegionMapper;
import com.lzdn.aswxmall.db.domain.AswxmallRebates;
import com.lzdn.aswxmall.db.domain.AswxmallRebatesExample;
import com.lzdn.aswxmall.db.domain.AswxmallRegionExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AswxmallRebatesService {
    @Resource
    private AswxmallRebatesMapper aswxmallRebatesMapper;


    public List<AswxmallRebates> selectRebatesByUid(Integer uid) {
        AswxmallRebatesExample aswxmallRebatesExample = new AswxmallRebatesExample();
        aswxmallRebatesExample.or().andUserIdEqualTo(uid);
        return aswxmallRebatesMapper.selectByExample(aswxmallRebatesExample);
    }
}
