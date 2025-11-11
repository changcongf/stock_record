package xyz.fcc.stock.record.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fcc.stock.record.dto.DailyRecordDTO;
import xyz.fcc.stock.record.service.DailyRecordService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/daily-records")
@RequiredArgsConstructor
public class DailyRecordController {

    private final DailyRecordService dailyRecordService;

    @PostMapping // 用于创建新记录
    public ResponseEntity<Integer> createDailyRecord(@RequestBody DailyRecordDTO dailyRecordDTO) {
        int result = dailyRecordService.saveDailyRecord(dailyRecordDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<DailyRecordDTO>> getDailyRecords(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String content,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {

        List<DailyRecordDTO> records = dailyRecordService.getDailyRecordsByCondition(startDate, endDate, content, pageNum, pageSize);
        return ResponseEntity.ok(records);
    }
}