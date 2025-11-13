package xyz.fcc.stock.record.service;

import xyz.fcc.stock.record.dto.*;
import java.time.LocalDate;
import java.util.List;

public interface CommentService {
    int saveDailyComment(DailyCommentDTO commentDTO);
    int saveCompanyComment(CompanyCommentDTO commentDTO);
    int saveIndustryComment(IndustryCommentDTO commentDTO);
    int saveProductComment(ProductCommentDTO commentDTO);
}