package com.InstituteManagement.InstituteManagement.staff.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffRequestDTO {
    private Long userId;
    private String designation;
    private BigDecimal salary;
}
