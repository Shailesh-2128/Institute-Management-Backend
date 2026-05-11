package com.InstituteManagement.InstituteManagement.batch.Controller;

import com.InstituteManagement.InstituteManagement.batch.DTO.BatchRequestDTO;
import com.InstituteManagement.InstituteManagement.batch.DTO.BatchResponseDTO;
import com.InstituteManagement.InstituteManagement.batch.Service.BatchService;
import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
@Tag(name="Batch Api's", description = "Manage batches")
public class BatchController {

    private final BatchService batchService;

    @Operation(summary = "Create a new batch")
    @PostMapping
    public ApiResponse<BatchResponseDTO> createBatch(@RequestBody BatchRequestDTO requestDTO) {
        return ApiResponse.<BatchResponseDTO>builder().success(true).data(batchService.createBatch(requestDTO)).message("Batch created successfully").build();
    }

    @Operation(summary = "Get all batches")
    @GetMapping
    public ApiResponse<List<BatchResponseDTO>> getAllBatches() {
        return ApiResponse.<List<BatchResponseDTO>>builder().success(true).data(batchService.getAllBatches()).message("Batches fetched successfully").build();
    }

    @Operation(summary = "Get batch by ID")
    @GetMapping("/{id}")
    public ApiResponse<BatchResponseDTO> getBatchById(@PathVariable Long id) {
        return ApiResponse.<BatchResponseDTO>builder().success(true).data(batchService.getBatchById(id)).message("Batch fetched successfully").build();
    }

    @Operation(summary = "Update batch by ID")
    @PutMapping("/{id}")
    public ApiResponse<BatchResponseDTO> updateBatch(@PathVariable Long id, @RequestBody BatchRequestDTO requestDTO) {
        return ApiResponse.<BatchResponseDTO>builder().success(true).data(batchService.updateBatch(id, requestDTO)).message("Batch updated successfully").build();
    }

    @Operation(summary = "Delete batch by ID")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBatch(@PathVariable Long id) {
        batchService.deleteBatch(id);
        return ApiResponse.<Void>builder().success(true).message("Batch deleted successfully").build();
    }

    @Operation(summary = "Search batches by keyword")
    @GetMapping("/search")
    public ApiResponse<List<BatchResponseDTO>> searchBatches(@RequestParam String keyword) {
        return ApiResponse.<List<BatchResponseDTO>>builder().success(true).data(batchService.searchBatches(keyword)).message("Batches searched successfully").build();
    }

    @Operation(summary = "Get batches by course ID")
    @GetMapping("/course/{courseId}")
    public ApiResponse<List<BatchResponseDTO>> getBatchesByCourse(@PathVariable Long courseId) {
        return ApiResponse.<List<BatchResponseDTO>>builder().success(true).data(batchService.getBatchesByCourse(courseId)).message("Batches fetched successfully").build();
    }

    @Operation(summary = "Get batches by staff ID")
    @GetMapping("/staff/{staffId}")
    public ApiResponse<List<BatchResponseDTO>> getBatchesByStaff(@PathVariable Long staffId) {
        return ApiResponse.<List<BatchResponseDTO>>builder().success(true).data(batchService.getBatchesByStaff(staffId)).message("Batches fetched successfully").build();
    }

    @Operation(summary = "Get students in a batch")
    @GetMapping("/{id}/students")
    public ApiResponse<List<StudentResponseDTO>> getStudentsInBatch(@PathVariable Long id) {
        return ApiResponse.<List<StudentResponseDTO>>builder().success(true).data(batchService.getStudentsInBatch(id)).message("Students fetched successfully").build();
    }

    @Operation(summary = "Add student to a batch")
    @PostMapping("/{id}/students/{studentId}")
    public ApiResponse<Void> addStudentToBatch(@PathVariable Long id, @PathVariable Long studentId) {
        batchService.addStudentToBatch(id, studentId);
        return ApiResponse.<Void>builder().success(true).message("Student added to batch").build();
    }

    @Operation(summary = "Remove student from a batch")
    @DeleteMapping("/{id}/students/{studentId}")
    public ApiResponse<Void> removeStudentFromBatch(@PathVariable Long id, @PathVariable Long studentId) {
        batchService.removeStudentFromBatch(id, studentId);
        return ApiResponse.<Void>builder().success(true).message("Student removed from batch").build();
    }

    @Operation(summary = "Get total batch count")
    @GetMapping("/count")
    public ApiResponse<Long> getBatchCount() {
        return ApiResponse.<Long>builder().success(true).data(batchService.getBatchCount()).message("Count fetched successfully").build();
    }

    @Operation(summary = "Get batch statistics")
    @GetMapping("/{id}/stats")
    public ApiResponse<Map<String, Object>> getBatchStats(@PathVariable Long id) {
        return ApiResponse.<Map<String, Object>>builder().success(true).data(batchService.getBatchStats(id)).message("Stats fetched successfully").build();
    }

    @Operation(summary = "Create batch schedule")
    @PostMapping("/{id}/schedule")
    public ApiResponse<Object> createSchedule(@PathVariable Long id, @RequestBody Object body) {
        return ApiResponse.<Object>builder().success(true).data("Scheduled").message("Scheduled").build();
    }

    @Operation(summary = "Get batch schedule")
    @GetMapping("/{id}/schedule")
    public ApiResponse<Object> getSchedule(@PathVariable Long id) {
        return ApiResponse.<Object>builder().success(true).data("Schedule").message("Fetched").build();
    }

    @Operation(summary = "Update batch schedule")
    @PutMapping("/{id}/schedule")
    public ApiResponse<Object> updateSchedule(@PathVariable Long id, @RequestBody Object body) {
        return ApiResponse.<Object>builder().success(true).data("Scheduled").message("Updated").build();
    }
}
