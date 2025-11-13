package xyz.fcc.stock.record.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRecordWithCommentsDTO {
    private Long id;
    private LocalDate currentDay;
    private String company;
    private String content;
    private String info;
    private List<CompanyCommentDTO> comments;
}