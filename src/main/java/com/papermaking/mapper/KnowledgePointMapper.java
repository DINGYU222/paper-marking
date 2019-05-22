package com.papermaking.mapper;

import com.papermaking.pojo.KnowledgePoint;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface KnowledgePointMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table knowledge_point
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer kId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table knowledge_point
     *
     * @mbggenerated
     */
    int insert(KnowledgePoint record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table knowledge_point
     *
     * @mbggenerated
     */
    KnowledgePoint selectByPrimaryKey(Integer kId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table knowledge_point
     *
     * @mbggenerated
     */
    List<KnowledgePoint> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table knowledge_point
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(KnowledgePoint record);
}