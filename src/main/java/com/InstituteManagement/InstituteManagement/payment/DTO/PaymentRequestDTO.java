package com.InstituteManagement.InstituteManagement.payment.DTO;

import com.InstituteManagement.InstituteManagement.common.enums.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private Long studentId;
    private BigDecimal amount;
    private PaymentMode mode;
}
