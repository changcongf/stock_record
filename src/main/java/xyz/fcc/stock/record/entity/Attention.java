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
public class Attention {
    private Long id;
    private String name;
    private String type; // "company", "industry", "product"
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer times;
}