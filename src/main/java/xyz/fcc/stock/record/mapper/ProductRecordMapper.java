package xyz.fcc.stock.record.mapper;

import org.apache.ibatis.annotations.*;
import xyz.fcc.stock.record.entity.ProductRecord;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ProductRecordMapper {
    int insertProductRecord(ProductRecord record);

    List<ProductRecord> selectProductRecordByConditionWithPaging(
            @Param("product") String product,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("content") String content,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    int countProductRecordsByCondition(
            @Param("product") String product,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("content") String content
    );
}