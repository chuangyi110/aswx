package com.lzdn.aswxmall.db.dao;

import com.lzdn.aswxmall.db.domain.AswxmallRebates;
import com.lzdn.aswxmall.db.domain.AswxmallRebatesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AswxmallRebatesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    long countByExample(AswxmallRebatesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    int deleteByExample(AswxmallRebatesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    int insert(AswxmallRebates record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    int insertSelective(AswxmallRebates record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    AswxmallRebates selectOneByExample(AswxmallRebatesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    AswxmallRebates selectOneByExampleSelective(@Param("example") AswxmallRebatesExample example, @Param("selective") AswxmallRebates.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    List<AswxmallRebates> selectByExampleSelective(@Param("example") AswxmallRebatesExample example, @Param("selective") AswxmallRebates.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    List<AswxmallRebates> selectByExample(AswxmallRebatesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    AswxmallRebates selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") AswxmallRebates.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    AswxmallRebates selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AswxmallRebates record, @Param("example") AswxmallRebatesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AswxmallRebates record, @Param("example") AswxmallRebatesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AswxmallRebates record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_rebates
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AswxmallRebates record);
}