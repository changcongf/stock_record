package xyz.fcc.stock.record.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCommentDTO {
    private Long id;
    private Long productRecordId;
    private String product;
    private LocalDate currentDay;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String info;
}