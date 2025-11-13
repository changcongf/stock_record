package xyz.fcc.stock.record.mapper;

import org.apache.ibatis.annotations.*;
import xyz.fcc.stock.record.entity.DailyRecord;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface DailyRecordMapper {

    /**
     * 插入一条 t_daily_record 记录
     * @param record DailyRecord 实体对象
     * @return 影响的行数
     */
    int insertDailyRecord(@Param("record")DailyRecord record);

    /**
     * 根据 currentDay 范围和 content 关键字动态分页查询 t_daily_record 记录
     * @return 查询到的 DailyRecord 列表
     */
    List<DailyRecord> selectDailyRecordByConditionWithPaging(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("content") String content,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    /**
     * 根据条件统计 t_daily_record 记录总数
     * @return 记录总数
     */
    long countDailyRecordsByCondition(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("content") String content
    );
}