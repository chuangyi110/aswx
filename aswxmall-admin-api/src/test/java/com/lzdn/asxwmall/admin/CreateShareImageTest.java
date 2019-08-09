package com.lzdn.asxwmall.admin;

import com.lzdn.aswxmall.db.domain.AswxmallGoods;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.lzdn.aswxmall.core.qcode.QCodeService;
import com.lzdn.aswxmall.db.service.AswxmallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CreateShareImageTest {
    @Autowired
    QCodeService qCodeService;
    @Autowired
    AswxmallGoodsService AswxmallGoodsService;

    @Test
    public void test() {
        AswxmallGoods good = AswxmallGoodsService.findById(1181010);
        qCodeService.createGoodShareImage(good.getId().toString(), good.getPicUrl(), good.getName());
    }
}
