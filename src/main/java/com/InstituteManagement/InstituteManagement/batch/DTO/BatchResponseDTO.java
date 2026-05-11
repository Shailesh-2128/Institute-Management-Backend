package com.InstituteManagement.InstituteManagement.batch.DTO;

import com.InstituteManagement.InstituteManagement.course.DTO.CourseResponseDTO;
import com.InstituteManagement.InstituteManagement.staff.DTO.StaffResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchResponseDTO {
    private Long id;
    private CourseResponseDTO course;
    private StaffResponseDTO staff;
    private String timing;
}
