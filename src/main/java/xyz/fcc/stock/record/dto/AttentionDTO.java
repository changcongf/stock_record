package xyz.fcc.stock.record.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttentionDTO {
    private Long id;
    private String name;
    private String type; // "company", "industry", "product"
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer times;
}