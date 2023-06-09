package com.xftxyz.rocketblog.mapper;

import com.xftxyz.rocketblog.pojo.Like;
import com.xftxyz.rocketblog.pojo.LikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LikeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_like
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    long countByExample(LikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_like
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int deleteByExample(LikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_like
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int deleteByPrimaryKey(Long likeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_like
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int insert(Like record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_like
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int insertSelective(Like record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_like
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    List<Like> selectByExample(LikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_like
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    Like selectByPrimaryKey(Long likeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_like
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int updateByExampleSelective(@Param("record") Like record, @Param("example") LikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_like
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int updateByExample(@Param("record") Like record, @Param("example") LikeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_like
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int updateByPrimaryKeySelective(Like record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_like
     *
     * @mbg.generated Mon Mar 27 19:37:36 CST 2023
     */
    int updateByPrimaryKey(Like record);
}