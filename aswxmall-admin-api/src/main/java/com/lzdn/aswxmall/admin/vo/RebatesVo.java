package com.lzdn.aswxmall.admin.vo;

import com.lzdn.aswxmall.db.domain.AswxmallRebatesCreateOrder;
import com.lzdn.aswxmall.db.domain.AswxmallRebatesWithdrawalOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class RebatesVo {
    //总金额
    private BigDecimal amount;
    //提现比例
    private BigDecimal ratio;
    //提现金额
    private BigDecimal withdrawMoney;
    //余额
    private BigDecimal balance;
    //下线详情
    private List<RebatesCount> rebatesCountList;
    //提现订单
    private List<AswxmallRebatesWithdrawalOrder> aswxmallRebatesWithdrawalOrderList;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(BigDecimal withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List <RebatesCount> getRebatesCountList() {
        return rebatesCountList;
    }

    public void setRebatesCountList(List <RebatesCount> rebatesCountList) {
        this.rebatesCountList = rebatesCountList;
    }

    public List <AswxmallRebatesWithdrawalOrder> getAswxmallRebatesWithdrawalOrderList() {
        return aswxmallRebatesWithdrawalOrderList;
    }

    public void setAswxmallRebatesWithdrawalOrderList(List <AswxmallRebatesWithdrawalOrder> aswxmallRebatesWithdrawalOrderList) {
        this.aswxmallRebatesWithdrawalOrderList = aswxmallRebatesWithdrawalOrderList;
    }

    public static class RebatesCount{
        private Integer orderUserId;
        private BigDecimal totalRebates;
        private BigDecimal totalOrderActualPrice;
        private Integer orderCount;

        public Integer getOrderUserId() {
            return orderUserId;
        }

        public void setOrderUserId(Integer orderUserId) {
            this.orderUserId = orderUserId;
        }

        public BigDecimal getTotalRebates() {
            return totalRebates;
        }

        public void setTotalRebates(BigDecimal totalRebates) {
            this.totalRebates = totalRebates;
        }

        public BigDecimal getTotalOrderActualPrice() {
            return totalOrderActualPrice;
        }

        public void setTotalOrderActualPrice(BigDecimal totalOrderActualPrice) {
            this.totalOrderActualPrice = totalOrderActualPrice;
        }

        public Integer getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(Integer orderCount) {
            this.orderCount = orderCount;
        }
    }


}
