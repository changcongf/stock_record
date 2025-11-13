package xyz.fcc.stock.record.mapper;

import org.apache.ibatis.annotations.*;
import xyz.fcc.stock.record.entity.DailyComment;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface DailyCommentMapper {

    /**
     * 插入一条 t_daily_comment 记录
     * @param comment DailyComment 实体对象
     * @return 影响的行数
     */
    int insertDailyComment(DailyComment comment);

    /**
     * 根据记录ID查询所有评论
     * @param recordId 每日记录ID
     * @return 评论列表
     */
    List<DailyComment> selectDailyCommentsByRecordId(@Param("recordId") Long recordId);

    /**
     * 根据日期查询评论
     * @param currentDay 日期
     * @return 评论列表
     */
    List<DailyComment> selectDailyCommentsByDay(@Param("currentDay") LocalDate currentDay);
}