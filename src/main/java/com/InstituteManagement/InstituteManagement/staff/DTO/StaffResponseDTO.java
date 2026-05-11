package com.InstituteManagement.InstituteManagement.staff.DTO;

import com.InstituteManagement.InstituteManagement.User.DTO.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private String designation;
    private BigDecimal salary;
    private String status;
    private LocalDateTime joiningDate;
}
