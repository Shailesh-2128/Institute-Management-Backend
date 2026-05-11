package com.InstituteManagement.InstituteManagement.student.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {
    private Long userId;
    private LocalDate dob;
    private String address;
}
