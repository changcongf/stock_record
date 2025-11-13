package xyz.fcc.stock.record.service;

import xyz.fcc.stock.record.dto.AttentionDTO;
import xyz.fcc.stock.record.dto.PageResult;

import java.util.List;

public interface AttentionService {

    /**
     * 保存或更新关注记录
     * @param attentionDTO DTO对象
     * @return 影响的行数
     */
    int saveAttention(AttentionDTO attentionDTO);

    /**
     * 根据类型分页查询关注记录
     * @param type 类型 (company, industry, product)
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    PageResult<AttentionDTO> getAttentionByType(String type, int pageNum, int pageSize);

    /**
     * 递增指定名称的times字段
     * @param name 名称
     * @param type 类型
     */
    void incrementTimes(String name, String type);
}