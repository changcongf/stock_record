package xyz.fcc.stock.record.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.fcc.stock.record.dto.*;
import xyz.fcc.stock.record.entity.*;
import xyz.fcc.stock.record.mapper.*;
import xyz.fcc.stock.record.service.CommentService;
import xyz.fcc.stock.record.service.AttentionService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final DailyCommentMapper dailyCommentMapper;
    private final CompanyCommentMapper companyCommentMapper;
    private final IndustryCommentMapper industryCommentMapper;
    private final ProductCommentMapper productCommentMapper;
    private final AttentionService attentionService;

    @Override
    public int saveDailyComment(DailyCommentDTO commentDTO) {
        DailyComment entity = new DailyComment();
        entity.setId(commentDTO.getId());
        entity.setDailyRecordId(commentDTO.getDailyRecordId());
        entity.setCurrentDay(commentDTO.getCurrentDay());
        entity.setContent(commentDTO.getContent());
        entity.setInfo(null);
        return dailyCommentMapper.insertDailyComment(entity);
    }

    @Override
    public int saveCompanyComment(CompanyCommentDTO commentDTO) {
        CompanyComment entity = new CompanyComment();
        entity.setId(commentDTO.getId());
        entity.setCompanyRecordId(commentDTO.getCompanyRecordId());
        entity.setCompany(commentDTO.getCompany());
        entity.setCurrentDay(commentDTO.getCurrentDay());
        entity.setContent(commentDTO.getContent());
        entity.setInfo(null);

        int result = companyCommentMapper.insertCompanyComment(entity);

        // 保存评论时更新attention
        int cnt = attentionService.incrementTimes(commentDTO.getCompany(), "company");

        if (cnt == 0) {
            throw new RuntimeException("未注册");
        }

        return result;
    }

    @Override
    public int saveIndustryComment(IndustryCommentDTO commentDTO) {
        IndustryComment entity = new IndustryComment();
        entity.setId(commentDTO.getId());
        entity.setIndustryRecordId(commentDTO.getIndustryRecordId());
        entity.setIndustry(commentDTO.getIndustry());
        entity.setCurrentDay(commentDTO.getCurrentDay());
        entity.setContent(commentDTO.getContent());
        entity.setInfo(null);

        int result = industryCommentMapper.insertIndustryComment(entity);

        // 保存评论时更新attention
        int cnt = attentionService.incrementTimes(commentDTO.getIndustry(), "industry");

        if (cnt == 0) {
            throw new RuntimeException("未注册");
        }

        return result;
    }

    @Override
    public int saveProductComment(ProductCommentDTO commentDTO) {
        ProductComment entity = new ProductComment();
        entity.setId(commentDTO.getId());
        entity.setProductRecordId(commentDTO.getProductRecordId());
        entity.setProduct(commentDTO.getProduct());
        entity.setCurrentDay(commentDTO.getCurrentDay());
        entity.setContent(commentDTO.getContent());
        entity.setInfo(null);

        int result = productCommentMapper.insertProductComment(entity);

        // 保存评论时更新attention
        int cnt = attentionService.incrementTimes(commentDTO.getProduct(), "product");

        if (cnt == 0) {
            throw new RuntimeException("未注册");
        }

        return result;
    }
}