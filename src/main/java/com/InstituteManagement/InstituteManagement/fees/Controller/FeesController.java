package com.InstituteManagement.InstituteManagement.fees.Controller;

import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import com.InstituteManagement.InstituteManagement.fees.DTO.FeesRequestDTO;
import com.InstituteManagement.InstituteManagement.fees.DTO.FeesResponseDTO;
import com.InstituteManagement.InstituteManagement.fees.Service.FeesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
@RequiredArgsConstructor
@Tag(name="Fees Api's", description = "Manage student fees")
public class FeesController {

    private final FeesService feesService;

    @Operation(summary = "Create fees record")
    @PostMapping
    public ApiResponse<FeesResponseDTO> createFees(@RequestBody FeesRequestDTO requestDTO) {
        return ApiResponse.<FeesResponseDTO>builder().success(true).data(feesService.createFees(requestDTO)).message("Created").build();
    }

    @Operation(summary = "Get all fees")
    @GetMapping
    public ApiResponse<List<FeesResponseDTO>> getAllFees() {
        return ApiResponse.<List<FeesResponseDTO>>builder().success(true).data(feesService.getAllFees()).message("Fetched").build();
    }

    @Operation(summary = "Get fees by ID")
    @GetMapping("/{id}")
    public ApiResponse<FeesResponseDTO> getFeesById(@PathVariable Long id) {
        return ApiResponse.<FeesResponseDTO>builder().success(true).data(feesService.getFeesById(id)).message("Fetched").build();
    }

    @Operation(summary = "Update fees")
    @PutMapping("/{id}")
    public ApiResponse<FeesResponseDTO> updateFees(@PathVariable Long id, @RequestBody FeesRequestDTO requestDTO) {
        return ApiResponse.<FeesResponseDTO>builder().success(true).data(feesService.updateFees(id, requestDTO)).message("Updated").build();
    }

    @Operation(summary = "Delete fees")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteFees(@PathVariable Long id) {
        feesService.deleteFees(id);
        return ApiResponse.<Void>builder().success(true).message("Deleted").build();
    }

    @Operation(summary = "Get fees by student ID")
    @GetMapping("/student/{studentId}")
    public ApiResponse<FeesResponseDTO> getFeesByStudentId(@PathVariable Long studentId) {
        return ApiResponse.<FeesResponseDTO>builder().success(true).data(feesService.getFeesByStudentId(studentId)).message("Fetched").build();
    }

    @Operation(summary = "Get pending fees")
    @GetMapping("/pending")
    public ApiResponse<List<FeesResponseDTO>> getPendingFees() {
        return ApiResponse.<List<FeesResponseDTO>>builder().success(true).data(feesService.getPendingFees()).message("Fetched").build();
    }

    @Operation(summary = "Get paid fees")
    @GetMapping("/paid")
    public ApiResponse<List<FeesResponseDTO>> getPaidFees() {
        return ApiResponse.<List<FeesResponseDTO>>builder().success(true).data(feesService.getPaidFees()).message("Fetched").build();
    }

    @Operation(summary = "Send fee reminder")
    @PostMapping("/reminder/{studentId}")
    public ApiResponse<Object> sendReminder(@PathVariable Long studentId) {
        return ApiResponse.<Object>builder().success(true).data("Reminder sent").message("Sent").build();
    }
}
