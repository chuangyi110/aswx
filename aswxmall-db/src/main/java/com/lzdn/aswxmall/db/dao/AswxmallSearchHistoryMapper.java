package com.lzdn.aswxmall.db.dao;

import com.lzdn.aswxmall.db.domain.AswxmallSearchHistory;
import com.lzdn.aswxmall.db.domain.AswxmallSearchHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AswxmallSearchHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    long countByExample(AswxmallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    int deleteByExample(AswxmallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    int insert(AswxmallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    int insertSelective(AswxmallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    AswxmallSearchHistory selectOneByExample(AswxmallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    AswxmallSearchHistory selectOneByExampleSelective(@Param("example") AswxmallSearchHistoryExample example, @Param("selective") AswxmallSearchHistory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    List<AswxmallSearchHistory> selectByExampleSelective(@Param("example") AswxmallSearchHistoryExample example, @Param("selective") AswxmallSearchHistory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    List<AswxmallSearchHistory> selectByExample(AswxmallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    AswxmallSearchHistory selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") AswxmallSearchHistory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    AswxmallSearchHistory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    AswxmallSearchHistory selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AswxmallSearchHistory record, @Param("example") AswxmallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AswxmallSearchHistory record, @Param("example") AswxmallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AswxmallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AswxmallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") AswxmallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_search_history
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}