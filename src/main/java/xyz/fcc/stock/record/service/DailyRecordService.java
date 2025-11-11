package xyz.fcc.stock.record.service; // 请根据您的实际包名调整

import xyz.fcc.stock.record.dto.DailyRecordDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DailyRecordService {

    /**
     * 保存一条 DailyRecord
     * @param dailyRecordDTO DTO 对象
     * @return 影响的行数
     */
    int saveDailyRecord(DailyRecordDTO dailyRecordDTO);

    /**
     * 根据条件分页查询 DailyRecord
     * @param startDate 开始日期 (可选)
     * @param endDate 结束日期 (可选)
     * @param content 内容关键字 (可选)
     * @param pageNum 页码 (从1开始)
     * @param pageSize 每页大小
     * @return 查询到的 DTO 列表
     */
    List<DailyRecordDTO> getDailyRecordsByCondition(LocalDate startDate, LocalDate endDate, String content, int pageNum, int pageSize);
}