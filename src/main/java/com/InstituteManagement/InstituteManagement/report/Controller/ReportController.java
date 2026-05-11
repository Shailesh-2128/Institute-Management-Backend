package com.InstituteManagement.InstituteManagement.report.Controller;

import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@Tag(name="Report Api's", description = "System reports")
public class ReportController {

    @Operation(summary = "Get attendance report by student")
    @GetMapping("/attendance/student/{studentId}")
    public ApiResponse<Object> getAttendanceReportByStudent(@PathVariable Long studentId) {
        return ApiResponse.<Object>builder().success(true).data("Student Attendance Report").message("Fetched").build();
    }

    @Operation(summary = "Get attendance report by batch")
    @GetMapping("/attendance/batch/{batchId}")
    public ApiResponse<Object> getAttendanceReportByBatch(@PathVariable Long batchId) {
        return ApiResponse.<Object>builder().success(true).data("Batch Attendance Report").message("Fetched").build();
    }

    @Operation(summary = "Get attendance report by course")
    @GetMapping("/attendance/course/{courseId}")
    public ApiResponse<Object> getAttendanceReportByCourse(@PathVariable Long courseId) {
        return ApiResponse.<Object>builder().success(true).data("Course Attendance Report").message("Fetched").build();
    }

    @Operation(summary = "Get pending fees report")
    @GetMapping("/fees/pending")
    public ApiResponse<List<Object>> getPendingFeesReport() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Get paid fees report")
    @GetMapping("/fees/paid")
    public ApiResponse<List<Object>> getPaidFeesReport() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Get monthly fees report")
    @GetMapping("/fees/monthly")
    public ApiResponse<List<Object>> getMonthlyFeesReport() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Get fees report by student")
    @GetMapping("/fees/student/{studentId}")
    public ApiResponse<Object> getFeesReportByStudent(@PathVariable Long studentId) {
        return ApiResponse.<Object>builder().success(true).data("Student Fees Report").message("Fetched").build();
    }

    @Operation(summary = "Get results report by student")
    @GetMapping("/results/student/{studentId}")
    public ApiResponse<Object> getResultsReportByStudent(@PathVariable Long studentId) {
        return ApiResponse.<Object>builder().success(true).data("Student Results Report").message("Fetched").build();
    }

    @Operation(summary = "Get results report by exam")
    @GetMapping("/results/exam/{examId}")
    public ApiResponse<Object> getResultsReportByExam(@PathVariable Long examId) {
        return ApiResponse.<Object>builder().success(true).data("Exam Results Report").message("Fetched").build();
    }

    @Operation(summary = "Get results topper report")
    @GetMapping("/results/topper")
    public ApiResponse<Object> getResultsTopperReport() {
        return ApiResponse.<Object>builder().success(true).data("Results Topper Report").message("Fetched").build();
    }
}
