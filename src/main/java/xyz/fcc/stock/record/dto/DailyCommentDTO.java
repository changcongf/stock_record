package xyz.fcc.stock.record.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyCommentDTO {
    private Long id;
    private Long dailyRecordId;
    private LocalDate currentDay;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String info;
}
