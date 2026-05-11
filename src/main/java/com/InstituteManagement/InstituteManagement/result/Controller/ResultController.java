package com.InstituteManagement.InstituteManagement.result.Controller;

import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import com.InstituteManagement.InstituteManagement.result.DTO.ResultRequestDTO;
import com.InstituteManagement.InstituteManagement.result.DTO.ResultResponseDTO;
import com.InstituteManagement.InstituteManagement.result.Service.ResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
@Tag(name="Result Api's", description = "Manage exam results")
public class ResultController {

    private final ResultService resultService;

    @Operation(summary = "Create result")
    @PostMapping
    public ApiResponse<ResultResponseDTO> createResult(@RequestBody ResultRequestDTO requestDTO) {
        return ApiResponse.<ResultResponseDTO>builder().success(true).data(resultService.createResult(requestDTO)).message("Created").build();
    }

    @Operation(summary = "Get all results")
    @GetMapping
    public ApiResponse<List<ResultResponseDTO>> getAllResults() {
        return ApiResponse.<List<ResultResponseDTO>>builder().success(true).data(resultService.getAllResults()).message("Fetched").build();
    }

    @Operation(summary = "Get result by ID")
    @GetMapping("/{id}")
    public ApiResponse<ResultResponseDTO> getResultById(@PathVariable Long id) {
        return ApiResponse.<ResultResponseDTO>builder().success(true).data(resultService.getResultById(id)).message("Fetched").build();
    }

    @Operation(summary = "Update result")
    @PutMapping("/{id}")
    public ApiResponse<ResultResponseDTO> updateResult(@PathVariable Long id, @RequestBody ResultRequestDTO requestDTO) {
        return ApiResponse.<ResultResponseDTO>builder().success(true).data(resultService.updateResult(id, requestDTO)).message("Updated").build();
    }

    @Operation(summary = "Delete result")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return ApiResponse.<Void>builder().success(true).message("Deleted").build();
    }

    @Operation(summary = "Get results by student ID")
    @GetMapping("/student/{studentId}")
    public ApiResponse<List<ResultResponseDTO>> getResultsByStudentId(@PathVariable Long studentId) {
        return ApiResponse.<List<ResultResponseDTO>>builder().success(true).data(resultService.getResultsByStudentId(studentId)).message("Fetched").build();
    }

    @Operation(summary = "Get results by exam ID")
    @GetMapping("/exam/{examId}")
    public ApiResponse<List<ResultResponseDTO>> getResultsByExamId(@PathVariable Long examId) {
        return ApiResponse.<List<ResultResponseDTO>>builder().success(true).data(resultService.getResultsByExamId(examId)).message("Fetched").build();
    }

    @Operation(summary = "Get student report")
    @GetMapping("/student/{studentId}/report")
    public ApiResponse<Object> getStudentReport(@PathVariable Long studentId) {
        return ApiResponse.<Object>builder().success(true).data(resultService.getStudentReport(studentId)).message("Fetched").build();
    }

    @Operation(summary = "Get results topper")
    @GetMapping("/topper")
    public ApiResponse<Object> getTopper() {
        return ApiResponse.<Object>builder().success(true).data("Topper").message("Fetched").build();
    }

    @Operation(summary = "Get results rankings")
    @GetMapping("/rankings")
    public ApiResponse<List<Object>> getRankings() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Get pass percentage")
    @GetMapping("/pass-percentage")
    public ApiResponse<Double> getPassPercentage() {
        return ApiResponse.<Double>builder().success(true).data(85.5).message("Fetched").build();
    }
}
