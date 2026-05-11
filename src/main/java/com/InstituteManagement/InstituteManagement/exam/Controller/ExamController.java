package com.InstituteManagement.InstituteManagement.exam.Controller;

import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import com.InstituteManagement.InstituteManagement.exam.DTO.ExamRequestDTO;
import com.InstituteManagement.InstituteManagement.exam.DTO.ExamResponseDTO;
import com.InstituteManagement.InstituteManagement.exam.Service.ExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
@Tag(name="Exam Api's", description = "Manage exams")
public class ExamController {

    private final ExamService examService;

    @Operation(summary = "Create exam")
    @PostMapping
    public ApiResponse<ExamResponseDTO> createExam(@RequestBody ExamRequestDTO requestDTO) {
        return ApiResponse.<ExamResponseDTO>builder().success(true).data(examService.createExam(requestDTO)).message("Created").build();
    }

    @Operation(summary = "Get all exams")
    @GetMapping
    public ApiResponse<List<ExamResponseDTO>> getAllExams() {
        return ApiResponse.<List<ExamResponseDTO>>builder().success(true).data(examService.getAllExams()).message("Fetched").build();
    }

    @Operation(summary = "Get exam by ID")
    @GetMapping("/{id}")
    public ApiResponse<ExamResponseDTO> getExamById(@PathVariable Long id) {
        return ApiResponse.<ExamResponseDTO>builder().success(true).data(examService.getExamById(id)).message("Fetched").build();
    }

    @Operation(summary = "Update exam")
    @PutMapping("/{id}")
    public ApiResponse<ExamResponseDTO> updateExam(@PathVariable Long id, @RequestBody ExamRequestDTO requestDTO) {
        return ApiResponse.<ExamResponseDTO>builder().success(true).data(examService.updateExam(id, requestDTO)).message("Updated").build();
    }

    @Operation(summary = "Delete exam")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ApiResponse.<Void>builder().success(true).message("Deleted").build();
    }

    @Operation(summary = "Get exams by course")
    @GetMapping("/course/{courseId}")
    public ApiResponse<List<ExamResponseDTO>> getExamsByCourseId(@PathVariable Long courseId) {
        return ApiResponse.<List<ExamResponseDTO>>builder().success(true).data(examService.getExamsByCourseId(courseId)).message("Fetched").build();
    }

    @Operation(summary = "Get exams by date")
    @GetMapping("/date/{date}")
    public ApiResponse<List<ExamResponseDTO>> getExamsByDate(@PathVariable LocalDate date) {
        return ApiResponse.<List<ExamResponseDTO>>builder().success(true).data(examService.getExamsByDate(date)).message("Fetched").build();
    }

    @Operation(summary = "Publish exam")
    @PostMapping("/{id}/publish")
    public ApiResponse<Object> publishExam(@PathVariable Long id) {
        return ApiResponse.<Object>builder().success(true).data("Published").message("Published").build();
    }

    @Operation(summary = "Get upcoming exams")
    @GetMapping("/upcoming")
    public ApiResponse<List<Object>> getUpcomingExams() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }

    @Operation(summary = "Get completed exams")
    @GetMapping("/completed")
    public ApiResponse<List<Object>> getCompletedExams() {
        return ApiResponse.<List<Object>>builder().success(true).data(List.of()).message("Fetched").build();
    }
}
