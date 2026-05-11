package com.InstituteManagement.InstituteManagement.payment.Controller;

import com.InstituteManagement.InstituteManagement.common.enums.PaymentMode;
import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import com.InstituteManagement.InstituteManagement.payment.DTO.PaymentRequestDTO;
import com.InstituteManagement.InstituteManagement.payment.DTO.PaymentResponseDTO;
import com.InstituteManagement.InstituteManagement.payment.Service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name="Payment Api's", description = "Manage payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Create payment")
    @PostMapping
    public ApiResponse<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO requestDTO) {
        return ApiResponse.<PaymentResponseDTO>builder().success(true).data(paymentService.createPayment(requestDTO)).message("Created").build();
    }

    @Operation(summary = "Get all payments")
    @GetMapping
    public ApiResponse<List<PaymentResponseDTO>> getAllPayments() {
        return ApiResponse.<List<PaymentResponseDTO>>builder().success(true).data(paymentService.getAllPayments()).message("Fetched").build();
    }

    @Operation(summary = "Get payment by ID")
    @GetMapping("/{id}")
    public ApiResponse<PaymentResponseDTO> getPaymentById(@PathVariable Long id) {
        return ApiResponse.<PaymentResponseDTO>builder().success(true).data(paymentService.getPaymentById(id)).message("Fetched").build();
    }

    @Operation(summary = "Get payments by student ID")
    @GetMapping("/student/{studentId}")
    public ApiResponse<List<PaymentResponseDTO>> getPaymentsByStudentId(@PathVariable Long studentId) {
        return ApiResponse.<List<PaymentResponseDTO>>builder().success(true).data(paymentService.getPaymentsByStudentId(studentId)).message("Fetched").build();
    }

    @Operation(summary = "Get payments by date")
    @GetMapping("/date/{date}")
    public ApiResponse<List<PaymentResponseDTO>> getPaymentsByDate(@PathVariable LocalDate date) {
        return ApiResponse.<List<PaymentResponseDTO>>builder().success(true).data(paymentService.getPaymentsByDate(date)).message("Fetched").build();
    }

    @Operation(summary = "Get payments by mode")
    @GetMapping("/mode/{mode}")
    public ApiResponse<List<PaymentResponseDTO>> getPaymentsByMode(@PathVariable PaymentMode mode) {
        return ApiResponse.<List<PaymentResponseDTO>>builder().success(true).data(paymentService.getPaymentsByMode(mode)).message("Fetched").build();
    }

    @Operation(summary = "Get recent payments")
    @GetMapping("/recent")
    public ApiResponse<List<Object>> getRecentPayments() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Get total payments")
    @GetMapping("/total")
    public ApiResponse<Object> getTotalPayments() {
        return ApiResponse.<Object>builder().success(true).data("Total").message("Fetched").build();
    }
}
