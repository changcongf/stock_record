package xyz.fcc.stock.record.service;

import xyz.fcc.stock.record.dto.*;
import java.time.LocalDate;
import java.util.List;

public interface ProductRecordService {
    int saveProductRecord(ProductRecordDTO productRecordDTO);

    PageResult<ProductRecordDTO> getProductRecordsByCondition(
            String product,
            LocalDate startDate,
            LocalDate endDate,
            String content,
            int pageNum,
            int pageSize
    );

    List<ProductCommentDTO> getCommentsByRecordId(Long recordId);
}
