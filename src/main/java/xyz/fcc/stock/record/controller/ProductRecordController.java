package xyz.fcc.stock.record.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fcc.stock.record.dto.*;
import xyz.fcc.stock.record.service.ProductRecordService;
import xyz.fcc.stock.record.service.CommentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/product-records")
@RequiredArgsConstructor
public class ProductRecordController {

    private final ProductRecordService productRecordService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Integer> createProductRecord(@RequestBody ProductRecordDTO productRecordDTO) {
        if (productRecordDTO.getInfo() == null || productRecordDTO.getInfo().isEmpty()) {
            productRecordDTO.setInfo("兼容处理");
        }
        return ResponseEntity.ok(productRecordService.saveProductRecord(productRecordDTO));
    }

    @GetMapping
    public ResponseEntity<PageResult<ProductRecordDTO>> getProductRecords(
            @RequestParam(required = false) String product,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String content,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.ok(productRecordService.getProductRecordsByCondition(
                product, startDate, endDate, content, pageNum, pageSize
        ));
    }

    @PostMapping("/{recordId}/comments")
    public ResponseEntity<Integer> addComment(
            @PathVariable Long recordId,
            @RequestBody ProductCommentDTO commentDTO
    ) {
        commentDTO.setProductRecordId(recordId);
        return ResponseEntity.ok(commentService.saveProductComment(commentDTO));
    }
}