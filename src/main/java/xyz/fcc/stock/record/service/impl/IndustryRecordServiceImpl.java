package xyz.fcc.stock.record.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.fcc.stock.record.dto.*;
import xyz.fcc.stock.record.entity.IndustryRecord;
import xyz.fcc.stock.record.mapper.IndustryRecordMapper;
import xyz.fcc.stock.record.mapper.IndustryCommentMapper;
import xyz.fcc.stock.record.service.IndustryRecordService;
import xyz.fcc.stock.record.service.AttentionService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndustryRecordServiceImpl implements IndustryRecordService {

    private final IndustryRecordMapper industryRecordMapper;
    private final IndustryCommentMapper industryCommentMapper;
    private final AttentionService attentionService;

    @Override
    public int saveIndustryRecord(IndustryRecordDTO industryRecordDTO) {
        IndustryRecord entity = new IndustryRecord();
        entity.setId(industryRecordDTO.getId());
        entity.setCurrentDay(industryRecordDTO.getCurrentDay());
        entity.setIndustry(industryRecordDTO.getIndustry());
        entity.setContent(industryRecordDTO.getContent());
        entity.setInfo("兼容处理");

        int result = industryRecordMapper.insertIndustryRecord(entity);

        // 更新attention
        int cnt = attentionService.incrementTimes(industryRecordDTO.getIndustry(), "industry");

        if (cnt == 0) {
            throw new RuntimeException("未注册");
        }

        return result;
    }

    @Override
    public PageResult<IndustryRecordDTO> getIndustryRecordsByCondition(
            String industry,
            LocalDate startDate,
            LocalDate endDate,
            String content,
            int pageNum,
            int pageSize
    ) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<IndustryRecord> entities = industryRecordMapper.selectIndustryRecordByConditionWithPaging(
                industry, startDate, endDate, content, offset, limit
        );

        int total = industryRecordMapper.countIndustryRecordsByCondition(
                industry, startDate, endDate, content
        );

        List<IndustryRecordDTO> dtos = entities.stream()
                .map(this::convertToDTOWithComments)
                .collect(Collectors.toList());

        return new PageResult<>(
                dtos,
                total,
                pageNum,
                pageSize,
                (total + pageSize - 1) / pageSize
        );
    }

    @Override
    public List<IndustryCommentDTO> getCommentsByRecordId(Long recordId) {
        return industryCommentMapper.selectIndustryCommentsByRecordId(recordId).stream()
                .map(this::convertCommentToDTO)
                .collect(Collectors.toList());
    }

    private IndustryRecordDTO convertToDTOWithComments(IndustryRecord entity) {
        IndustryRecordDTO dto = new IndustryRecordDTO();
        dto.setId(entity.getId());
        dto.setCurrentDay(entity.getCurrentDay());
        dto.setIndustry(entity.getIndustry());
        dto.setContent(entity.getContent());
        dto.setInfo(entity.getInfo());
        dto.setComments(getCommentsByRecordId(entity.getId()));
        return dto;
    }

    private IndustryCommentDTO convertCommentToDTO(xyz.fcc.stock.record.entity.IndustryComment entity) {
        IndustryCommentDTO dto = new IndustryCommentDTO();
        dto.setId(entity.getId());
        dto.setIndustryRecordId(entity.getIndustryRecordId());
        dto.setIndustry(entity.getIndustry());
        dto.setCurrentDay(entity.getCurrentDay());
        dto.setContent(entity.getContent());
        dto.setCreateTime(entity.getCreateTime());
        dto.setUpdateTime(entity.getUpdateTime());
        dto.setInfo(entity.getInfo());

        // 保存评论时也更新attention
        int cnt = attentionService.incrementTimes(entity.getIndustry(), "industry");
        if (cnt == 0) {
            throw new RuntimeException("未注册");
        }

        return dto;
    }
}