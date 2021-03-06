package com.lzdn.aswxmall.db.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class AswxmallUserReferrer {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_user_referrer.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_user_referrer.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column aswxmall_user_referrer.referrer_user_id
     *
     * @mbg.generated
     */
    private Integer referrerUserId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_user_referrer.id
     *
     * @return the value of aswxmall_user_referrer.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_user_referrer.id
     *
     * @param id the value for aswxmall_user_referrer.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_user_referrer.user_id
     *
     * @return the value of aswxmall_user_referrer.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_user_referrer.user_id
     *
     * @param userId the value for aswxmall_user_referrer.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column aswxmall_user_referrer.referrer_user_id
     *
     * @return the value of aswxmall_user_referrer.referrer_user_id
     *
     * @mbg.generated
     */
    public Integer getReferrerUserId() {
        return referrerUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column aswxmall_user_referrer.referrer_user_id
     *
     * @param referrerUserId the value for aswxmall_user_referrer.referrer_user_id
     *
     * @mbg.generated
     */
    public void setReferrerUserId(Integer referrerUserId) {
        this.referrerUserId = referrerUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_user_referrer
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
        sb.append(", referrerUserId=").append(referrerUserId);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_user_referrer
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
        AswxmallUserReferrer other = (AswxmallUserReferrer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getReferrerUserId() == null ? other.getReferrerUserId() == null : this.getReferrerUserId().equals(other.getReferrerUserId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_user_referrer
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getReferrerUserId() == null) ? 0 : getReferrerUserId().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table aswxmall_user_referrer
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        userId("user_id", "userId", "INTEGER", false),
        referrerUserId("referrer_user_id", "referrerUserId", "INTEGER", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table aswxmall_user_referrer
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table aswxmall_user_referrer
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table aswxmall_user_referrer
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table aswxmall_user_referrer
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table aswxmall_user_referrer
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table aswxmall_user_referrer
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_user_referrer
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_user_referrer
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_user_referrer
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_user_referrer
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_user_referrer
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
         * This method corresponds to the database table aswxmall_user_referrer
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_user_referrer
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table aswxmall_user_referrer
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
         * This method corresponds to the database table aswxmall_user_referrer
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
         * This method corresponds to the database table aswxmall_user_referrer
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}