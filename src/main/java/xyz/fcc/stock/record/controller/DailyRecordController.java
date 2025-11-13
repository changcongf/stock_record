package xyz.fcc.stock.record.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fcc.stock.record.dto.*;
import xyz.fcc.stock.record.service.DailyRecordService;
import xyz.fcc.stock.record.service.CommentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/daily-records")
@RequiredArgsConstructor
public class DailyRecordController {

    private final DailyRecordService dailyRecordService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Integer> createDailyRecord(@RequestBody DailyRecordDTO dailyRecordDTO) {
        return ResponseEntity.ok(dailyRecordService.saveDailyRecord(dailyRecordDTO));
    }

    @GetMapping
    public ResponseEntity<PageResult<DailyRecordWithCommentsDTO>> getDailyRecords(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String content,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.ok(dailyRecordService.getDailyRecordsByCondition(
                startDate, endDate, content, pageNum, pageSize
        ));
    }

    @PostMapping("/{recordId}/comments")
    public ResponseEntity<Integer> addComment(
            @PathVariable Long recordId,
            @RequestBody DailyCommentDTO commentDTO
    ) {
        commentDTO.setDailyRecordId(recordId);
        return ResponseEntity.ok(commentService.saveDailyComment(commentDTO));
    }
}
