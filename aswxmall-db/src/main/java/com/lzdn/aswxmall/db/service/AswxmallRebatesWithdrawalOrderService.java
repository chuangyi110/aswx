package com.lzdn.aswxmall.db.service;

import com.lzdn.aswxmall.db.dao.AswxmallRebatesWithdrawalOrderMapper;
import com.lzdn.aswxmall.db.domain.AswxmallRebatesCreateOrder;
import com.lzdn.aswxmall.db.domain.AswxmallRebatesWithdrawalOrder;
import com.lzdn.aswxmall.db.domain.AswxmallRebatesWithdrawalOrderExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AswxmallRebatesWithdrawalOrderService {
    @Resource
    private AswxmallRebatesWithdrawalOrderMapper aswxmallRebatesWithdrawalOrderMapper;


    public List<AswxmallRebatesWithdrawalOrder> selectOrderByUid(Integer uid) {
        AswxmallRebatesWithdrawalOrderExample rebatesWithdrawalOrderExample = new AswxmallRebatesWithdrawalOrderExample();
        rebatesWithdrawalOrderExample.or().andUserIdEqualTo(uid);
        return aswxmallRebatesWithdrawalOrderMapper.selectByExample(rebatesWithdrawalOrderExample);
    }
}
