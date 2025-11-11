package xyz.fcc.stock.record.service; // 请根据您的实际包名调整

import xyz.fcc.stock.record.dto.CompanyRecordDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CompanyRecordService {

    /**
     * 保存一条 CompanyRecord
     * @param companyRecordDTO DTO 对象
     * @return 影响的行数
     */
    int saveCompanyRecord(CompanyRecordDTO companyRecordDTO);

    /**
     * 根据条件分页查询 CompanyRecord
     * @param company 公司名称 (可选)
     * @param startDate 开始日期 (可选)
     * @param endDate 结束日期 (可选)
     * @param content 内容关键字 (可选)
     * @param pageNum 页码 (从1开始)
     * @param pageSize 每页大小
     * @return 查询到的 DTO 列表
     */
    List<CompanyRecordDTO> getCompanyRecordsByCondition(String company, LocalDate startDate, LocalDate endDate, String content, int pageNum, int pageSize);
}