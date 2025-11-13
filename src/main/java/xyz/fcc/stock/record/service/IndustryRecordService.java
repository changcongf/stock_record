package xyz.fcc.stock.record.service;

import xyz.fcc.stock.record.dto.*;
import java.time.LocalDate;
import java.util.List;

public interface IndustryRecordService {
    int saveIndustryRecord(IndustryRecordDTO industryRecordDTO);

    PageResult<IndustryRecordDTO> getIndustryRecordsByCondition(
            String industry,
            String type,
            LocalDate startDate,
            LocalDate endDate,
            String content,
            int pageNum,
            int pageSize
    );

    List<IndustryCommentDTO> getCommentsByRecordId(Long recordId);
}