package xyz.fcc.stock.record.mapper;

import org.apache.ibatis.annotations.*;
import xyz.fcc.stock.record.entity.CompanyRecord;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CompanyRecordMapper {

    /**
     * 插入一条 t_company_record 记录
     * @param record CompanyRecord 实体对象
     * @return 影响的行数
     */
    int insertCompanyRecord(CompanyRecord record);

    /**
     * 根据 company, currentDay 范围和 content 关键字动态分页查询 t_company_record 记录
     * @return 查询到的 CompanyRecord 列表
     */
    List<CompanyRecord> selectCompanyRecordByConditionWithPaging(
            @Param("company") String company,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("content") String content,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    /**
     * 根据条件统计 t_company_record 记录总数
     * @return 记录总数
     */
    long countCompanyRecordsByCondition(
            @Param("company") String company,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("content") String content
    );
}