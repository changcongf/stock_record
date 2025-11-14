package xyz.fcc.stock.record.mapper;

import org.apache.ibatis.annotations.*;
import xyz.fcc.stock.record.entity.IndustryRecord;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface IndustryRecordMapper {
    int insertIndustryRecord(@Param("record") IndustryRecord record);

    List<IndustryRecord> selectIndustryRecordByConditionWithPaging(
            @Param("industry") String industry,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("content") String content,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    int countIndustryRecordsByCondition(
            @Param("industry") String industry,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("content") String content
    );
}