package com.InstituteManagement.InstituteManagement.attendance.DTO;

import com.InstituteManagement.InstituteManagement.common.enums.AttendanceStatus;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceResponseDTO {
    private Long id;
    private StudentResponseDTO student;
    private LocalDate date;
    private AttendanceStatus status;
    private LocalDateTime createdAt;
}
