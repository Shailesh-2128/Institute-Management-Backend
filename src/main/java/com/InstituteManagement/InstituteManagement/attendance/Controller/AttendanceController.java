package com.InstituteManagement.InstituteManagement.attendance.Controller;

import com.InstituteManagement.InstituteManagement.attendance.DTO.AttendanceRequestDTO;
import com.InstituteManagement.InstituteManagement.attendance.DTO.AttendanceResponseDTO;
import com.InstituteManagement.InstituteManagement.attendance.Service.AttendanceService;
import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
@Tag(name="Attendance Api's", description = "Manage student attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Operation(summary = "Create attendance record")
    @PostMapping
    public ApiResponse<AttendanceResponseDTO> createAttendance(@RequestBody AttendanceRequestDTO requestDTO) {
        return ApiResponse.<AttendanceResponseDTO>builder().success(true).data(attendanceService.createAttendance(requestDTO)).message("Created").build();
    }

    @Operation(summary = "Get all attendance")
    @GetMapping
    public ApiResponse<List<AttendanceResponseDTO>> getAllAttendance() {
        return ApiResponse.<List<AttendanceResponseDTO>>builder().success(true).data(attendanceService.getAllAttendance()).message("Fetched").build();
    }

    @Operation(summary = "Get attendance by ID")
    @GetMapping("/{id}")
    public ApiResponse<AttendanceResponseDTO> getAttendanceById(@PathVariable Long id) {
        return ApiResponse.<AttendanceResponseDTO>builder().success(true).data(attendanceService.getAttendanceById(id)).message("Fetched").build();
    }

    @Operation(summary = "Update attendance")
    @PutMapping("/{id}")
    public ApiResponse<AttendanceResponseDTO> updateAttendance(@PathVariable Long id, @RequestBody AttendanceRequestDTO requestDTO) {
        return ApiResponse.<AttendanceResponseDTO>builder().success(true).data(attendanceService.updateAttendance(id, requestDTO)).message("Updated").build();
    }

    @Operation(summary = "Delete attendance")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ApiResponse.<Void>builder().success(true).message("Deleted").build();
    }

    @Operation(summary = "Get attendance by student ID")
    @GetMapping("/student/{studentId}")
    public ApiResponse<List<AttendanceResponseDTO>> getAttendanceByStudentId(@PathVariable Long studentId) {
        return ApiResponse.<List<AttendanceResponseDTO>>builder().success(true).data(attendanceService.getAttendanceByStudentId(studentId)).message("Fetched").build();
    }

    @Operation(summary = "Get attendance percentage")
    @GetMapping("/student/{studentId}/percentage")
    public ApiResponse<Double> getAttendancePercentage(@PathVariable Long studentId) {
        return ApiResponse.<Double>builder().success(true).data(attendanceService.getAttendancePercentageByStudentId(studentId)).message("Fetched").build();
    }

    @Operation(summary = "Get attendance by batch ID")
    @GetMapping("/batch/{batchId}")
    public ApiResponse<List<AttendanceResponseDTO>> getAttendanceByBatchId(@PathVariable Long batchId) {
        return ApiResponse.<List<AttendanceResponseDTO>>builder().success(true).data(attendanceService.getAttendanceByBatchId(batchId)).message("Fetched").build();
    }

    @Operation(summary = "Get attendance by date")
    @GetMapping("/date/{date}")
    public ApiResponse<List<AttendanceResponseDTO>> getAttendanceByDate(@PathVariable LocalDate date) {
        return ApiResponse.<List<AttendanceResponseDTO>>builder().success(true).data(attendanceService.getAttendanceByDate(date)).message("Fetched").build();
    }

    @Operation(summary = "Get today attendance")
    @GetMapping("/today")
    public ApiResponse<List<Object>> getTodayAttendance() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Get absent attendance")
    @GetMapping("/absent")
    public ApiResponse<List<Object>> getAbsentAttendance() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Get present attendance")
    @GetMapping("/present")
    public ApiResponse<List<Object>> getPresentAttendance() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }
}
