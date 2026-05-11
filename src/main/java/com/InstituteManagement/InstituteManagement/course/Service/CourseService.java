package com.InstituteManagement.InstituteManagement.course.Service;

import com.InstituteManagement.InstituteManagement.batch.DTO.BatchResponseDTO;
import com.InstituteManagement.InstituteManagement.course.DTO.CourseRequestDTO;
import com.InstituteManagement.InstituteManagement.course.DTO.CourseResponseDTO;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;

import java.util.List;
import java.util.Map;

public interface CourseService {
    CourseResponseDTO createCourse(CourseRequestDTO requestDTO);
    List<CourseResponseDTO> getAllCourses();
    CourseResponseDTO getCourseById(Long id);
    CourseResponseDTO updateCourse(Long id, CourseRequestDTO requestDTO);
    void deleteCourse(Long id);
    
    List<CourseResponseDTO> searchCourses(String keyword);
    List<CourseResponseDTO> getCoursesByDuration(Integer months);
    List<CourseResponseDTO> getCoursesByFeesRange(Double min, Double max);
    
    List<BatchResponseDTO> getBatchesForCourse(Long id);
    List<StudentResponseDTO> getStudentsForCourse(Long id);
    long getStudentsCountForCourse(Long id);
    
    long getCourseCount();
    List<CourseResponseDTO> getPopularCourses();
}
