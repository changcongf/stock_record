package xyz.fcc.stock.record.mapper;

import org.apache.ibatis.annotations.*;
import xyz.fcc.stock.record.entity.ProductComment;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ProductCommentMapper {

    /**
     * 插入一条 t_product_comment 记录
     * @param comment ProductComment 实体对象
     * @return 影响的行数
     */
    int insertProductComment(ProductComment comment);

    /**
     * 根据记录ID查询所有评论
     * @param recordId 产业记录ID
     * @return 评论列表
     */
    List<ProductComment> selectProductCommentsByRecordId(@Param("recordId") Long recordId);

    /**
     * 根据产业和日期查询评论
     * @param product 产业名称
     * @param currentDay 日期
     * @return 评论列表
     */
    List<ProductComment> selectProductCommentsByProductAndDay(
            @Param("product") String product,
            @Param("currentDay") LocalDate currentDay
    );
}