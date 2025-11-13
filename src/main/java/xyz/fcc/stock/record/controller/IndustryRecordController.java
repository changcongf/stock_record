package xyz.fcc.stock.record.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fcc.stock.record.dto.*;
import xyz.fcc.stock.record.service.IndustryRecordService;
import xyz.fcc.stock.record.service.CommentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/industry-records")
@RequiredArgsConstructor
public class IndustryRecordController {

    private final IndustryRecordService industryRecordService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Integer> createIndustryRecord(@RequestBody IndustryRecordDTO industryRecordDTO) {
        if (industryRecordDTO.getInfo() == null || industryRecordDTO.getInfo().isEmpty()) {
            industryRecordDTO.setInfo("兼容处理");
        }
        return ResponseEntity.ok(industryRecordService.saveIndustryRecord(industryRecordDTO));
    }

    @GetMapping
    public ResponseEntity<PageResult<IndustryRecordDTO>> getIndustryRecords(
            @RequestParam(required = false) String industry,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String content,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.ok(industryRecordService.getIndustryRecordsByCondition(
                industry, type, startDate, endDate, content, pageNum, pageSize
        ));
    }

    @PostMapping("/{recordId}/comments")
    public ResponseEntity<Integer> addComment(
            @PathVariable Long recordId,
            @RequestBody IndustryCommentDTO commentDTO
    ) {
        commentDTO.setIndustryRecordId(recordId);
        return ResponseEntity.ok(commentService.saveIndustryComment(commentDTO));
    }
}
