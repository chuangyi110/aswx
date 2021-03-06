package com.lzdn.aswxmall.db.dao;

import com.lzdn.aswxmall.db.domain.AswxmallCategory;
import com.lzdn.aswxmall.db.domain.AswxmallCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AswxmallCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    long countByExample(AswxmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    int deleteByExample(AswxmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    int insert(AswxmallCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    int insertSelective(AswxmallCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    AswxmallCategory selectOneByExample(AswxmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    AswxmallCategory selectOneByExampleSelective(@Param("example") AswxmallCategoryExample example, @Param("selective") AswxmallCategory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    List<AswxmallCategory> selectByExampleSelective(@Param("example") AswxmallCategoryExample example, @Param("selective") AswxmallCategory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    List<AswxmallCategory> selectByExample(AswxmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    AswxmallCategory selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") AswxmallCategory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    AswxmallCategory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    AswxmallCategory selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AswxmallCategory record, @Param("example") AswxmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AswxmallCategory record, @Param("example") AswxmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AswxmallCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AswxmallCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") AswxmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table aswxmall_category
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}