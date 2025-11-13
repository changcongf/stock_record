package xyz.fcc.stock.record.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseComment {
    private Long id;
    private Long recordId;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String info;
}