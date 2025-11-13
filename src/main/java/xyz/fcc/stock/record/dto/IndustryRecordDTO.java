package xyz.fcc.stock.record.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndustryRecordDTO {
    private Long id;
    private LocalDate currentDay;
    private String industry;
    private String content;
    private String type;
    private String info;
    private List<IndustryCommentDTO> comments;
}