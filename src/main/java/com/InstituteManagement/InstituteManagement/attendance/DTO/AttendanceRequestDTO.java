package com.InstituteManagement.InstituteManagement.attendance.DTO;

import com.InstituteManagement.InstituteManagement.common.enums.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRequestDTO {
    private Long studentId;
    private LocalDate date;
    private AttendanceStatus status;
}
