package xyz.fcc.stock.record.mapper;

import org.apache.ibatis.annotations.*;
import xyz.fcc.stock.record.entity.Attention;

import java.util.List;

@Mapper
public interface AttentionMapper {

    /**
     * 插入或更新 t_attention 记录
     * @param attention Attention 实体对象
     * @return 影响的行数
     */
    int insertOrUpdateAttention(Attention attention);

    /**
     * 根据name查询attention记录
     * @param name 名称
     * @return Attention对象
     */
    Attention selectAttentionByName(@Param("name") String name);

    /**
     * 根据type分页查询attention记录
     * @param type 类型
     * @param offset 偏移量
     * @param limit 限制数
     * @return Attention列表
     */
    List<Attention> selectAttentionByTypeWithPaging(
        @Param("type") String type,
        @Param("offset") int offset,
        @Param("limit") int limit
    );

    /**
     * 统计指定type的记录总数
     * @param type 类型
     * @return 记录总数
     */
    long countAttentionByType(@Param("type") String type);

    /**
     * 递增times字段
     * @param name 名称
     * @return 影响的行数
     */
    int incrementTimesByName(@Param("name") String name);
}