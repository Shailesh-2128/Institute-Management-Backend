package com.InstituteManagement.InstituteManagement.course.Controller;

import com.InstituteManagement.InstituteManagement.batch.DTO.BatchResponseDTO;
import com.InstituteManagement.InstituteManagement.common.response.ApiResponse;
import com.InstituteManagement.InstituteManagement.course.DTO.CourseRequestDTO;
import com.InstituteManagement.InstituteManagement.course.DTO.CourseResponseDTO;
import com.InstituteManagement.InstituteManagement.course.Service.CourseService;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Tag(name="Course Api's", description = "Manage courses")
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "Create a new course")
    @PostMapping
    public ApiResponse<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO requestDTO) {
        return ApiResponse.<CourseResponseDTO>builder().success(true).data(courseService.createCourse(requestDTO)).message("Course created successfully").build();
    }

    @Operation(summary = "Get all courses")
    @GetMapping
    public ApiResponse<List<CourseResponseDTO>> getAllCourses() {
        return ApiResponse.<List<CourseResponseDTO>>builder().success(true).data(courseService.getAllCourses()).message("Courses fetched successfully").build();
    }

    @Operation(summary = "Get course by ID")
    @GetMapping("/{id}")
    public ApiResponse<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        return ApiResponse.<CourseResponseDTO>builder().success(true).data(courseService.getCourseById(id)).message("Course fetched successfully").build();
    }

    @Operation(summary = "Update course by ID")
    @PutMapping("/{id}")
    public ApiResponse<CourseResponseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDTO requestDTO) {
        return ApiResponse.<CourseResponseDTO>builder().success(true).data(courseService.updateCourse(id, requestDTO)).message("Course updated successfully").build();
    }

    @Operation(summary = "Delete course by ID")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ApiResponse.<Void>builder().success(true).message("Course deleted successfully").build();
    }

    @Operation(summary = "Search courses by keyword")
    @GetMapping("/search")
    public ApiResponse<List<CourseResponseDTO>> searchCourses(@RequestParam String keyword) {
        return ApiResponse.<List<CourseResponseDTO>>builder().success(true).data(courseService.searchCourses(keyword)).message("Courses searched successfully").build();
    }

    @Operation(summary = "Get courses by duration")
    @GetMapping("/duration/{months}")
    public ApiResponse<List<CourseResponseDTO>> getCoursesByDuration(@PathVariable Integer months) {
        return ApiResponse.<List<CourseResponseDTO>>builder().success(true).data(courseService.getCoursesByDuration(months)).message("Courses fetched successfully").build();
    }

    @Operation(summary = "Get courses by fees range")
    @GetMapping("/fees-range")
    public ApiResponse<List<CourseResponseDTO>> getCoursesByFeesRange(@RequestParam Double min, @RequestParam Double max) {
        return ApiResponse.<List<CourseResponseDTO>>builder().success(true).data(courseService.getCoursesByFeesRange(min, max)).message("Courses fetched successfully").build();
    }

    @Operation(summary = "Get batches for a course")
    @GetMapping("/{id}/batches")
    public ApiResponse<List<BatchResponseDTO>> getBatchesForCourse(@PathVariable Long id) {
        return ApiResponse.<List<BatchResponseDTO>>builder().success(true).data(courseService.getBatchesForCourse(id)).message("Batches fetched successfully").build();
    }

    @Operation(summary = "Get students for a course")
    @GetMapping("/{id}/students")
    public ApiResponse<List<StudentResponseDTO>> getStudentsForCourse(@PathVariable Long id) {
        return ApiResponse.<List<StudentResponseDTO>>builder().success(true).data(courseService.getStudentsForCourse(id)).message("Students fetched successfully").build();
    }

    @Operation(summary = "Get student count for a course")
    @GetMapping("/{id}/students/count")
    public ApiResponse<Long> getStudentsCountForCourse(@PathVariable Long id) {
        return ApiResponse.<Long>builder().success(true).data(courseService.getStudentsCountForCourse(id)).message("Count fetched successfully").build();
    }

    @Operation(summary = "Get total course count")
    @GetMapping("/count")
    public ApiResponse<Long> getCourseCount() {
        return ApiResponse.<Long>builder().success(true).data(courseService.getCourseCount()).message("Count fetched successfully").build();
    }

    @Operation(summary = "Get popular courses")
    @GetMapping("/popular")
    public ApiResponse<List<CourseResponseDTO>> getPopularCourses() {
        return ApiResponse.<List<CourseResponseDTO>>builder().success(true).data(courseService.getPopularCourses()).message("Popular courses fetched successfully").build();
    }
}
