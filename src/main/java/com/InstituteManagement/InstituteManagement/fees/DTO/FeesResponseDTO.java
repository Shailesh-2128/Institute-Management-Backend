package com.InstituteManagement.InstituteManagement.fees.DTO;

import com.InstituteManagement.InstituteManagement.common.enums.FeeStatus;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeesResponseDTO {
    private Long id;
    private StudentResponseDTO student;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal dueAmount;
    private FeeStatus status;
    private LocalDateTime createdAt;
}
