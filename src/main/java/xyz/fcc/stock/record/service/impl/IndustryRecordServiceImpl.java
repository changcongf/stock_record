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
        entity.setType(industryRecordDTO.getType());
        entity.setInfo("兼容处理");

        int result = industryRecordMapper.insertIndustryRecord(entity);

        // 更新attention
        attentionService.incrementTimes(industryRecordDTO.getIndustry(), "industry");

        return result;
    }

    @Override
    public PageResult<IndustryRecordDTO> getIndustryRecordsByCondition(
            String industry,
            String type,
            LocalDate startDate,
            LocalDate endDate,
            String content,
            int pageNum,
            int pageSize
    ) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<IndustryRecord> entities = industryRecordMapper.selectIndustryRecordByConditionWithPaging(
                industry, type, startDate, endDate, content, offset, limit
        );

        long total = industryRecordMapper.countIndustryRecordsByCondition(
                industry, type, startDate, endDate, content
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
        dto.setType(entity.getType());
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
        attentionService.incrementTimes(entity.getIndustry(), "industry");

        return dto;
    }
}