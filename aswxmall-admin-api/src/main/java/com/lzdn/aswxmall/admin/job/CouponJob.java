package com.lzdn.aswxmall.admin.job;

import com.lzdn.aswxmall.db.domain.AswxmallCoupon;
import com.lzdn.aswxmall.db.domain.AswxmallCouponUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lzdn.aswxmall.db.service.AswxmallCouponService;
import com.lzdn.aswxmall.db.service.AswxmallCouponUserService;
import com.lzdn.aswxmall.db.util.CouponConstant;
import com.lzdn.aswxmall.db.util.CouponUserConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 检测优惠券过期情况
 */
@Component
public class CouponJob {
    private final Log logger = LogFactory.getLog(CouponJob.class);

    @Autowired
    private AswxmallCouponService couponService;
    @Autowired
    private AswxmallCouponUserService couponUserService;

    /**
     * 每隔一个小时检查
     * TODO
     * 注意，因为是相隔一个小时检查，因此导致优惠券真正超时时间可能比设定时间延迟1个小时
     */
    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void checkCouponExpired() {
        logger.info("系统开启任务检查优惠券是否已经过期");

        List<AswxmallCoupon> couponList = couponService.queryExpired();
        for (AswxmallCoupon coupon : couponList) {
            coupon.setStatus(CouponConstant.STATUS_EXPIRED);
            couponService.updateById(coupon);
        }

        List<AswxmallCouponUser> couponUserList = couponUserService.queryExpired();
        for (AswxmallCouponUser couponUser : couponUserList) {
            couponUser.setStatus(CouponUserConstant.STATUS_EXPIRED);
            couponUserService.update(couponUser);
        }
    }

}
