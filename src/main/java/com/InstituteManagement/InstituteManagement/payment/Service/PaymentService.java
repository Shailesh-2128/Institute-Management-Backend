package com.InstituteManagement.InstituteManagement.payment.Service;

import com.InstituteManagement.InstituteManagement.common.enums.PaymentMode;
import com.InstituteManagement.InstituteManagement.payment.DTO.PaymentRequestDTO;
import com.InstituteManagement.InstituteManagement.payment.DTO.PaymentResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    PaymentResponseDTO createPayment(PaymentRequestDTO requestDTO);
    List<PaymentResponseDTO> getAllPayments();
    PaymentResponseDTO getPaymentById(Long id);
    List<PaymentResponseDTO> getPaymentsByStudentId(Long studentId);
    List<PaymentResponseDTO> getPaymentsByDate(LocalDate date);
    List<PaymentResponseDTO> getPaymentsByMode(PaymentMode mode);
}
