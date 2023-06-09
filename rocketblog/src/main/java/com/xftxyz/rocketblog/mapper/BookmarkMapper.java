package com.xftxyz.rocketblog.mapper;

import com.xftxyz.rocketblog.pojo.Bookmark;
import com.xftxyz.rocketblog.pojo.BookmarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookmarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bookmark
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    long countByExample(BookmarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bookmark
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int deleteByExample(BookmarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bookmark
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int deleteByPrimaryKey(Long bookmarkId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bookmark
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int insert(Bookmark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bookmark
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int insertSelective(Bookmark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bookmark
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    List<Bookmark> selectByExample(BookmarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bookmark
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    Bookmark selectByPrimaryKey(Long bookmarkId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bookmark
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int updateByExampleSelective(@Param("record") Bookmark record, @Param("example") BookmarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bookmark
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int updateByExample(@Param("record") Bookmark record, @Param("example") BookmarkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bookmark
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int updateByPrimaryKeySelective(Bookmark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bookmark
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int updateByPrimaryKey(Bookmark record);
}