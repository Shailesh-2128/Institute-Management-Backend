package com.InstituteManagement.InstituteManagement.student.DTO;

import com.InstituteManagement.InstituteManagement.User.DTO.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private LocalDate dob;
    private String address;
    private LocalDate admissionDate;
    private String status;
}
