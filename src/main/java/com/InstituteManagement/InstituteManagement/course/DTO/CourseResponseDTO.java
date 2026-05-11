package com.InstituteManagement.InstituteManagement.course.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {
    private Long id;
    private String name;
    private Integer duration;
    private BigDecimal fees;
}
