package com.InstituteManagement.InstituteManagement.payment.Service.Impl;

import com.InstituteManagement.InstituteManagement.common.enums.PaymentMode;
import com.InstituteManagement.InstituteManagement.common.exception.ResourceNotFoundException;
import com.InstituteManagement.InstituteManagement.payment.DTO.PaymentRequestDTO;
import com.InstituteManagement.InstituteManagement.payment.DTO.PaymentResponseDTO;
import com.InstituteManagement.InstituteManagement.payment.Entity.Payment;
import com.InstituteManagement.InstituteManagement.payment.Repository.PaymentRepository;
import com.InstituteManagement.InstituteManagement.payment.Service.PaymentService;
import com.InstituteManagement.InstituteManagement.student.Entity.Student;
import com.InstituteManagement.InstituteManagement.student.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO requestDTO) {
        Student student = studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Payment payment = Payment.builder()
                .student(student)
                .amount(requestDTO.getAmount())
                .mode(requestDTO.getMode())
                .build();
        return modelMapper.map(paymentRepository.save(payment), PaymentResponseDTO.class);
    }

    @Override
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(p -> modelMapper.map(p, PaymentResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long id) {
        Payment p = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        return modelMapper.map(p, PaymentResponseDTO.class);
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByStudentId(Long studentId) {
        return paymentRepository.findByStudentId(studentId).stream()
                .map(p -> modelMapper.map(p, PaymentResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByDate(LocalDate date) {
        return paymentRepository.findByDate(date).stream()
                .map(p -> modelMapper.map(p, PaymentResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByMode(PaymentMode mode) {
        return paymentRepository.findByMode(mode).stream()
                .map(p -> modelMapper.map(p, PaymentResponseDTO.class))
                .collect(Collectors.toList());
    }
}
