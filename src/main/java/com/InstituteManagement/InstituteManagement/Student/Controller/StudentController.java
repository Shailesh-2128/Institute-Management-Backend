package com.InstituteManagement.InstituteManagement.student.Controller;

import com.InstituteManagement.InstituteManagement.batch.DTO.BatchResponseDTO;
import com.InstituteManagement.InstituteManagement.course.DTO.CourseResponseDTO;
import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentRequestDTO;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;
import com.InstituteManagement.InstituteManagement.student.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name="Student Api's", description = "Manage student profiles")
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Create a new student profile")
    @PostMapping
    public ApiResponse<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO requestDTO) {
        StudentResponseDTO student = studentService.createStudent(requestDTO);
        return ApiResponse.<StudentResponseDTO>builder().success(true).data(student).message("Student created successfully").build();
    }

    @Operation(summary = "Get all student profiles")
    @GetMapping
    public ApiResponse<List<StudentResponseDTO>> getAllStudents() {
        return ApiResponse.<List<StudentResponseDTO>>builder().success(true).data(studentService.getAllStudents()).message("Students fetched successfully").build();
    }

    @Operation(summary = "Get student profile by ID")
    @GetMapping("/{id}")
    public ApiResponse<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        return ApiResponse.<StudentResponseDTO>builder().success(true).data(studentService.getStudentById(id)).message("Student fetched successfully").build();
    }

    @Operation(summary = "Update student profile by ID")
    @PutMapping("/{id}")
    public ApiResponse<StudentResponseDTO> updateStudent(@PathVariable Long id, @RequestBody StudentRequestDTO requestDTO) {
        return ApiResponse.<StudentResponseDTO>builder().success(true).data(studentService.updateStudent(id, requestDTO)).message("Student updated successfully").build();
    }

    @Operation(summary = "Delete student profile by ID")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ApiResponse.<Void>builder().success(true).message("Student deleted successfully").build();
    }

    @Operation(summary = "Get current student profile")
    @GetMapping("/me")
    public ApiResponse<StudentResponseDTO> getCurrentStudent() {
        return ApiResponse.<StudentResponseDTO>builder().success(true).data(studentService.getCurrentStudent()).message("Current student fetched successfully").build();
    }

    @Operation(summary = "Update current student profile")
    @PutMapping("/me")
    public ApiResponse<StudentResponseDTO> updateCurrentStudent(@RequestBody StudentRequestDTO requestDTO) {
        return ApiResponse.<StudentResponseDTO>builder().success(true).data(studentService.updateCurrentStudent(requestDTO)).message("Current student updated successfully").build();
    }

    @Operation(summary = "Search student profiles by keyword")
    @GetMapping("/search")
    public ApiResponse<List<StudentResponseDTO>> searchStudents(@RequestParam String keyword) {
        return ApiResponse.<List<StudentResponseDTO>>builder().success(true).data(studentService.searchStudents(keyword)).message("Students searched successfully").build();
    }

    @Operation(summary = "Get active student profiles")
    @GetMapping("/active")
    public ApiResponse<List<StudentResponseDTO>> getActiveStudents() {
        return ApiResponse.<List<StudentResponseDTO>>builder().success(true).data(studentService.getActiveStudents()).message("Active students fetched successfully").build();
    }

    @Operation(summary = "Get inactive student profiles")
    @GetMapping("/inactive")
    public ApiResponse<List<StudentResponseDTO>> getInactiveStudents() {
        return ApiResponse.<List<StudentResponseDTO>>builder().success(true).data(studentService.getInactiveStudents()).message("Inactive students fetched successfully").build();
    }

    @Operation(summary = "Get batches for student")
    @GetMapping("/{id}/batches")
    public ApiResponse<List<BatchResponseDTO>> getBatchesForStudent(@PathVariable Long id) {
        return ApiResponse.<List<BatchResponseDTO>>builder().success(true).data(studentService.getBatchesForStudent(id)).message("Batches fetched successfully").build();
    }

    @Operation(summary = "Get courses for student")
    @GetMapping("/{id}/courses")
    public ApiResponse<List<CourseResponseDTO>> getCoursesForStudent(@PathVariable Long id) {
        return ApiResponse.<List<CourseResponseDTO>>builder().success(true).data(studentService.getCoursesForStudent(id)).message("Courses fetched successfully").build();
    }

    @Operation(summary = "Assign batch to student")
    @PostMapping("/{id}/assign-batch/{batchId}")
    public ApiResponse<StudentResponseDTO> assignBatchToStudent(@PathVariable Long id, @PathVariable Long batchId) {
        return ApiResponse.<StudentResponseDTO>builder().success(true).data(studentService.assignBatchToStudent(id, batchId)).message("Batch assigned successfully").build();
    }

    @Operation(summary = "Remove batch from student")
    @DeleteMapping("/{id}/remove-batch/{batchId}")
    public ApiResponse<StudentResponseDTO> removeBatchFromStudent(@PathVariable Long id, @PathVariable Long batchId) {
        return ApiResponse.<StudentResponseDTO>builder().success(true).data(studentService.removeBatchFromStudent(id, batchId)).message("Batch removed successfully").build();
    }
}
