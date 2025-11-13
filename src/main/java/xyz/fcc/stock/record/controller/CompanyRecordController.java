package xyz.fcc.stock.record.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fcc.stock.record.dto.*;
import xyz.fcc.stock.record.service.CompanyRecordService;
import xyz.fcc.stock.record.service.CommentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/company-records")
@RequiredArgsConstructor
public class CompanyRecordController {

    private final CompanyRecordService companyRecordService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Integer> createCompanyRecord(@RequestBody CompanyRecordDTO companyRecordDTO) {
        return ResponseEntity.ok(companyRecordService.saveCompanyRecord(companyRecordDTO));
    }

    @GetMapping
    public ResponseEntity<PageResult<CompanyRecordWithCommentsDTO>> getCompanyRecords(
            @RequestParam(required = false) String company,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String content,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.ok(companyRecordService.getCompanyRecordsByCondition(
                company, startDate, endDate, content, pageNum, pageSize
        ));
    }

    @PostMapping("/{recordId}/comments")
    public ResponseEntity<Integer> addComment(
            @PathVariable Long recordId,
            @RequestBody CompanyCommentDTO commentDTO
    ) {
        commentDTO.setCompanyRecordId(recordId);
        return ResponseEntity.ok(commentService.saveCompanyComment(commentDTO));
    }
}