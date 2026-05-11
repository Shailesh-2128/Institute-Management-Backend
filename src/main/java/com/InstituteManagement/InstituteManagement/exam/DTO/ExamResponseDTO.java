package com.InstituteManagement.InstituteManagement.exam.DTO;

import com.InstituteManagement.InstituteManagement.course.DTO.CourseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponseDTO {
    private Long id;
    private CourseResponseDTO course;
    private String name;
    private LocalDate date;
    private LocalDateTime createdAt;
}
