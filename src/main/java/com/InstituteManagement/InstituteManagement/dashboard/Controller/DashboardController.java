package com.InstituteManagement.InstituteManagement.dashboard.Controller;

import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name="Dashboard Api's", description = "Admin and Student dashboards")
public class DashboardController {

    @Operation(summary = "Get admin dashboard")
    @GetMapping("/admin")
    public ApiResponse<Object> getAdminDashboard() {
        return ApiResponse.<Object>builder().success(true).data("Admin Dashboard").message("Fetched").build();
    }

    @Operation(summary = "Get admin stats")
    @GetMapping("/admin/stats")
    public ApiResponse<Object> getAdminStats() {
        return ApiResponse.<Object>builder().success(true).data("Admin Stats").message("Fetched").build();
    }

    @Operation(summary = "Get admin revenue")
    @GetMapping("/admin/revenue")
    public ApiResponse<Object> getAdminRevenue() {
        return ApiResponse.<Object>builder().success(true).data("Admin Revenue").message("Fetched").build();
    }

    @Operation(summary = "Get admin attendance")
    @GetMapping("/admin/attendance")
    public ApiResponse<Object> getAdminAttendance() {
        return ApiResponse.<Object>builder().success(true).data("Admin Attendance").message("Fetched").build();
    }

    @Operation(summary = "Get recent students")
    @GetMapping("/admin/recent-students")
    public ApiResponse<List<Object>> getRecentStudents() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Get recent payments")
    @GetMapping("/admin/recent-payments")
    public ApiResponse<List<Object>> getRecentPayments() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Get student dashboard")
    @GetMapping("/student")
    public ApiResponse<Object> getStudentDashboard() {
        return ApiResponse.<Object>builder().success(true).data("Student Dashboard").message("Fetched").build();
    }

    @Operation(summary = "Get student attendance dashboard")
    @GetMapping("/student/attendance")
    public ApiResponse<Object> getStudentAttendanceDashboard() {
        return ApiResponse.<Object>builder().success(true).data("Student Attendance Dashboard").message("Fetched").build();
    }

    @Operation(summary = "Get student results dashboard")
    @GetMapping("/student/results")
    public ApiResponse<Object> getStudentResultsDashboard() {
        return ApiResponse.<Object>builder().success(true).data("Student Results Dashboard").message("Fetched").build();
    }

    @Operation(summary = "Get student fees dashboard")
    @GetMapping("/student/fees")
    public ApiResponse<Object> getStudentFeesDashboard() {
        return ApiResponse.<Object>builder().success(true).data("Student Fees Dashboard").message("Fetched").build();
    }
}
