package com.InstituteManagement.InstituteManagement.result.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultRequestDTO {
    private Long examId;
    private Long studentId;
    private BigDecimal marks;
    private String grade;
}
