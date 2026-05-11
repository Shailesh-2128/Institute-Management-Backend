package com.InstituteManagement.InstituteManagement.result.DTO;

import com.InstituteManagement.InstituteManagement.exam.DTO.ExamResponseDTO;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponseDTO {
    private Long id;
    private ExamResponseDTO exam;
    private StudentResponseDTO student;
    private BigDecimal marks;
    private String grade;
    private LocalDateTime createdAt;
}
