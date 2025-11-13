package xyz.fcc.stock.record.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.fcc.stock.record.dto.*;
import xyz.fcc.stock.record.entity.CompanyRecord;
import xyz.fcc.stock.record.mapper.CompanyRecordMapper;
import xyz.fcc.stock.record.mapper.CompanyCommentMapper;
import xyz.fcc.stock.record.service.CompanyRecordService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyRecordServiceImpl implements CompanyRecordService {

    private final CompanyRecordMapper companyRecordMapper;
    private final CompanyCommentMapper companyCommentMapper;

    @Override
    public int saveCompanyRecord(CompanyRecordDTO companyRecordDTO) {
        CompanyRecord entity = new CompanyRecord();
        entity.setId(companyRecordDTO.getId());
        entity.setCurrentDay(companyRecordDTO.getCurrentDay());
        entity.setCompany(companyRecordDTO.getCompany());
        entity.setContent(companyRecordDTO.getContent());
        entity.setInfo("兼容处理"); // info字段兼容处理
        return companyRecordMapper.insertCompanyRecord(entity);
    }

    @Override
    public PageResult<CompanyRecordWithCommentsDTO> getCompanyRecordsByCondition(
            String company,
            LocalDate startDate,
            LocalDate endDate,
            String content,
            int pageNum,
            int pageSize
    ) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;

        List<CompanyRecord> entities = companyRecordMapper.selectCompanyRecordByConditionWithPaging(
                company, startDate, endDate, content, offset, limit
        );

        long total = companyRecordMapper.countCompanyRecordsByCondition(company, startDate, endDate, content);

        List<CompanyRecordWithCommentsDTO> dtos = entities.stream()
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
    public List<CompanyCommentDTO> getCommentsByRecordId(Long recordId) {
        return companyCommentMapper.selectCompanyCommentsByRecordId(recordId).stream()
                .map(this::convertCommentToDTO)
                .collect(Collectors.toList());
    }

    private CompanyRecordWithCommentsDTO convertToDTOWithComments(CompanyRecord entity) {
        CompanyRecordWithCommentsDTO dto = new CompanyRecordWithCommentsDTO();
        dto.setId(entity.getId());
        dto.setCurrentDay(entity.getCurrentDay());
        dto.setCompany(entity.getCompany());
        dto.setContent(entity.getContent());
        dto.setInfo(entity.getInfo());
        dto.setComments(getCommentsByRecordId(entity.getId()));
        return dto;
    }

    private CompanyCommentDTO convertCommentToDTO(xyz.fcc.stock.record.entity.CompanyComment entity) {
        CompanyCommentDTO dto = new CompanyCommentDTO();
        dto.setId(entity.getId());
        dto.setCompanyRecordId(entity.getCompanyRecordId());
        dto.setCompany(entity.getCompany());
        dto.setCurrentDay(entity.getCurrentDay());
        dto.setContent(entity.getContent());
        dto.setCreateTime(entity.getCreateTime());
        dto.setUpdateTime(entity.getUpdateTime());
        dto.setInfo(entity.getInfo());
        return dto;
    }
}
