package com.InstituteManagement.InstituteManagement.course.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {
    private String name;
    private Integer duration;
    private BigDecimal fees;
}
