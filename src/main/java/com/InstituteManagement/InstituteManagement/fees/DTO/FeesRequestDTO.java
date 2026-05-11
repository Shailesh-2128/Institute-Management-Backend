package com.InstituteManagement.InstituteManagement.fees.DTO;

import com.InstituteManagement.InstituteManagement.common.enums.FeeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeesRequestDTO {
    private Long studentId;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal dueAmount;
    private FeeStatus status;
}
