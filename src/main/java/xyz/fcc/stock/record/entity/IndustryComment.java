package xyz.fcc.stock.record.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndustryComment {
    private Long id;
    private Long industryRecordId;
    private String industry;
    private LocalDate currentDay;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String info;
}