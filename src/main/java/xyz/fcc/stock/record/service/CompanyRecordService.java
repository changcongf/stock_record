package xyz.fcc.stock.record.service;

import xyz.fcc.stock.record.dto.*;
import java.time.LocalDate;
import java.util.List;

public interface CompanyRecordService {
    int saveCompanyRecord(CompanyRecordDTO companyRecordDTO);

    PageResult<CompanyRecordWithCommentsDTO> getCompanyRecordsByCondition(
            String company,
            LocalDate startDate,
            LocalDate endDate,
            String content,
            int pageNum,
            int pageSize
    );

    List<CompanyCommentDTO> getCommentsByRecordId(Long recordId);
}