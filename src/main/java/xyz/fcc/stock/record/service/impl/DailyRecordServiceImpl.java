package xyz.fcc.stock.record.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.fcc.stock.record.dto.DailyRecordDTO;
import xyz.fcc.stock.record.entity.DailyRecord;
import xyz.fcc.stock.record.mapper.DailyRecordMapper;
import xyz.fcc.stock.record.service.DailyRecordService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyRecordServiceImpl implements DailyRecordService {

    private final DailyRecordMapper dailyRecordMapper;

    @Override
    public int saveDailyRecord(DailyRecordDTO dailyRecordDTO) {
        DailyRecord entity = new DailyRecord();
        entity.setId(dailyRecordDTO.getId());
        entity.setCurrentDay(dailyRecordDTO.getCurrentDay());
        entity.setContent(dailyRecordDTO.getContent());
        entity.setInfo(dailyRecordDTO.getInfo());
        // create_time 和 update_time 由数据库自动生成
        return dailyRecordMapper.insertDailyRecord(entity);
    }

    @Override
    public List<DailyRecordDTO> getDailyRecordsByCondition(LocalDate startDate, LocalDate endDate, String content, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<DailyRecord> entities = dailyRecordMapper.selectDailyRecordByConditionWithPaging(startDate, endDate, content, offset, limit);
        return entities.stream()
                .map(this::convertToDTO) // 将 Entity 转换为 DTO
                .collect(Collectors.toList());
    }

    // 辅助方法：将 Entity 转换为 DTO
    private DailyRecordDTO convertToDTO(DailyRecord entity) {
        DailyRecordDTO dto = new DailyRecordDTO();
        dto.setId(entity.getId());
        dto.setCurrentDay(entity.getCurrentDay());
        dto.setContent(entity.getContent());
        dto.setInfo(entity.getInfo());
        return dto;
    }
}