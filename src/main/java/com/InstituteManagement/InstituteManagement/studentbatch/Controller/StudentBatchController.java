package com.InstituteManagement.InstituteManagement.studentbatch.Controller;

import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import com.InstituteManagement.InstituteManagement.studentbatch.DTO.StudentBatchRequestDTO;
import com.InstituteManagement.InstituteManagement.studentbatch.DTO.StudentBatchResponseDTO;
import com.InstituteManagement.InstituteManagement.studentbatch.Service.StudentBatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-batches")
@RequiredArgsConstructor
@Tag(name="Student Batch Api's", description = "Manage student batch assignments")
public class StudentBatchController {

    private final StudentBatchService studentBatchService;

    @Operation(summary = "Create a new student-batch assignment")
    @PostMapping
    public ApiResponse<StudentBatchResponseDTO> createStudentBatch(@RequestBody StudentBatchRequestDTO requestDTO) {
        return ApiResponse.<StudentBatchResponseDTO>builder().success(true).data(studentBatchService.createStudentBatch(requestDTO)).message("Assignment created").build();
    }

    @Operation(summary = "Get all student-batch assignments")
    @GetMapping
    public ApiResponse<List<StudentBatchResponseDTO>> getAllStudentBatches() {
        return ApiResponse.<List<StudentBatchResponseDTO>>builder().success(true).data(studentBatchService.getAllStudentBatches()).message("Assignments fetched").build();
    }

    @Operation(summary = "Get assignment by ID")
    @GetMapping("/{id}")
    public ApiResponse<StudentBatchResponseDTO> getStudentBatchById(@PathVariable Long id) {
        return ApiResponse.<StudentBatchResponseDTO>builder().success(true).data(studentBatchService.getStudentBatchById(id)).message("Assignment fetched").build();
    }

    @Operation(summary = "Delete assignment by ID")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteStudentBatch(@PathVariable Long id) {
        studentBatchService.deleteStudentBatch(id);
        return ApiResponse.<Void>builder().success(true).message("Assignment deleted").build();
    }

    @Operation(summary = "Get assignments by student ID")
    @GetMapping("/student/{studentId}")
    public ApiResponse<List<StudentBatchResponseDTO>> getStudentBatchesByStudentId(@PathVariable Long studentId) {
        return ApiResponse.<List<StudentBatchResponseDTO>>builder().success(true).data(studentBatchService.getStudentBatchesByStudentId(studentId)).message("Assignments fetched").build();
    }

    @Operation(summary = "Get assignments by batch ID")
    @GetMapping("/batch/{batchId}")
    public ApiResponse<List<StudentBatchResponseDTO>> getStudentBatchesByBatchId(@PathVariable Long batchId) {
        return ApiResponse.<List<StudentBatchResponseDTO>>builder().success(true).data(studentBatchService.getStudentBatchesByBatchId(batchId)).message("Assignments fetched").build();
    }
}
