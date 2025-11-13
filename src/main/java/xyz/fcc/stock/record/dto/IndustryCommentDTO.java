package xyz.fcc.stock.record.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndustryCommentDTO {
    private Long id;
    private Long industryRecordId;
    private String industry;
    private LocalDate currentDay;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String info;
}