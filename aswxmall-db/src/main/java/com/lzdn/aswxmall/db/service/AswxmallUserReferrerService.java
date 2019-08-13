package com.lzdn.aswxmall.db.service;

import com.lzdn.aswxmall.db.dao.AswxmallUserReferrerMapper;
import com.lzdn.aswxmall.db.domain.AswxmallUserReferrer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AswxmallUserReferrerService {
    @Resource
    private AswxmallUserReferrerMapper aswxmallUserReferrerMapper;

    public void add(AswxmallUserReferrer aswxmallUserReferrer){
        aswxmallUserReferrerMapper.insert(aswxmallUserReferrer);
    }

}
