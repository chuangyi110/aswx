package com.lzdn.aswxmall.db.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class AswxmallRebatesWithdrawalOrder {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_rebates_withdrawal_order.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_rebates_withdrawal_order.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_rebates_withdrawal_order.before_amount
     *
     * @mbg.generated
     */
    private BigDecimal beforeAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_rebates_withdrawal_order.withdraw_money
     *
     * @mbg.generated
     */
    private BigDecimal withdrawMoney;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_rebates_withdrawal_order.balance
     *
     * @mbg.generated
     */
    private BigDecimal balance;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_rebates_withdrawal_order.withdraw_time
     *
     * @mbg.generated
     */
    private LocalDateTime withdrawTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_rebates_withdrawal_order.action_ip
     *
     * @mbg.generated
     */
    private String actionIp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_rebates_withdrawal_order.action_client
     *
     * @mbg.generated
     */
    private String actionClient;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_rebates_withdrawal_order.status
     *
     * @mbg.generated
     */
    private Short status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_rebates_withdrawal_order.admin_id
     *
     * @mbg.generated
     */
    private Integer adminId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_rebates_withdrawal_order.approve_time
     *
     * @mbg.generated
     */
    private LocalDateTime approveTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_rebates_withdrawal_order.id
     *
     * @return the value of aswxmall_rebates_withdrawal_order.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_rebates_withdrawal_order.id
     *
     * @param id the value for aswxmall_rebates_withdrawal_order.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_rebates_withdrawal_order.user_id
     *
     * @return the value of aswxmall_rebates_withdrawal_order.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_rebates_withdrawal_order.user_id
     *
     * @param userId the value for aswxmall_rebates_withdrawal_order.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_rebates_withdrawal_order.before_amount
     *
     * @return the value of aswxmall_rebates_withdrawal_order.before_amount
     *
     * @mbg.generated
     */
    public BigDecimal getBeforeAmount() {
        return beforeAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_rebates_withdrawal_order.before_amount
     *
     * @param beforeAmount the value for aswxmall_rebates_withdrawal_order.before_amount
     *
     * @mbg.generated
     */
    public void setBeforeAmount(BigDecimal beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_rebates_withdrawal_order.withdraw_money
     *
     * @return the value of aswxmall_rebates_withdrawal_order.withdraw_money
     *
     * @mbg.generated
     */
    public BigDecimal getWithdrawMoney() {
        return withdrawMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_rebates_withdrawal_order.withdraw_money
     *
     * @param withdrawMoney the value for aswxmall_rebates_withdrawal_order.withdraw_money
     *
     * @mbg.generated
     */
    public void setWithdrawMoney(BigDecimal withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_rebates_withdrawal_order.balance
     *
     * @return the value of aswxmall_rebates_withdrawal_order.balance
     *
     * @mbg.generated
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_rebates_withdrawal_order.balance
     *
     * @param balance the value for aswxmall_rebates_withdrawal_order.balance
     *
     * @mbg.generated
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_rebates_withdrawal_order.withdraw_time
     *
     * @return the value of aswxmall_rebates_withdrawal_order.withdraw_time
     *
     * @mbg.generated
     */
    public LocalDateTime getWithdrawTime() {
        return withdrawTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_rebates_withdrawal_order.withdraw_time
     *
     * @param withdrawTime the value for aswxmall_rebates_withdrawal_order.withdraw_time
     *
     * @mbg.generated
     */
    public void setWithdrawTime(LocalDateTime withdrawTime) {
        this.withdrawTime = withdrawTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_rebates_withdrawal_order.action_ip
     *
     * @return the value of aswxmall_rebates_withdrawal_order.action_ip
     *
     * @mbg.generated
     */
    public String getActionIp() {
        return actionIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_rebates_withdrawal_order.action_ip
     *
     * @param actionIp the value for aswxmall_rebates_withdrawal_order.action_ip
     *
     * @mbg.generated
     */
    public void setActionIp(String actionIp) {
        this.actionIp = actionIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_rebates_withdrawal_order.action_client
     *
     * @return the value of aswxmall_rebates_withdrawal_order.action_client
     *
     * @mbg.generated
     */
    public String getActionClient() {
        return actionClient;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_rebates_withdrawal_order.action_client
     *
     * @param actionClient the value for aswxmall_rebates_withdrawal_order.action_client
     *
     * @mbg.generated
     */
    public void setActionClient(String actionClient) {
        this.actionClient = actionClient;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_rebates_withdrawal_order.status
     *
     * @return the value of aswxmall_rebates_withdrawal_order.status
     *
     * @mbg.generated
     */
    public Short getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_rebates_withdrawal_order.status
     *
     * @param status the value for aswxmall_rebates_withdrawal_order.status
     *
     * @mbg.generated
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_rebates_withdrawal_order.admin_id
     *
     * @return the value of aswxmall_rebates_withdrawal_order.admin_id
     *
     * @mbg.generated
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_rebates_withdrawal_order.admin_id
     *
     * @param adminId the value for aswxmall_rebates_withdrawal_order.admin_id
     *
     * @mbg.generated
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_rebates_withdrawal_order.approve_time
     *
     * @return the value of aswxmall_rebates_withdrawal_order.approve_time
     *
     * @mbg.generated
     */
    public LocalDateTime getApproveTime() {
        return approveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_rebates_withdrawal_order.approve_time
     *
     * @param approveTime the value for aswxmall_rebates_withdrawal_order.approve_time
     *
     * @mbg.generated
     */
    public void setApproveTime(LocalDateTime approveTime) {
        this.approveTime = approveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates_withdrawal_order
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", beforeAmount=").append(beforeAmount);
        sb.append(", withdrawMoney=").append(withdrawMoney);
        sb.append(", balance=").append(balance);
        sb.append(", withdrawTime=").append(withdrawTime);
        sb.append(", actionIp=").append(actionIp);
        sb.append(", actionClient=").append(actionClient);
        sb.append(", status=").append(status);
        sb.append(", adminId=").append(adminId);
        sb.append(", approveTime=").append(approveTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates_withdrawal_order
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AswxmallRebatesWithdrawalOrder other = (AswxmallRebatesWithdrawalOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getBeforeAmount() == null ? other.getBeforeAmount() == null : this.getBeforeAmount().equals(other.getBeforeAmount()))
            && (this.getWithdrawMoney() == null ? other.getWithdrawMoney() == null : this.getWithdrawMoney().equals(other.getWithdrawMoney()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getWithdrawTime() == null ? other.getWithdrawTime() == null : this.getWithdrawTime().equals(other.getWithdrawTime()))
            && (this.getActionIp() == null ? other.getActionIp() == null : this.getActionIp().equals(other.getActionIp()))
            && (this.getActionClient() == null ? other.getActionClient() == null : this.getActionClient().equals(other.getActionClient()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAdminId() == null ? other.getAdminId() == null : this.getAdminId().equals(other.getAdminId()))
            && (this.getApproveTime() == null ? other.getApproveTime() == null : this.getApproveTime().equals(other.getApproveTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates_withdrawal_order
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getBeforeAmount() == null) ? 0 : getBeforeAmount().hashCode());
        result = prime * result + ((getWithdrawMoney() == null) ? 0 : getWithdrawMoney().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getWithdrawTime() == null) ? 0 : getWithdrawTime().hashCode());
        result = prime * result + ((getActionIp() == null) ? 0 : getActionIp().hashCode());
        result = prime * result + ((getActionClient() == null) ? 0 : getActionClient().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAdminId() == null) ? 0 : getAdminId().hashCode());
        result = prime * result + ((getApproveTime() == null) ? 0 : getApproveTime().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table aswxmall_rebates_withdrawal_order
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        userId("user_id", "userId", "INTEGER", false),
        beforeAmount("before_amount", "beforeAmount", "DECIMAL", false),
        withdrawMoney("withdraw_money", "withdrawMoney", "DECIMAL", false),
        balance("balance", "balance", "DECIMAL", false),
        withdrawTime("withdraw_time", "withdrawTime", "TIMESTAMP", false),
        actionIp("action_ip", "actionIp", "VARCHAR", false),
        actionClient("action_client", "actionClient", "VARCHAR", false),
        status("status", "status", "SMALLINT", true),
        adminId("admin_id", "adminId", "INTEGER", false),
        approveTime("approve_time", "approveTime", "TIMESTAMP", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_rebates_withdrawal_order
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}