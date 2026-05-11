package com.InstituteManagement.InstituteManagement.staff.Controller;

import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import com.InstituteManagement.InstituteManagement.staff.DTO.StaffRequestDTO;
import com.InstituteManagement.InstituteManagement.staff.DTO.StaffResponseDTO;
import com.InstituteManagement.InstituteManagement.staff.Service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
@Tag(name="Staff Api's", description = "Manage staff profiles")
public class StaffController {

    private final StaffService staffService;

    @Operation(summary = "Create a new staff profile")
    @PostMapping
    public ApiResponse<StaffResponseDTO> createStaff(@RequestBody StaffRequestDTO requestDTO) {
        StaffResponseDTO staff = staffService.createStaff(requestDTO);
        return ApiResponse.<StaffResponseDTO>builder().success(true).data(staff).message("Staff created successfully").build();
    }

    @Operation(summary = "Get all staff profiles")
    @GetMapping
    public ApiResponse<List<StaffResponseDTO>> getAllStaff() {
        return ApiResponse.<List<StaffResponseDTO>>builder().success(true).data(staffService.getAllStaff()).message("Staff list fetched successfully").build();
    }

    @Operation(summary = "Get staff profile by ID")
    @GetMapping("/{id}")
    public ApiResponse<StaffResponseDTO> getStaffById(@PathVariable Long id) {
        return ApiResponse.<StaffResponseDTO>builder().success(true).data(staffService.getStaffById(id)).message("Staff fetched successfully").build();
    }

    @Operation(summary = "Update staff profile by ID")
    @PutMapping("/{id}")
    public ApiResponse<StaffResponseDTO> updateStaff(@PathVariable Long id, @RequestBody StaffRequestDTO requestDTO) {
        return ApiResponse.<StaffResponseDTO>builder().success(true).data(staffService.updateStaff(id, requestDTO)).message("Staff updated successfully").build();
    }

    @Operation(summary = "Delete staff profile by ID")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ApiResponse.<Void>builder().success(true).message("Staff deleted successfully").build();
    }

    @Operation(summary = "Get staff dashboard stats")
    @GetMapping("/dashboard/stats")
    public ApiResponse<Object> getDashboardStats() {
        return ApiResponse.<Object>builder().success(true).data("Dashboard Stats").message("Fetched").build();
    }

    @Operation(summary = "Get staff batches")
    @GetMapping("/{id}/batches")
    public ApiResponse<List<Object>> getStaffBatches(@PathVariable Long id) {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Assign batch to staff")
    @PostMapping("/{id}/assign-batch/{batchId}")
    public ApiResponse<Object> assignBatchToStaff(@PathVariable Long id, @PathVariable Long batchId) {
        return ApiResponse.<Object>builder().success(true).data("Assigned").message("Assigned").build();
    }

    @Operation(summary = "Remove batch from staff")
    @DeleteMapping("/{id}/remove-batch/{batchId}")
    public ApiResponse<Object> removeBatchFromStaff(@PathVariable Long id, @PathVariable Long batchId) {
        return ApiResponse.<Object>builder().success(true).data("Removed").message("Removed").build();
    }

    @Operation(summary = "Create staff attendance")
    @PostMapping("/{id}/attendance")
    public ApiResponse<Object> createStaffAttendance(@PathVariable Long id, @RequestBody Object body) {
        return ApiResponse.<Object>builder().success(true).data("Created").message("Created").build();
    }

    @Operation(summary = "Get staff attendance")
    @GetMapping("/{id}/attendance")
    public ApiResponse<List<Object>> getStaffAttendance(@PathVariable Long id) {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Get staff salary")
    @GetMapping("/{id}/salary")
    public ApiResponse<Object> getStaffSalary(@PathVariable Long id) {
        return ApiResponse.<Object>builder().success(true).data(staffService.getStaffById(id).getSalary()).message("Fetched").build();
    }

    @Operation(summary = "Update staff salary")
    @PutMapping("/{id}/salary")
    public ApiResponse<Object> updateStaffSalary(@PathVariable Long id, @RequestBody Object body) {
        return ApiResponse.<Object>builder().success(true).data("Updated").message("Updated").build();
    }

    @Operation(summary = "Get current staff profile")
    @GetMapping("/me")
    public ApiResponse<StaffResponseDTO> getCurrentStaff() {
        return ApiResponse.<StaffResponseDTO>builder().success(true).data(staffService.getCurrentStaff()).message("Current staff fetched successfully").build();
    }

    @Operation(summary = "Update current staff profile")
    @PutMapping("/me")
    public ApiResponse<StaffResponseDTO> updateCurrentStaff(@RequestBody StaffRequestDTO requestDTO) {
        return ApiResponse.<StaffResponseDTO>builder().success(true).data(staffService.updateCurrentStaff(requestDTO)).message("Current staff updated successfully").build();
    }

    @Operation(summary = "Search staff profiles by keyword")
    @GetMapping("/search")
    public ApiResponse<List<StaffResponseDTO>> searchStaff(@RequestParam String keyword) {
        return ApiResponse.<List<StaffResponseDTO>>builder().success(true).data(staffService.searchStaff(keyword)).message("Staff searched successfully").build();
    }

    @Operation(summary = "Get staff profiles by designation")
    @GetMapping("/designation/{designation}")
    public ApiResponse<List<StaffResponseDTO>> getStaffByDesignation(@PathVariable String designation) {
        return ApiResponse.<List<StaffResponseDTO>>builder().success(true).data(staffService.getStaffByDesignation(designation)).message("Staff by designation fetched successfully").build();
    }

    @Operation(summary = "Get all active staff profiles")
    @GetMapping("/active")
    public ApiResponse<List<StaffResponseDTO>> getActiveStaff() {
        return ApiResponse.<List<StaffResponseDTO>>builder().success(true).data(staffService.getActiveStaff()).message("Active staff fetched successfully").build();
    }
}
