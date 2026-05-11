package com.InstituteManagement.InstituteManagement.exam.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRequestDTO {
    private Long courseId;
    private String name;
    private LocalDate date;
}
