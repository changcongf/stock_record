package xyz.fcc.stock.record.mapper;

import org.apache.ibatis.annotations.*;
import xyz.fcc.stock.record.entity.CompanyComment;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CompanyCommentMapper {

    /**
     * 插入一条 t_company_comment 记录
     * @param comment CompanyComment 实体对象
     * @return 影响的行数
     */
    int insertCompanyComment(CompanyComment comment);

    /**
     * 根据记录ID查询所有评论
     * @param recordId 公司记录ID
     * @return 评论列表
     */
    List<CompanyComment> selectCompanyCommentsByRecordId(@Param("recordId") Long recordId);

    /**
     * 根据公司和日期查询评论
     * @param company 公司名称
     * @param currentDay 日期
     * @return 评论列表
     */
    List<CompanyComment> selectCompanyCommentsByCompanyAndDay(
            @Param("company") String company,
            @Param("currentDay") LocalDate currentDay
    );
}