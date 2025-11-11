package xyz.fcc.stock.record.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.fcc.stock.record.dto.CompanyRecordDTO;
import xyz.fcc.stock.record.entity.CompanyRecord;
import xyz.fcc.stock.record.mapper.CompanyRecordMapper;
import xyz.fcc.stock.record.service.CompanyRecordService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyRecordServiceImpl implements CompanyRecordService {

    private final CompanyRecordMapper companyRecordMapper;

    @Override
    public int saveCompanyRecord(CompanyRecordDTO companyRecordDTO) {
        CompanyRecord entity = new CompanyRecord();
        entity.setId(companyRecordDTO.getId());
        entity.setCurrentDay(companyRecordDTO.getCurrentDay());
        entity.setCompany(companyRecordDTO.getCompany());
        entity.setContent(companyRecordDTO.getContent());
        entity.setInfo(companyRecordDTO.getInfo());
        // create_time 和 update_time 由数据库自动生成
        return companyRecordMapper.insertCompanyRecord(entity);
    }

    @Override
    public List<CompanyRecordDTO> getCompanyRecordsByCondition(String company, LocalDate startDate, LocalDate endDate, String content, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;


        List<CompanyRecord> entities = companyRecordMapper.selectCompanyRecordByConditionWithPaging(company, startDate, endDate, content, offset, limit);
        return entities.stream()
                .map(this::convertToDTO) // 将 Entity 转换为 DTO
                .collect(Collectors.toList());
    }

    // 辅助方法：将 Entity 转换为 DTO
    private CompanyRecordDTO convertToDTO(CompanyRecord entity) {
        CompanyRecordDTO dto = new CompanyRecordDTO();
        dto.setId(entity.getId());
        dto.setCurrentDay(entity.getCurrentDay());
        dto.setCompany(entity.getCompany());
        dto.setContent(entity.getContent());
        dto.setInfo(entity.getInfo());
        return dto;
    }
}