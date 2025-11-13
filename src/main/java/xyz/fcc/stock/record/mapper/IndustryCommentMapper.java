package xyz.fcc.stock.record.mapper;

import org.apache.ibatis.annotations.*;
import xyz.fcc.stock.record.entity.IndustryComment;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface IndustryCommentMapper {

    /**
     * 插入一条 t_industry_comment 记录
     * @param comment IndustryComment 实体对象
     * @return 影响的行数
     */
    int insertIndustryComment(IndustryComment comment);

    /**
     * 根据记录ID查询所有评论
     * @param recordId 行业记录ID
     * @return 评论列表
     */
    List<IndustryComment> selectIndustryCommentsByRecordId(@Param("recordId") Long recordId);

    /**
     * 根据行业和日期查询评论
     * @param industry 行业名称
     * @param currentDay 日期
     * @return 评论列表
     */
    List<IndustryComment> selectIndustryCommentsByIndustryAndDay(
            @Param("industry") String industry,
            @Param("currentDay") LocalDate currentDay
    );
}