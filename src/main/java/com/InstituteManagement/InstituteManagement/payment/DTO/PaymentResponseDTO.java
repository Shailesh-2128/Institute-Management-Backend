package com.InstituteManagement.InstituteManagement.payment.DTO;

import com.InstituteManagement.InstituteManagement.common.enums.PaymentMode;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private Long id;
    private StudentResponseDTO student;
    private BigDecimal amount;
    private PaymentMode mode;
    private LocalDateTime date;
}
