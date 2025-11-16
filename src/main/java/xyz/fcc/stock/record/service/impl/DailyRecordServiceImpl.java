package xyz.fcc.stock.record.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.fcc.stock.record.dto.*;
import xyz.fcc.stock.record.entity.DailyRecord;
import xyz.fcc.stock.record.mapper.DailyRecordMapper;
import xyz.fcc.stock.record.mapper.DailyCommentMapper;
import xyz.fcc.stock.record.service.DailyRecordService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyRecordServiceImpl implements DailyRecordService {

    private final DailyRecordMapper dailyRecordMapper;
    private final DailyCommentMapper dailyCommentMapper;

    @Override
    public int saveDailyRecord(DailyRecordDTO dailyRecordDTO) {
        DailyRecord entity = new DailyRecord();
        entity.setId(dailyRecordDTO.getId());
        entity.setCurrentDay(dailyRecordDTO.getCurrentDay());
        entity.setContent(dailyRecordDTO.getContent());
        entity.setInfo(null); // info字段兼容处理
        return dailyRecordMapper.insertDailyRecord(entity);
    }

    @Override
    public PageResult<DailyRecordWithCommentsDTO> getDailyRecordsByCondition(
            LocalDate startDate,
            LocalDate endDate,
            String content,
            int pageNum,
            int pageSize
    ) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<DailyRecord> entities = dailyRecordMapper.selectDailyRecordByConditionWithPaging(
                startDate, endDate, content, offset, limit
        );

        long total = dailyRecordMapper.countDailyRecordsByCondition(startDate, endDate, content);

        List<DailyRecordWithCommentsDTO> dtos = entities.stream()
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
    public List<DailyCommentDTO> getCommentsByRecordId(Long recordId) {
        return dailyCommentMapper.selectDailyCommentsByRecordId(recordId).stream()
                .map(this::convertCommentToDTO)
                .collect(Collectors.toList());
    }

    private DailyRecordWithCommentsDTO convertToDTOWithComments(DailyRecord entity) {
        DailyRecordWithCommentsDTO dto = new DailyRecordWithCommentsDTO();
        dto.setId(entity.getId());
        dto.setCurrentDay(entity.getCurrentDay());
        dto.setContent(entity.getContent());
        dto.setInfo(entity.getInfo());
        dto.setComments(getCommentsByRecordId(entity.getId()));
        return dto;
    }

    private DailyCommentDTO convertCommentToDTO(xyz.fcc.stock.record.entity.DailyComment entity) {
        DailyCommentDTO dto = new DailyCommentDTO();
        dto.setId(entity.getId());
        dto.setDailyRecordId(entity.getDailyRecordId());
        dto.setCurrentDay(entity.getCurrentDay());
        dto.setContent(entity.getContent());
        dto.setCreateTime(entity.getCreateTime());
        dto.setUpdateTime(entity.getUpdateTime());
        dto.setInfo(entity.getInfo());
        return dto;
    }
}