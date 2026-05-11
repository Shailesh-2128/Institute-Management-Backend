package com.InstituteManagement.InstituteManagement.student.Service;

import com.InstituteManagement.InstituteManagement.batch.DTO.BatchResponseDTO;
import com.InstituteManagement.InstituteManagement.course.DTO.CourseResponseDTO;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentRequestDTO;
import com.InstituteManagement.InstituteManagement.student.DTO.StudentResponseDTO;

import java.util.List;

public interface StudentService {
    StudentResponseDTO createStudent(StudentRequestDTO requestDTO);
    List<StudentResponseDTO> getAllStudents();
    StudentResponseDTO getStudentById(Long id);
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO requestDTO);
    void deleteStudent(Long id);
    
    StudentResponseDTO getCurrentStudent();
    StudentResponseDTO updateCurrentStudent(StudentRequestDTO requestDTO);
    List<StudentResponseDTO> searchStudents(String keyword);
    List<StudentResponseDTO> getActiveStudents();
    List<StudentResponseDTO> getInactiveStudents();
    
    List<BatchResponseDTO> getBatchesForStudent(Long id);
    List<CourseResponseDTO> getCoursesForStudent(Long id);
    StudentResponseDTO assignBatchToStudent(Long studentId, Long batchId);
    StudentResponseDTO removeBatchFromStudent(Long studentId, Long batchId);
}
