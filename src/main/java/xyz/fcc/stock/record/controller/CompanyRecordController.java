package xyz.fcc.stock.record.controller; // 请根据您的实际包名调整

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fcc.stock.record.dto.CompanyRecordDTO;
import xyz.fcc.stock.record.service.CompanyRecordService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/company-records") // 根据实际 API 路径调整
@RequiredArgsConstructor
public class CompanyRecordController {

    private final CompanyRecordService companyRecordService;

    @PostMapping // 用于创建新记录
    public ResponseEntity<Integer> createCompanyRecord(@RequestBody CompanyRecordDTO companyRecordDTO) {
        int result = companyRecordService.saveCompanyRecord(companyRecordDTO);
        return ResponseEntity.ok(result); // 返回影响的行数
    }

    @GetMapping // 用于查询记录
    public ResponseEntity<List<CompanyRecordDTO>> getCompanyRecords(
            @RequestParam(required = false) String company,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String content,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {

        List<CompanyRecordDTO> records = companyRecordService.getCompanyRecordsByCondition(company, startDate, endDate, content, pageNum, pageSize);
        return ResponseEntity.ok(records);
    }
}