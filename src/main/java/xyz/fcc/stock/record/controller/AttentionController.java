package xyz.fcc.stock.record.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fcc.stock.record.dto.AttentionDTO;
import xyz.fcc.stock.record.dto.PageResult;
import xyz.fcc.stock.record.service.AttentionService;

@RestController
@RequestMapping("/api/attentions")
@RequiredArgsConstructor
public class AttentionController {

    private final AttentionService attentionService;

    @PostMapping
    public ResponseEntity<Integer> createAttention(@RequestBody AttentionDTO attentionDTO) {
        return ResponseEntity.ok(attentionService.saveAttention(attentionDTO));
    }

    @GetMapping
    public ResponseEntity<PageResult<AttentionDTO>> getAttentions(
            @RequestParam String type,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "30") int pageSize) {
        return ResponseEntity.ok(attentionService.getAttentionByType(type, pageNum, pageSize));
    }
}